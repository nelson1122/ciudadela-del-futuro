package com.constructoressas.ciudadeladelfuturo.entities;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "compra_material")
public class CompraMaterial {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "fechaIngreso")
    private Date fechaIngreso;
    @Column(name = "cantidad_ingresada")
    private Double cantidadIngresada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_material", nullable = false)
    private Material idMaterial;

    public CompraMaterial() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getCantidadIngresada() {
        return cantidadIngresada;
    }

    public void setCantidadIngresada(Double cantidadIngresada) {
        this.cantidadIngresada = cantidadIngresada;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }
}
