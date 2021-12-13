package com.github.alkhanm.sorveteria.services;

import com.github.alkhanm.sorveteria.domain.Sorvete;
import com.github.alkhanm.sorveteria.domain.interfaces.SensorClimatico;
import com.github.alkhanm.sorveteria.repositories.SorveteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SorveteService {
    private final SorveteRepository repository;

    public SorveteService(SorveteRepository repository) {
        this.repository = repository;
    }

    public Sorvete find(Long id){
        return repository.findById(id).get();
    }

    public List<Sorvete> findAll(){
        return repository.findAll();
    }

    public Sorvete save(Sorvete product){
       return repository.save(product);
    }

    public void delete(Long id) {
       repository.findById(id)
               .ifPresent(repository::delete);
    }

    public Sorvete calcularDesconto(SensorClimatico sensor, Long id) {
        return find(id).aplicarDesconto(sensor);
    }
}
