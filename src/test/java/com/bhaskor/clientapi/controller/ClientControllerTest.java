package com.bhaskor.clientapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.bhaskor.clientapi.entity.ClientEntity;
import com.bhaskor.clientapi.payload.response.JsonResponse;
import com.bhaskor.clientapi.repository.ClientRepository;
import com.bhaskor.clientapi.service.ClientService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

   @Autowired
   @InjectMocks
   ClientService clientService;

   @Autowired
   ClientController clientController;

   @Mock
   ClientRepository clientRepository;

   @Test
   public void testCreateClient() {
      ClientEntity client = new ClientEntity();
      client.setId(1L);
      client.setFirstName("Bhaskor");
      client.setLastName("Sarmah");
      client.setIdNumber("840911 4567 658");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      MockHttpServletRequest request = new MockHttpServletRequest();
      RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

      when(clientRepository.save(any(ClientEntity.class))).thenReturn(client);
      BindingResult result = mock(BindingResult.class);

      assertThat(clientController).isNotNull();

      ResponseEntity<JsonResponse> responseEntity = (ResponseEntity<JsonResponse>) clientController.createNewClient(client, result);

      assertEquals(200, responseEntity.getStatusCodeValue());
      // System.out.println(responseEntity.getBody());
      assertTrue(responseEntity.getBody().isStatus());
   }
}