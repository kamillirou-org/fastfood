package com.fiap.fastfood.core.in.controller;

import com.fiap.fastfood.core.entities.Pagamento;
import com.fiap.fastfood.core.usecases.service.PagamentoService;
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/pedido/{idPedido}")
    public Pagamento getPedido(@PathVariable Long idPedido) {
        return pagamentoService.getPayment(idPedido);
    }

    @PostMapping("/initPayment/{idPedido}/{paymentType}")
    public Pagamento initPayment(@PathVariable Long idPedido, @PathVariable String paymentType) {
        return pagamentoService.initPayment(idPedido, paymentType);
    }

    @PostMapping("/confirmPayment/{idPedido}")
    public Pagamento confirmPayment(@PathVariable Long idPedido, @RequestBody String paymentAuth) throws AuthException {
        return pagamentoService.confirmPayment(idPedido, paymentAuth);
    }

}
