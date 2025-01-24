package com.fiap.fastfood.integration;

import com.fiap.fastfood.adapters.in.dto.CriarPedidoDTO;
import com.fiap.fastfood.adapters.in.dto.PedidoDTO;
import com.fiap.fastfood.core.application.PedidoQueueIntegration;
import com.fiap.fastfood.core.application.repository.ClienteRepository;
import com.fiap.fastfood.core.application.repository.PedidoRepository;
import com.fiap.fastfood.core.application.repository.ProdutoRepository;
import com.fiap.fastfood.core.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class FluxoDePedido2IT {

    public static final String CPF = LocalDateTime.now().toString();

    @LocalServerPort
    private int port;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoQueueIntegration pedidoQueueIntegration;

    @PersistenceContext
    private EntityManager em;

    List<Produto> produtos;

    @BeforeEach
    public void setUp() {
        String produtoUrl = "http://localhost:" + port + "/api/v1/produto/";

        produtos = List.of(
                new Produto("Hamburguer", "Pao, carne, ovo", 10.10, ProductCategory.LANCHE),
                new Produto("Batata", "Batata frita", 5.0, ProductCategory.ACOMPANHAMENTO),
                new Produto("Coca Zero", "Refrigerante de Cola", 12.0, ProductCategory.BEBIDA),
                new Produto("Casquinha", "Sorvete de baunilha", 2.50, ProductCategory.SOBREMESA));

        produtos.forEach(produto -> {
            produtoRepository.save(produto);
        });
    }

    @AfterEach
    public void tearDown() {
        em.createQuery("DELETE FROM PedidoQueue").executeUpdate();
        em.createQuery("DELETE FROM PedidoProduto").executeUpdate();
        em.createQuery("DELETE FROM Pedido").executeUpdate();
        em.createQuery("DELETE FROM Produto").executeUpdate();
        em.createQuery("DELETE FROM Cliente").executeUpdate();

        em.flush();
    }

    @Test
    void comClienteIdentificado() {
//        produtos = List.of(
//                new Produto("Hamburguer", "Pao, carne, ovo", 10.10, ProductCategory.LANCHE),
//                new Produto("Batata", "Batata frita", 5.0, ProductCategory.ACOMPANHAMENTO),
//                new Produto("Coca Zero", "Refrigerante de Cola", 12.0, ProductCategory.BEBIDA),
//                new Produto("Casquinha", "Sorvete de baunilha", 2.50, ProductCategory.SOBREMESA));
//
//                produtos.forEach(em::persist);
//        em.flush();
//
//        // 1. Cadastro de cliente
//        String clienteUrl = "http://localhost:" + port + "/api/v1/cliente/";
//        Cliente cliente = new Cliente(CPF, "email@email.com", "Nome do Cliente");
//
//        ResponseEntity<Cliente> resposta =  restTemplate.postForEntity(clienteUrl, cliente, Cliente.class);
//
//        assertThat(resposta.getStatusCode().value()).isEqualTo(201);
//        assertThat(clienteRepository.findByCpf(cliente.getCpf())).isNotNull();
//
//        // 2. Carregando os produtos
//        String produtoUrl = "http://localhost:" + port + "/api/v1/produto/";
//
//        ResponseEntity<List<Produto>> response = restTemplate.exchange(
//                produtoUrl + ProductCategory.LANCHE.name(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Produto>>() {}
//        );
//
//        // 3. Criando o pedido
//        String pedidoUrl = "http://localhost:" + port + "/api/v1/pedido/";
//        CriarPedidoDTO criarPedidoDTO = new CriarPedidoDTO();
//        criarPedidoDTO.setClienteCPF(CPF);
//        criarPedidoDTO.setProdutoIds(List.of(response.getBody().getFirst().getId()));
//
//        ResponseEntity<PedidoDTO> pedidoResponseEntity =  restTemplate.postForEntity(pedidoUrl, criarPedidoDTO, PedidoDTO.class);
//        PedidoDTO pedidoDTO = pedidoResponseEntity.getBody();
//
//        assertThat(pedidoResponseEntity.getStatusCode().value()).isEqualTo(200);
//        assertThat(pedidoDTO).isNotNull();
//        assertThat(pedidoDTO.getId()).isNotNull();
//
//        // 4. Conferindo se pedido foi pra fila
//
//        PedidoQueue itemDaFila = pedidoQueueIntegration.getItemByPedidoId(pedidoDTO.getId());
//        assertThat(itemDaFila).isNotNull();
//
//        // 5. Mudando status do pedido
//        restTemplate.put(pedidoUrl + "/%s/%s".formatted(pedidoDTO.getId(), PedidoStatus.EM_PREPARACAO), null);
//
//        // 6. Conferindo status do pedido
//        ResponseEntity<PedidoDTO> pedidoDTOUpdated = restTemplate.getForEntity(pedidoUrl + pedidoDTO.getId(), PedidoDTO.class);
//
//        assertThat(pedidoDTOUpdated.getBody()).isNotNull();
//        assertThat(pedidoDTOUpdated.getBody().getStatus()).isEqualTo(PedidoStatus.EM_PREPARACAO);
    }
}
