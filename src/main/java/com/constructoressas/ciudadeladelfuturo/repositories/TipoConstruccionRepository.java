package com.constructoressas.ciudadeladelfuturo.repositories;

import com.constructoressas.ciudadeladelfuturo.entities.TipoConstruccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoConstruccionRepository extends JpaRepository<TipoConstruccion, Integer> {
    @Query("SELECT t FROM TipoConstruccion WHERE t.nombre LIKE ?1")
    TipoConstruccion findByNombre(String nombre);
}
