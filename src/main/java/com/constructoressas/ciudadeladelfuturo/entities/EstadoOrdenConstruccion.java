package com.constructoressas.ciudadeladelfuturo.entities;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "estado_orden_construccion")
public class EstadoOrdenConstruccion {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "x")
    private String nombre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idEstadoOrdenConstruccion")
    private List<OrdenConstruccion> ordenConstruccionList;

    public EstadoOrdenConstruccion() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<OrdenConstruccion> getOrdenConstruccionList() {
        return ordenConstruccionList;
    }

    public void setOrdenConstruccionList(List<OrdenConstruccion> ordenConstruccionList) {
        this.ordenConstruccionList = ordenConstruccionList;
    }
}
