package com.fiap.fastfood.core.usecases.service.impl;

import com.fiap.fastfood.core.entities.Pagamento;
import com.fiap.fastfood.core.entities.Pedido;
import com.fiap.fastfood.core.entities.PedidoStatus;
import com.fiap.fastfood.core.in.repository.PaymentRepository;
import com.fiap.fastfood.core.in.repository.PedidoRepository;
import com.fiap.fastfood.core.usecases.service.PagamentoService;
import com.fiap.fastfood.core.usecases.service.PedidoService;
import jakarta.security.auth.message.AuthException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    private final PaymentRepository paymentRepository;

    private final PedidoRepository pedidoRepository;

    private final PedidoService pedidoService;

    public PagamentoServiceImpl(PaymentRepository paymentRepository, PedidoRepository pedidoRepository, PedidoService pedidoService) {
        this.paymentRepository = paymentRepository;
        this.pedidoRepository = pedidoRepository;
        this.pedidoService = pedidoService;
    }

    @Override
    public Pagamento getPayment(Long idPedido) {
        return null;
    }

    @Override
    public Pagamento initPayment(Long idPedido, String paymentType) {
        Pedido pedido = Optional.of(pedidoRepository.find(idPedido)).orElseThrow(IllegalArgumentException::new);

        validarCriacaoPagamento(pedido);
        String pagamentoAuth = UUID.randomUUID().toString();

        Pagamento pagamento = new Pagamento();
        pagamento.setMeioPagamento(paymentType);
        pagamento.setStatus("PENDENTE");
        pagamento.setPedidoId(idPedido);
        pagamento.setPagamentoAuth(pagamentoAuth);

        return paymentRepository.save(pagamento);
    }

    @Override
    @Transactional
    public Pagamento confirmPayment(Long idPedido, String paymentAuth) throws AuthException {
        Pagamento pagamento = paymentRepository.findByPedidoId(idPedido).orElseThrow(IllegalArgumentException::new);
        if(pagamento.getPagamentoAuth().equals(paymentAuth)) {
            pagamento.setStatus("CONFIRMADO");
            pedidoService.pagamentoConfirmadoListener(idPedido);
        } else {
            throw new AuthException("Não autenticado");
        }

        return paymentRepository.save(pagamento);
    }

    public void validarCriacaoPagamento(Pedido pedido) {
        if (pedido.getStatus() != PedidoStatus.ESPERANDO_PAGAMENTO) {
            throw new IllegalStateException("Pedido não está no status correto.");
        }

        if (pagamentoJaExisteParaPedido(pedido.getId())) {
            throw new IllegalStateException("Pagamento já existe.");
        }
    }

    private boolean pagamentoJaExisteParaPedido(Long idPedido) {
        return paymentRepository.findByPedidoId(idPedido).isPresent();
    }
}
