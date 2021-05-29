package com.bhaskor.clientapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bhaskor.clientapi.entity.ClientEntity;
import com.bhaskor.clientapi.payload.request.ClientRequest;
import com.bhaskor.clientapi.repository.ClientRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceTest {
    /*********************************************** */
    /******* UNIT TEST OF THE CLIENT SERVICE ******* */
    /*********************************************** */

    @Mock
    private ClientRepository repository;

    @Autowired
    @InjectMocks
    private ClientService clientService;

    @Test
    @Order(1)
    @DisplayName("Test - Creating a Client")
    public void doTestClientCreation() {
        // Verifying client creation
        ClientEntity client = new ClientEntity();
        client.setFirstName("Bhaskor");
        client.setLastName("Sarmah");
        client.setIdNumber("840911 4567 658");
        client.setMobileNumber("+91-7002402636");
        client.setPhysicalAddress("My Address");

        // Verify create client
        assertEquals("Client Saved Successfully", clientService.createClient(client).getMessage());
    }

    @Test
    @Order(2)
    @DisplayName("Test - Searching a Client By National ID")
    public void searchClientByNationalId() {

        // Verifying client search
        ClientRequest clientReq = new ClientRequest();
        clientReq.setFirstName("");
        clientReq.setIdNumber("840911 4567 658");
        clientReq.setMobileNumber("");

        // Verify search client
        assertEquals("Data Fetch Successfully", clientService.getClient(clientReq).getMessage());
    }

    @Test
    @Order(3)
    @DisplayName("Test - Update Client")
    public void updateClient() {

        // Verifying client update
        ClientEntity client = new ClientEntity();
        client.setId(1L);
        client.setFirstName("Sarmah");
        client.setLastName("Bhaskor");
        client.setIdNumber("840911 4567 658");
        client.setMobileNumber("+91-7002402636");
        client.setPhysicalAddress("My Address");

        // Verify update client
        assertEquals("Client Updated Successfully", clientService.updateClient(client).getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("Test - Update Client with ID not present in the DB")
    public void updateClientInvalidId() {

        // Verifying client update with a ID that is not available
        ClientEntity client = new ClientEntity();
        client.setId(10L);
        client.setFirstName("Sarmah");
        client.setLastName("Bhaskor");
        client.setIdNumber("840911 4567 658");
        client.setMobileNumber("+91-7002402636");
        client.setPhysicalAddress("My Address");

        // Verify update client
        assertEquals("Client not found with Id - 10", clientService.updateClient(client).getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("Test - Fetch All Clients")
    public void getAllClients() {
        // Verify fetch all clients
        assertEquals("Client Data Fetched Successfully", clientService.getAllClients().getMessage());
    }

}