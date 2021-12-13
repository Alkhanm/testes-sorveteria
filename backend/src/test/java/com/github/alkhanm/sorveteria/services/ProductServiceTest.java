package com.github.alkhanm.sorveteria.services;
import com.github.alkhanm.sorveteria.domain.Sorvete;
import com.github.alkhanm.sorveteria.domain.interfaces.SensorClimatico;
import com.github.alkhanm.sorveteria.repositories.SorveteRepository;
import com.github.alkhanm.sorveteria.services.util.SorveteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("Testes para SorveteService")
public class ProductServiceTest {

    @Mock
    private SensorClimatico sensorClimatico;

    @Autowired
    private SorveteService servico;

    @Autowired
    private SorveteRepository repositorio;

    private final List<Sorvete> sorveteListaInicial = SorveteCreator.criarListaSorvete();

    @BeforeEach
    public void iniciar(){
        repositorio.saveAll(sorveteListaInicial);
    }

    @AfterEach
    public void limpar(){
        repositorio.deleteAll();
    }

    @Test
    @DisplayName("Teste se a lista com todos os sorvetes está sendo retornada")
    void  listarTodosTeste(){
        List<Sorvete> sorvetesLista = servico.findAll();

        // Assegura que a lista não está nula ou vazia
        Assertions.assertThat(sorvetesLista)
                .isNotNull()
                .isNotEmpty();

        // Assegura que os sorvetes anteriormente salvos estão na lista
        Assertions.assertThat(sorvetesLista)
                .containsAll(sorveteListaInicial);
    }

    @Test
    @DisplayName("Testa se o sorvete esta sendo salvo")
    void salvarTeste(){
        // Pega o quantidade de sorvetes que há na lista
        Integer productQuantity = servico.findAll().size();

        // Cria um sorvete, guarda na variável e depois o inseri para ser salvo
        Sorvete sorvete = SorveteCreator.criarSorvete();
        servico.save(sorvete);

        // Verifica se a lista aumentou de tamanho devido á nova inserção
        Assertions.assertThat(productQuantity)
                .isLessThan(servico.findAll().size());

        // Verifica se o sorvete criado acima está na lista de produtos salvos no banco de dados
        Assertions.assertThat(servico.findAll())
                .contains(sorvete);
    }

    @Test
    @DisplayName("Testa se o desconto está sendo aplicado em dias quentes")
    void calcularDescontoCasoEstejaQuenteTeste(){
        final double TEMPERATURA_ATUAL = 33.0,
                TEMPERATURA_MEDIA_ANO = 29.5;

        BDDMockito.when(sensorClimatico.capturarTemperaturaAtual())
                .thenReturn(TEMPERATURA_ATUAL);
        BDDMockito.when(sensorClimatico.capturarTemperaturaMediaAno())
                .thenReturn(TEMPERATURA_MEDIA_ANO);

        Sorvete sorvete = servico.findAll().stream().findAny().orElseThrow();
        final double PRECO_ORIGINAL = sorvete.getPreco();

        sorvete.aplicarDesconto(sensorClimatico);
        final double PRECO_COM_DESCONTO = sorvete.getPreco();

        Assertions.assertThat(PRECO_COM_DESCONTO)
                .isLessThan(PRECO_ORIGINAL);
    }

    @Test
    @DisplayName("Testa se o desconto está sendo aplicado em dias que não estão quentes")
    void calcularDescontoCasoNaoEstejaQuenteTeste(){
        final double TEMPERATURA_ATUAL = 22.5,
                TEMPERATURA_MEDIA_ANO = 29.5;

        BDDMockito.when(sensorClimatico.capturarTemperaturaAtual())
                .thenReturn(TEMPERATURA_ATUAL);

        BDDMockito.when(sensorClimatico.capturarTemperaturaMediaAno())
                .thenReturn(TEMPERATURA_MEDIA_ANO);

        Sorvete sorvete = servico.findAll().stream().findAny().orElseThrow();
        final double PRECO_ORIGINAL = sorvete.getPreco();

        sorvete.aplicarDesconto(sensorClimatico);
        final double PRECO_COM_DESCONTO = sorvete.getPreco();

        Assertions.assertThat(PRECO_COM_DESCONTO)
                .isEqualTo(PRECO_ORIGINAL);
    }
}
