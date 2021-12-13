package com.github.alkhanm.sorveteria.domain;


import com.github.alkhanm.sorveteria.domain.enums.Categoria;
import com.github.alkhanm.sorveteria.domain.interfaces.SensorClimatico;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "tb_sorvete")
public class Sorvete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Double preco;
    private Integer qnt;

    // Usa um serviço de sensor de temperatura para aplicar o desconto
    public Sorvete aplicarDesconto(SensorClimatico sensor) {
        double tempAtual = sensor.capturarTemperaturaAtual();
        double tempMedia = sensor.capturarTemperaturaMediaAno();
        // Em dias frios haverá um desconto de 10% nos preços
        if (tempAtual > tempMedia) this.preco = this.preco * 0.9;
        return this;
    }


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sorvete sorvete = (Sorvete) o;
        return Objects.equals(id, sorvete.id) && Objects.equals(nome, sorvete.nome) && categoria == sorvete.categoria && Objects.equals(preco, sorvete.preco) && Objects.equals(qnt, sorvete.qnt);
    }
    @Override public int hashCode() {
        return Objects.hash(id, nome, categoria, preco, qnt);
    }
}
