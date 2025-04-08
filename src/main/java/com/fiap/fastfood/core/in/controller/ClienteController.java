package com.fiap.fastfood.core.in.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.fastfood.core.entities.Cliente;
import com.fiap.fastfood.core.in.repository.ClienteRepository;

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
