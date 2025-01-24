package com.fiap.fastfood.adapters.out.infra.repository;

import com.fiap.fastfood.core.application.repository.ClienteRepository;
import com.fiap.fastfood.core.domain.Cliente;
import com.fiap.fastfood.core.domain.ProductCategory;
import com.fiap.fastfood.core.domain.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Cliente cliente) {
        if(cliente != null) {
            entityManager.persist(cliente);
        }

    }

    @Override
    public Cliente findByCpf(String cpf) {
        return entityManager.find(Cliente.class, cpf);
    }

    @Override
    public List<Cliente> findAll() {
        return entityManager.createQuery("select c from Cliente c", Cliente.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(String cpf) {
        String jpql = "DELETE FROM Cliente c WHERE c.cpf = :cpf";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("cpf", cpf);

        query.executeUpdate();
    }
}
