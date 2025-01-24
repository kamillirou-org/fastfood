package com.fiap.fastfood.adapters.in.controllers;

import com.fiap.fastfood.adapters.in.dto.CriarPedidoDTO;
import com.fiap.fastfood.adapters.in.dto.PedidoDTO;
import com.fiap.fastfood.core.application.service.PedidoService;
import com.fiap.fastfood.core.domain.Pedido;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
