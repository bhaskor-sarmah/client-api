package com.bhaskor.clientapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhaskor.clientapi.entity.ClientEntity;
import com.bhaskor.clientapi.payload.response.JsonResponse;
import com.google.gson.Gson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

   @Autowired
   MockMvc mvc;   

   @Test
   @DisplayName("Test - Creating a Client Integration Test")
   public void testCreateClient() throws Exception {
      ClientEntity client = new ClientEntity();
      client.setId(1L);
      client.setFirstName("Bhaskor");
      client.setLastName("Sarmah");
      client.setIdNumber("840911 4567 658");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/createClient").contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      System.out.println();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(200, result.getResponse().getStatus());
      assertTrue(res.isStatus());
      assertEquals("Client Saved Successfully",res.getMessage());
      
   }

   @Test
   public void testCreateClientInvalidIdNumber() throws Exception {
      ClientEntity client = new ClientEntity();
      client.setId(1L);
      client.setFirstName("Bhaskor");
      client.setLastName("Sarmah");
      client.setIdNumber("840911 4567");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/createClient").contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      System.out.println();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(400, result.getResponse().getStatus());
      assertFalse(res.isStatus());
      assertEquals("Data validation error",res.getMessage());
      
   }

}