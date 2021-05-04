package com.constructoressas.ciudadeladelfuturo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tipo_construccion")
public class TipoConstruccion {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "cemento")
    private Double cemento;

    @Column(name = "grava")
    private Double grava;

    @Column(name = "arena")
    private Double arena;

    @Column(name = "madera")
    private Double madera;

    @Column(name = "adobe")
    private Double adobe;

    @Column(name = "dias")
    private Integer dias;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idTipoConstruccion")
    private List<Solicitud> solicitudList;

    public TipoConstruccion() {
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Double getCemento() {
        return cemento;
    }

    public void setCemento(Double cemento) {
        this.cemento = cemento;
    }

    public Double getGrava() {
        return grava;
    }

    public void setGrava(Double grava) {
        this.grava = grava;
    }

    public Double getArena() {
        return arena;
    }

    public void setArena(Double arena) {
        this.arena = arena;
    }

    public Double getMadera() {
        return madera;
    }

    public void setMadera(Double madera) {
        this.madera = madera;
    }

    public Double getAdobe() {
        return adobe;
    }

    public void setAdobe(Double adobe) {
        this.adobe = adobe;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }
}
