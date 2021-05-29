package com.bhaskor.clientapi.repository;

import com.bhaskor.clientapi.entity.ClientEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long>{
    
}
