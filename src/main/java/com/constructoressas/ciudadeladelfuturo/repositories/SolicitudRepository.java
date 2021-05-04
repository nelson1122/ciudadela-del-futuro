package com.constructoressas.ciudadeladelfuturo.repositories;

import com.constructoressas.ciudadeladelfuturo.dto.SolicitudDTO;
import com.constructoressas.ciudadeladelfuturo.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    @Query("SELECT s FROM Solicitud WHERE s.x = ?1 AND s.y = ?2")
    Solicitud findByXY(Double x, Double y);

    @Query("SELECT max(s.fechaFin) FROM Solicitud s JOIN s.ordenConstruccionList o JOIN o.idEstadoOrdenConstruccion e WHERE e.id = 4")
    Date findMaxFechaFin();

    @Query(value = "" +
            "SELECT\n" +
            "  tc.nombre AS \"TIPO_CONSTRUCCION\",\n" +
            "  s.x AS ,\n" +
            "  s.y,\n" +
            "  c2.nombre AS estado,\n" +
            "  o.fecha_estado AS \"fechaEstado\"\n" +
            "FROM solicitud s\n" +
            "  JOIN tipo_construccion tc ON s.id_tipo_construccion = tc.id\n" +
            "  JOIN orden_construccion o ON s.id = o.id_solicitud\n" +
            "  JOIN estado_orden_construccion c2 ON o.id_estado = c2.id", nativeQuery = true)
    List<Object[]> findAllSolicitudes();
}
