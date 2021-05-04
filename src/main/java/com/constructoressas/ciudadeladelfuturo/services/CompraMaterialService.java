package com.constructoressas.ciudadeladelfuturo.services;

import com.constructoressas.ciudadeladelfuturo.entities.CompraMaterial;
import com.constructoressas.ciudadeladelfuturo.entities.Material;
import com.constructoressas.ciudadeladelfuturo.repositories.CompraMaterialRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.MaterialRepository;
import com.constructoressas.ciudadeladelfuturo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class CompraMaterialService {
   @Autowired
    CompraMaterialRepository repository;
    @Autowired
    MaterialRepository materialRepository;

    public Map<String, Object> ingresarMaterial(CompraMaterial compraMaterial) {
        String nombreMaterial = compraMaterial.getIdMaterial().getNombre();
        Material material = materialRepository.findByNombre(nombreMaterial);

        Double nuevaCantidad = material.getCantidadDisponible() + compraMaterial.getCantidadIngresada();
        material.setCantidadDisponible(nuevaCantidad);
        material = materialRepository.save(material);

        compraMaterial.setFechaIngreso(new Date());
        compraMaterial.setIdMaterial(material);

        return ResponseUtil.mapOK(repository.save(compraMaterial));
    }

}
