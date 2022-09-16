package com.egg.EggNewsintel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class Periodista extends Usuario{
        @Column(columnDefinition = "double default 0.0")
        private Double salarioMensual;

        @OneToMany
        @JoinColumn(name = "idNoticia", insertable = false, updatable = false)
        private List<Noticia> cantidadNoticias;

        public Double getSalarioMensual() {
                return salarioMensual;
        }

        public void setSalarioMensual(Double salarioMensual) {
                this.salarioMensual = salarioMensual;
        }

        public List<Noticia> getCantidadNoticias() {
                return cantidadNoticias;
        }

        public void setCantidadNoticias(List<Noticia> cantidadNoticias) {
                this.cantidadNoticias = cantidadNoticias;
        }
}
