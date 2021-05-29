package com.bhaskor.clientapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bhaskor.clientapi.entity.ClientEntity;
import com.bhaskor.clientapi.repository.ClientRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {

    @Mock
    private ClientRepository repository;

    @Autowired
    @InjectMocks
    private ClientService clientService;

    @Test
    @DisplayName("Test - Creating a Client")
    public void testAirlineCoveringMaximumCities() {
        // Verifying client creation
        ClientEntity client = new ClientEntity();
        client.setFirstName("Bhaskor");
        client.setLastName("Sarmah");
        client.setIdNumber("840911 4567 658");
        client.setMobileNumber("+91-7002402636");
        client.setPhysicalAddress("My Address");

        // Verify create client
        assertEquals("Client Saved Successfully",clientService.createClient(client).getMessage());
    }

    @Test
    public void doTestClientCreation(){

    }
    
}