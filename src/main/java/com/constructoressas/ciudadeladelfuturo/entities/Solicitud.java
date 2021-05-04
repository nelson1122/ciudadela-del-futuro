package com.constructoressas.ciudadeladelfuturo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "solicitud")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_construccion", nullable = false)
    private TipoConstruccion idTipoConstruccion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idSolicitud")
    private List<OrdenConstruccion> ordenConstruccionList;

    public Solicitud() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public TipoConstruccion getIdTipoConstruccion() {
        return idTipoConstruccion;
    }

    public void setIdTipoConstruccion(TipoConstruccion idTipoConstruccion) {
        this.idTipoConstruccion = idTipoConstruccion;
    }

    public List<OrdenConstruccion> getOrdenConstruccionList() {
        return ordenConstruccionList;
    }

    public void setOrdenConstruccionList(List<OrdenConstruccion> ordenConstruccionList) {
        this.ordenConstruccionList = ordenConstruccionList;
    }
}
