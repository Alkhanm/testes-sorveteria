package com.github.alkhanm.sorveteria.domain;

import com.github.alkhanm.sorveteria.domain.interfaces.SensorClimatico;

public class SensorClimaticoPadrao implements SensorClimatico {

    private double temperaturaAtual;
    private double temperaturaMediaAno;

    public SensorClimaticoPadrao() {
    }

    public SensorClimaticoPadrao(double temperaturaAtual, double temperaturaMediaAno) {
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMediaAno = temperaturaMediaAno;
    }


    @Override
    public double capturarTemperaturaAtual() {
        return temperaturaAtual;
    }

    @Override
    public double capturarTemperaturaMediaAno() {
        return temperaturaMediaAno;
    }
}
