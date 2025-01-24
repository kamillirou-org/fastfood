package com.fiap.fastfood.integration.repository;

import com.fiap.fastfood.adapters.out.infra.repository.PedidoRepositoryImpl;
import com.fiap.fastfood.core.application.repository.PedidoRepository;
import com.fiap.fastfood.core.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PedidoRepositoryIT {
//
//    @Autowired
//    PedidoRepository pedidoRepository;
//
//    @Autowired
//    TestEntityManager entityManager;
//
//    private Produto produto;
//
//    private Cliente cliente;
//
//    @BeforeEach
//    void setUp() {
//        produto = new Produto("Hamburguer", "Pão, carne, ovo", 10.10, ProductCategory.LANCHE);
//        cliente = new Cliente("12345", "Email", "Nome");
//        entityManager.persist(produto);
//        entityManager.persist(cliente);
//    }
//
//    @Test
//    void deveSalvarEPersistirPedido() {
//        // Arrange
//        Pedido pedido = new Pedido();
//
//        PedidoProduto pedidoProduto = new PedidoProduto();
//        pedidoProduto.setProduto(produto);
//        pedidoProduto.setPedido(pedido);
//
//        pedido.setPedidoProduto(Set.of(pedidoProduto));
//        pedido.setCliente(cliente);
//
//        // Act
//        Pedido pedidoSalvo = pedidoRepository.save(pedido);
//
//        // Assert
//        assertThat(pedidoSalvo.getId()).isNotNull();
//        Pedido pedidoEncontrado = entityManager.find(Pedido.class, pedidoSalvo.getId());
//        assertThat(pedidoEncontrado).isNotNull();
//        assertThat(pedidoEncontrado.getStatus()).isEqualTo(PedidoStatus.RECEBIDO);
//    }
//
//    @Test
//    void deveEncontrarPedidoPorId() {
//        // Arrange
//        Pedido pedido = new Pedido();
//        pedido.setDataCriacao(LocalDateTime.now());
//        pedido.setStatus(PedidoStatus.EM_PREPARACAO);
//        pedido.setProdutos(List.of(produto));
//        entityManager.persist(pedido);
//
//        // Act
//        Pedido pedidoEncontrado = pedidoRepository.find(pedido.getId());
//
//        // Assert
//        assertThat(pedidoEncontrado).isNotNull();
//        assertThat(pedidoEncontrado.getId()).isEqualTo(pedido.getId());
//        assertThat(pedidoEncontrado.getStatus()).isEqualTo(PedidoStatus.EM_PREPARACAO);
//    }
//
//    @Test
//    void deveListarTodosOsPedidos() {
//        // Arrange
//        Pedido pedido1 = new Pedido();
//        pedido1.setDataCriacao(LocalDateTime.now());
//        pedido1.setStatus(PedidoStatus.FINALIZADO);
//        pedido1.setProdutos(List.of(produto));
//        entityManager.persist(pedido1);
//
//        Pedido pedido2 = new Pedido();
//        pedido2.setDataCriacao(LocalDateTime.now());
//        pedido2.setStatus(PedidoStatus.AGUARDANDO);
//        pedido2.setProdutos(List.of(produto));
//        entityManager.persist(pedido2);
//
//        // Act
//        List<Pedido> pedidos = pedidoRepository.list();
//
//        // Assert
//        assertThat(pedidos).hasSize(2);
//    }
//
//    @Test
//    void deveListarPedidosPorStatus() {
//        // Arrange
//        Pedido pedido1 = new Pedido();
//        pedido1.setDataCriacao(LocalDateTime.now());
//        pedido1.setStatus(PedidoStatus.AGUARDANDO);
//        pedido1.setProdutos(List.of(produto));
//        entityManager.persist(pedido1);
//
//        Pedido pedido2 = new Pedido();
//        pedido2.setDataCriacao(LocalDateTime.now());
//        pedido2.setStatus(PedidoStatus.FINALIZADO);
//        pedido2.setProdutos(List.of(produto));
//        entityManager.persist(pedido2);
//
//        // Act
//        List<Pedido> pedidosAguardando = pedidoRepository.list("AGUARDANDO");
//        List<Pedido> pedidosFinalizados = pedidoRepository.list("FINALIZADO");
//
//        // Assert
//        assertThat(pedidosAguardando).hasSize(1);
//        assertThat(pedidosAguardando.get(0).getStatus()).isEqualTo(PedidoStatus.AGUARDANDO);
//
//        assertThat(pedidosFinalizados).hasSize(1);
//        assertThat(pedidosFinalizados.get(0).getStatus()).isEqualTo(PedidoStatus.FINALIZADO);
//    }
}