package com.constructoressas.ciudadeladelfuturo.services;

import com.constructoressas.ciudadeladelfuturo.entities.CompraMaterial;
import com.constructoressas.ciudadeladelfuturo.entities.Material;
import com.constructoressas.ciudadeladelfuturo.repositories.CompraMaterialRepository;
import com.constructoressas.ciudadeladelfuturo.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class CompraMaterialService {

    @Autowired
    CompraMaterialRepository repository;
    @Autowired
    MaterialRepository materialRepository;

    public CompraMaterial ingresarMaterial(CompraMaterial compraMaterial){
        String nombreMaterial = compraMaterial.getIdMaterial().getNombre();
        Material material = materialRepository.findByNombre(nombreMaterial);

        Double nuevaCantidad = material.getCantidadDisponible() + compraMaterial.getCantidadIngresada();
        material.setCantidadDisponible(nuevaCantidad);
        material = materialRepository.save(material);

        compraMaterial.setFechaIngreso(new Date());
        compraMaterial.setIdMaterial(material);
        return repository.save(compraMaterial);
    }

}
