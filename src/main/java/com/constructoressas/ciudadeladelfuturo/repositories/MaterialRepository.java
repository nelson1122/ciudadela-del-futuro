package com.constructoressas.ciudadeladelfuturo.repositories;

import com.constructoressas.ciudadeladelfuturo.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
    @Query("SELECT m FROM Material m WHERE m.nombre LIKE ?1")
    Material findByNombre(String nombre);
}
