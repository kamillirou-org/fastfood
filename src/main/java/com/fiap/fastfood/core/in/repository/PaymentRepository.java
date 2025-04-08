package com.fiap.fastfood.core.in.repository;

import com.fiap.fastfood.core.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findByPedidoId(Long idPedido);
}
