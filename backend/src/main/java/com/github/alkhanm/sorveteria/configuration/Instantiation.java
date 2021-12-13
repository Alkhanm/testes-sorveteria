package com.github.alkhanm.sorveteria.configuration;

import com.github.alkhanm.sorveteria.domain.Sorvete;
import com.github.alkhanm.sorveteria.domain.enums.Categoria;
import com.github.alkhanm.sorveteria.repositories.SorveteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {
    private final SorveteRepository sorveteRepository;

    public Instantiation(SorveteRepository sorveteRepository) {
        this.sorveteRepository = sorveteRepository;
    }

    @Override
    public void run(String... args) {
        sorveteRepository.deleteAll();
        sorveteRepository.saveAll(List.of(
                new Sorvete(1L, "Morango", Categoria.FRUTAS, 120.90, 3)
        ));
    }
}
