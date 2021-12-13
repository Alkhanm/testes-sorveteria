package com.github.alkhanm.sorveteria.resources.controllers;

import com.github.alkhanm.sorveteria.domain.Sorvete;
import com.github.alkhanm.sorveteria.domain.interfaces.SensorClimatico;
import com.github.alkhanm.sorveteria.services.SorveteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sorvetes")
@CrossOrigin(origins = {"http://localhost:8080/"})
public class SorveteController {
    private final SorveteService service;

    public SorveteController(SorveteService service) {
        this.service = service;
    }


    @GetMapping
    public @ResponseBody
    List<Sorvete> buscarTodos() {
        return service.findAll();
    }

//
//    @RequestMapping(value = "/hello/{name}", method = RequestMethod.POST)
//    public String sayHi(
//            @PathVariable("name") String name,
//            @RequestBody Topic topic,
//            //@RequestParam(required = false, name = "s") String s,
//            @RequestParam Map<String, String> req) {
//
//        return "Hi "+name +" Topic : "+ topic+" RequestParams : "+req;
//    }

    @GetMapping("/desconto/{id}")
    public @ResponseBody
    Sorvete solicitarDesconto(@RequestBody SensorClimatico sensor, @PathVariable Long id) {
        return service.calcularDesconto(sensor, id);
    }

    @PostMapping
    public @ResponseBody
    Sorvete salvar(@RequestBody Sorvete product) {
        return service.save(product);
    }


    @DeleteMapping("/{id}")
    public @ResponseBody
    void deletar(@PathVariable Long id) {
        service.delete(id);
    }
}
