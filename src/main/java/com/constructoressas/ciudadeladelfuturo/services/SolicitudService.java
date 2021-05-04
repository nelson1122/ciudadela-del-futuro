package com.constructoressas.ciudadeladelfuturo.services;

import com.constructoressas.ciudadeladelfuturo.entities.*;
import com.constructoressas.ciudadeladelfuturo.repositories.MaterialRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.OrdenConstruccionRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.SolicitudRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.TipoConstruccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.constructoressas.ciudadeladelfuturo.utls.Constants.*;

@Service
@Transactional
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    TipoConstruccionRepository tipoConstruccionRepository;
    @Autowired
    OrdenConstruccionRepository ordenConstruccionRepository;
    @Autowired
    MaterialRepository materialRepository;

    public Solicitud crearSolicitud(Solicitud solicitud) {
        String nombreTipoConstruccion = solicitud.getIdTipoConstruccion().getNombre();
        TipoConstruccion tipoConstruccion = tipoConstruccionRepository.findByNombre(nombreTipoConstruccion);
        solicitud.setIdTipoConstruccion(tipoConstruccion);
        solicitud = solicitudRepository.save(solicitud);

        // 3. Creación orden construccion
        EstadoOrdenConstruccion estadoOrdenConstruccion = new EstadoOrdenConstruccion();
        OrdenConstruccion ordenConstruccion = new OrdenConstruccion();
        ordenConstruccion.setIdEstadoOrdenConstruccion(estadoOrdenConstruccion);
        ordenConstruccion.setIdSolicitud(solicitud);

        // 1. validar materiales disponibles
        List<Material> materialList = materialRepository.findAll();
        for (Material material : materialList) {
            String nombreMaterial = material.getNombre();
            switch (nombreMaterial) {
                case MATERIAL_CEMENTO:
                    material.setCantidadDisponible(material.getCantidadDisponible() - tipoConstruccion.getCemento());
                    break;
                case MATERIAL_GRAVA:
                    material.setCantidadDisponible(material.getCantidadDisponible() - tipoConstruccion.getGrava());
                    break;
                case MATERIAL_ARENA:
                    material.setCantidadDisponible(material.getCantidadDisponible() - tipoConstruccion.getArena());
                    break;
                case MATERIAL_MADERA:
                    material.setCantidadDisponible(material.getCantidadDisponible() - tipoConstruccion.getMadera());
                    break;
                case MATERIAL_ADOBE:
                    material.setCantidadDisponible(material.getCantidadDisponible() - tipoConstruccion.getAdobe());
                    break;
                default:
                    throw new IllegalStateException(MSG_VALOR_DESCONOCIDO + nombreMaterial);
            }
            if (material.getCantidadDisponible() <= 0) {
                estadoOrdenConstruccion.setId(ESTADO_SOLICITUD_PENDIENTE);
                ordenConstruccion.setMotivo(MSG_CANTIDAD_MATERIAL_NO_SUFICIENTE + nombreMaterial);
                ordenConstruccionRepository.save(ordenConstruccion);
                throw new IllegalArgumentException(MSG_CANTIDAD_MATERIAL_NO_SUFICIENTE + nombreMaterial);
            }
        }
        // 2. validar construccion existente en la zona
        Solicitud solicitudExistente = solicitudRepository.findByXY(solicitud.getX(), solicitud.getY());
        if (solicitudExistente != null) {
            estadoOrdenConstruccion.setId(ESTADO_SOLICITUD_RECHAZADA);
            ordenConstruccion.setMotivo(MSG_COORDENADAS_NO_DISPONIBLES);
            ordenConstruccionRepository.save(ordenConstruccion);
            throw new IllegalArgumentException(MSG_COORDENADAS_NO_DISPONIBLES);
        }

        // Si las validaciones anteriores son correctas se procede a registrar la solicitud
        materialRepository.saveAll(materialList);
        estadoOrdenConstruccion.setId(ESTADO_SOLICITUD_PROGRAMADA);

        Date fechaUltimaSolicitudEnProgreso = solicitudRepository.findMaxFechaFin();


        return solicitud;
    }
}
