package com.fiap.fastfood.core.application.repository;


import com.fiap.fastfood.core.domain.Cliente;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository {

    void save(Cliente cliente);

    Cliente findByCpf(String cpf);

    List<Cliente> findAll();

    void delete(String cpf);
}
