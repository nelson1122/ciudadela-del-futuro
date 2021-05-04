package com.constructoressas.ciudadeladelfuturo.services;

import com.constructoressas.ciudadeladelfuturo.dto.FechaFinalizacionDTO;
import com.constructoressas.ciudadeladelfuturo.entities.*;
import com.constructoressas.ciudadeladelfuturo.repositories.MaterialRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.OrdenConstruccionRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.SolicitudRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.TipoConstruccionRepository;
import com.constructoressas.ciudadeladelfuturo.utils.ResponseUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.constructoressas.ciudadeladelfuturo.utils.Constants.*;

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
    @Value("${xlsx.temp.file.path}")
    private String ruta;

    public Map<String, Object> crearSolicitud(Solicitud solicitud) {
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
                return ResponseUtil.mapError(MSG_CANTIDAD_MATERIAL_NO_SUFICIENTE + nombreMaterial);
            }
        }
        // 2. validar construccion existente en la zona
        Solicitud solicitudExistente = solicitudRepository.findByXY(solicitud.getX(), solicitud.getY());
        if (solicitudExistente != null) {
            estadoOrdenConstruccion.setId(ESTADO_SOLICITUD_RECHAZADA);
            ordenConstruccion.setMotivo(MSG_COORDENADAS_NO_DISPONIBLES);
            ordenConstruccionRepository.save(ordenConstruccion);
            return ResponseUtil.mapError(MSG_COORDENADAS_NO_DISPONIBLES);
        }

        // Si las validaciones anteriores son correctas se procede a registrar la solicitud
        materialRepository.saveAll(materialList);
        estadoOrdenConstruccion.setId(ESTADO_SOLICITUD_PROGRAMADA);

        ordenConstruccionRepository.save(ordenConstruccion);

        return ResponseUtil.mapOK(solicitud);
    }

    public Map<String, Object> consultarFechaFinalizacion() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = solicitudRepository.findMaxFechaFin();
        if (fecha != null) {
            FechaFinalizacionDTO fechaFinalizacionDTO = new FechaFinalizacionDTO();
            fechaFinalizacionDTO.setFechaCulminacion(sdf.format(fecha));
            return ResponseUtil.mapOK(fechaFinalizacionDTO);
        }
        return ResponseUtil.mapError(MSG_SOLICITUDES_NO_REGISTRADAS);
    }

    public Map<String, Object> generarReporte() {
        List<Object[]> datosReporte = solicitudRepository.findAllSolicitudes();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Historial solicitudes");

        int rowNum = 0;
        int colNum = 0;
        Row row = sheet.createRow(rowNum++);
        for (Object field : HEADERS_REPORTE) {
            Cell cell = row.createCell(colNum++);
            if (field instanceof String) {
                cell.setCellValue((String) field);
            } else if (field instanceof Integer) {
                cell.setCellValue((Integer) field);
            }
        }
        for (Object[] datos : datosReporte) {
            row = sheet.createRow(rowNum++);
            colNum = 0;
            for (Object field : datos) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(ruta);
            workbook.write(outputStream);
            workbook.close();
            return ResponseUtil.mapOK(MSG_REPORTE_GENERADO);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtil.mapError(MSG_ERROR_GENERAR_REPORTE);
        }
    }
}
