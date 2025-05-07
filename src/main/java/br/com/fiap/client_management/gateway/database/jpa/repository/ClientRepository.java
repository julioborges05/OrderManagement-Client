package br.com.fiap.client_management.gateway.database.jpa.repository;

import br.com.fiap.client_management.gateway.database.jpa.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query(value = """
           select c.* from client c where c.cpf = :cpf and c.is_active = true
           """, nativeQuery = true)
    Optional<ClientEntity> findByCpf(String cpf);

    @Query(value = """
           select c.* from client c where c.id = :id and c.is_active = true
           """, nativeQuery = true)
    Optional<ClientEntity> findById(Long id);

    @Query(value = """
        select * from client c where c.id = :id
        """, nativeQuery = true)
    Optional<ClientEntity>  findClientById(Long id);

}
