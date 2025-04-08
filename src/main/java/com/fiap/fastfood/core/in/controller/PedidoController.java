package com.fiap.fastfood.core.in.controller;

import java.util.List;

import com.fiap.fastfood.core.usecases.service.PagamentoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.fastfood.core.entities.Pedido;
import com.fiap.fastfood.core.in.dto.CriarPedidoDTO;
import com.fiap.fastfood.core.in.dto.PedidoDTO;
import com.fiap.fastfood.core.usecases.service.PedidoService;

@RestController
@RequestMapping("/api/v1/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/")
    public PedidoDTO createPedido(@RequestBody CriarPedidoDTO criarPedidoDTO) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        return pedidoDTO.fromEntity(pedidoService.criarPedido(criarPedidoDTO));
    }

    @PutMapping("/{id}/{status}")
    public PedidoDTO updateStatus(@PathVariable Long id, @PathVariable String status) {
        Pedido pedido = pedidoService.updateStatus(id, status);
        return new PedidoDTO().fromEntity(pedido);
    }

    @GetMapping("/{id}")
    public PedidoDTO getPedido(@PathVariable Long id) {
        return pedidoService.getPedido(id);
    }

    @GetMapping("/")
    public List<PedidoDTO> getPedidos(@RequestParam(value = "status", required = false) String status) {
        return pedidoService.getPedidos(status);
    }

    @GetMapping("/ordered")
    public List<PedidoDTO> getPedidosOrdered() {
        return pedidoService.getPedidosOrdered();
    }
}
