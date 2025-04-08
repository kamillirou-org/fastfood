package com.fiap.fastfood.core.usecases.service;

import com.fiap.fastfood.core.entities.Pagamento;
import jakarta.security.auth.message.AuthException;
import org.springframework.stereotype.Service;

@Service
public interface PagamentoService {

    Pagamento getPayment(Long idPedido);

    Pagamento initPayment(Long idPedido, String paymentType);

    Pagamento confirmPayment(Long idPedido, String paymentAuth) throws AuthException;
}
