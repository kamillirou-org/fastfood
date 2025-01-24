package com.fiap.fastfood.adapters.in.controllers;

import com.fiap.fastfood.core.application.repository.ClienteRepository;
import com.fiap.fastfood.core.domain.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> saveClient(@RequestBody Cliente cliente) {
        clienteRepository.save(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{cpf}")
    public Cliente getClientesByCpf(@PathVariable String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

}
