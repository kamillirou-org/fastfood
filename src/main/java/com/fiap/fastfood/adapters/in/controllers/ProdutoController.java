package com.fiap.fastfood.adapters.in.controllers;

import com.fiap.fastfood.core.application.repository.ProdutoRepository;
import com.fiap.fastfood.core.domain.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto) {
        produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<Produto> getClientes() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{categoria}")
    public List<Produto> getProdutosByCategoria(@PathVariable String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public void deleteProduto(@PathVariable Long id) {
        produtoRepository.delete(id);
    }
}
