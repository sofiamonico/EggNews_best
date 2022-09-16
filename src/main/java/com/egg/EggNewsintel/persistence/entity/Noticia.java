package com.egg.EggNewsintel.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity

public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNoticia;
    private String titulo;
    @Column(nullable= false, columnDefinition = "MEDIUMTEXT" )
    private String cuerpo;
    @Column(nullable= false, columnDefinition = "MEDIUMTEXT" )
    private String img;
    @Temporal(TemporalType.DATE)
    private Date fecha_publicacion;

    @ManyToOne
    private Periodista periodista;

    public Integer getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(Integer idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Date fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }
}
