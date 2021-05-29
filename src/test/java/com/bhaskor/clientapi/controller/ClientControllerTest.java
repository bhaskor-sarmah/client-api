package com.bhaskor.clientapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.bhaskor.clientapi.entity.ClientEntity;
import com.bhaskor.clientapi.payload.request.ClientRequest;
import com.bhaskor.clientapi.payload.response.JsonResponse;
import com.google.gson.Gson;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientControllerTest {

   /************************************************** */
   /******* INTEGRATION TEST OF THE REST APIs ******** */
   /************************************************** */

   @Autowired
   MockMvc mvc;

   @Test
   @Order(1)
   @DisplayName("Test - Creating a Client Integration Test")
   public void testCreateClient() throws Exception {
      ClientEntity client = new ClientEntity();
      // client.setId(1L);
      client.setFirstName("Bhaskor");
      client.setLastName("Sarmah");
      client.setIdNumber("840911 4567 658");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/createClient")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(200, result.getResponse().getStatus());
      assertTrue(res.isStatus());
      assertEquals("Client Saved Successfully", res.getMessage());

   }

   @Test
   @Order(2)
   @DisplayName("Test - Creating a Client with Invalid ID")
   public void testCreateClientInvalidIdNumber() throws Exception {
      ClientEntity client = new ClientEntity();
      // client.setId(1L);
      client.setFirstName("Bhaskor");
      client.setLastName("Sarmah");
      client.setIdNumber("840911 4567");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/createClient")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(400, result.getResponse().getStatus());
      assertFalse(res.isStatus());
      assertEquals("Data validation error", res.getMessage());

   }

   @Test
   @Order(3)
   @DisplayName("Test - Creating a Client with an ID Number Already Present")
   public void testCreateClientRepetedIdNumber() throws Exception {
      ClientEntity client = new ClientEntity();
      // client.setId(1L);
      client.setFirstName("Bhaskor");
      client.setLastName("Sarmah");
      client.setIdNumber("840911 4567 658");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/createClient")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(400, result.getResponse().getStatus());
      assertFalse(res.isStatus());
      assertEquals("Unique Constrain Violation : idNumber", res.getMessage());

   }

   @Test
   @Order(4)
   @DisplayName("Test - Searching a client by the National ID")
   public void searchClientByNationalId() throws Exception {
      ClientRequest clientReq = new ClientRequest();
      clientReq.setFirstName("");
      clientReq.setIdNumber("840911 4567 658");
      clientReq.setMobileNumber("");

      Gson gson = new Gson();
      String json = gson.toJson(clientReq);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/getClientDetails")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(200, result.getResponse().getStatus());
      assertTrue(res.isStatus());
      assertEquals("Data Fetch Successfully", res.getMessage());

   }

   @Test
   @Order(4)
   @DisplayName("Test - Searching a client by providing blank Data")
   public void searchClientWithBlankData() throws Exception {
      ClientRequest clientReq = new ClientRequest();
      clientReq.setFirstName("");
      clientReq.setIdNumber("");
      clientReq.setMobileNumber("");

      Gson gson = new Gson();
      String json = gson.toJson(clientReq);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/getClientDetails")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(400, result.getResponse().getStatus());
      assertFalse(res.isStatus());
      assertEquals("Please provide at least a firstName or IdNumber or Mobile", res.getMessage());

   }

   @Test
   @Order(4)
   @DisplayName("Test - Searching a client by providing the Name")
   public void searchClientByName() throws Exception {
      ClientRequest clientReq = new ClientRequest();
      clientReq.setFirstName("Bhaskor");
      clientReq.setIdNumber("");
      clientReq.setMobileNumber("");

      Gson gson = new Gson();
      String json = gson.toJson(clientReq);

      RequestBuilder request = MockMvcRequestBuilders.post("/api/1.0/getClientDetails")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(200, result.getResponse().getStatus());
      assertTrue(res.isStatus());
      assertEquals("Data Fetch Successfully", res.getMessage());
      
      // Test if we are getting a List Object instead of a Entity Object
      assertEquals(1, ((List<ClientEntity>)res.getPayload()).size());

   }

   @Test
   @Order(5)
   @DisplayName("Test - Update Client Details")
   public void updateClientDetails() throws Exception {
      ClientEntity client = new ClientEntity();
      client.setId(1L);
      client.setFirstName("Sarmah");
      client.setLastName("Bhaskor");
      client.setIdNumber("840911 4567 658");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.put("/api/1.0/updateClientDetails")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(200, result.getResponse().getStatus());
      assertTrue(res.isStatus());
      assertEquals("Client Updated Successfully", res.getMessage());
      
      ClientEntity clientEntity = gson.fromJson(gson.toJson(res.getPayload()), ClientEntity.class);

      // Test if we are getting updated name inside the Entity Object
      assertEquals("Sarmah", clientEntity.getFirstName());

   }

   @Test
   @Order(6)
   @DisplayName("Test - Try To Update Client Details Where ID Not Found")
   public void updateClientDetailsWithInvalidId() throws Exception {
      ClientEntity client = new ClientEntity();
      client.setId(10L);
      client.setFirstName("Sarmah");
      client.setLastName("Bhaskor");
      client.setIdNumber("840911 4567 658");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.put("/api/1.0/updateClientDetails")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(400, result.getResponse().getStatus());
      assertFalse(res.isStatus());
      assertEquals("Error Ocurred : Client not found with Id - 10", res.getMessage());

   }

   @Test
   @Order(7)
   @DisplayName("Test - Try to Update Client Details with no ID Provided")
   public void updateClientDetailsWithoutId() throws Exception {
      ClientEntity client = new ClientEntity();
      client.setFirstName("Sarmah");
      client.setLastName("Bhaskor");
      client.setIdNumber("840911 4567 658");
      client.setMobileNumber("+91-7002402636");
      client.setPhysicalAddress("My Address");

      Gson gson = new Gson();
      String json = gson.toJson(client);

      RequestBuilder request = MockMvcRequestBuilders.put("/api/1.0/updateClientDetails")
            .contentType(MediaType.APPLICATION_JSON).content(json);
      MvcResult result = mvc.perform(request).andReturn();

      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(400, result.getResponse().getStatus());
      assertFalse(res.isStatus());
      assertEquals("Error Ocurred : Client ID is required for updating the client", res.getMessage());

   }

   @Test
   @Order(8)
   @DisplayName("Test - Try to fetch all the client details")
   public void getAllClientDetails() throws Exception {

      RequestBuilder request = MockMvcRequestBuilders.get("/api/1.0/getAllClients");
      MvcResult result = mvc.perform(request).andReturn();

      Gson gson = new Gson();
      JsonResponse res = gson.fromJson(result.getResponse().getContentAsString(), JsonResponse.class);

      assertEquals(200, result.getResponse().getStatus());
      assertTrue(res.isStatus());
      assertEquals("Client Data Fetched Successfully", res.getMessage());
      
      // Test if we are getting a List Object instead of a Entity Object
      assertEquals(1, ((List<ClientEntity>)res.getPayload()).size());

   }

}