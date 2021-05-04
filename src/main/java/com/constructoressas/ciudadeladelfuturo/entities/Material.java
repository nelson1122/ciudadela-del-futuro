package com.constructoressas.ciudadeladelfuturo.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad_disponible")
    private Double cantidadDisponible;
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idMaterial")
    private List<CompraMaterial> materialList;

    public Material() {
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

    public Double getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(Double cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<CompraMaterial> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<CompraMaterial> materialList) {
        this.materialList = materialList;
    }
}
