package com.egg.EggNewsintel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
public class Periodista extends Usuario{
        @Column(columnDefinition = "double default 0.0")
        private Double salarioMensual;

        @OneToMany(mappedBy = "periodista", fetch = FetchType.EAGER)
        private List<Noticia> noticias;

        public Double getSalarioMensual() {
                return salarioMensual;
        }

        public void setSalarioMensual(Double salarioMensual) {
                this.salarioMensual = salarioMensual;
        }

        public List<Noticia> getNoticias() {
                return noticias;
        }

        public void setNoticias(List<Noticia> noticias) {
                this.noticias = noticias;
        }
}
