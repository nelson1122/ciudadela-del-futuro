package com.constructoressas.ciudadeladelfuturo.repositories;

import com.constructoressas.ciudadeladelfuturo.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    @Query("SELECT s FROM Solicitud WHERE s.x = ?1 AND s.y = ?2")
    Solicitud findByXY(Double x, Double y);

    @Query("SELECT max(s.fechaFin) FROM Solicitud s JOIN s.ordenConstruccionList o JOIN o.idEstadoOrdenConstruccion e WHERE e.id = 4")
    Date findMaxFechaFin();
}
