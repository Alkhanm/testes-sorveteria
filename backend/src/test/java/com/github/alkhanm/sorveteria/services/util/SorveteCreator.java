package com.github.alkhanm.sorveteria.services.util;

import com.github.alkhanm.sorveteria.domain.Sorvete;
import com.github.alkhanm.sorveteria.domain.enums.Categoria;

import java.util.List;
import java.util.Random;

public class SorveteCreator {
    private static final List<String> names = List.of(
            "HD 1000GB",
            "SSD",
            "Mouse pad",
            "Notebook",
            "Mouse"
    );
    private static final List<String> brand = List.of(
            "Kingston",
            "WD Green",
            "Positivo",
            "ACER",
            "Leadership"
    );

    public static Sorvete criarSorvete() {
        double price = new Random().nextDouble(10);
        return Sorvete
                .builder()
                .nome("Sorvete test")
                .categoria(Categoria.FRUTAS)
                .preco(price)
                .qnt(new Random().nextInt(9))
                .build();
    }

    public static List<Sorvete> criarListaSorvete() {
        return List.of(
                criarSorvete(),
                criarSorvete(),
                criarSorvete());
    }

    public static String createName() {
        int r = new Random().nextInt(4);
        return names.get(r);
    }

    public static String createBrand() {
        int r = new Random().nextInt(4);
        return brand.get(r);
    }

    public static int createPrice() {
        return new Random().nextInt(3000);
    }

}
