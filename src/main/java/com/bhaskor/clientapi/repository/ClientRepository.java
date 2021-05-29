package com.bhaskor.clientapi.repository;

import java.util.List;
import java.util.Optional;

import com.bhaskor.clientapi.entity.ClientEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Long>{

    Optional<ClientEntity> findByIdNumber(String idNumber);
    
    Optional<ClientEntity> findByMobileNumber(String mobileNumber);
    
    List<ClientEntity> findByFirstName(String firstName);
    
}
