package com.bhaskor.clientapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.bhaskor.clientapi.entity.ClientEntity;
import com.bhaskor.clientapi.exception.BadRequestException;
import com.bhaskor.clientapi.payload.response.JsonResponse;
import com.bhaskor.clientapi.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0")
public class ClientController{

    @Autowired
    private ClientService clientService;

    @PostMapping("/createClient")
    public ResponseEntity<?> createNewClient(@Valid @RequestBody ClientEntity client, BindingResult result){
        // Data sanity check
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            List<String> message = new ArrayList<>();
            for (FieldError e : errors) {
                message.add("@" + e.getField() + " : " + e.getDefaultMessage());
            }
            return new ResponseEntity<JsonResponse>(new JsonResponse(false, message, "Data validation error"),
                    HttpStatus.BAD_REQUEST);
        }
        // Trying to save the client
        JsonResponse res = clientService.createClient(client);
        // if saved successfully
        if (res.isStatus()) {
            return ResponseEntity.ok(res);
        } else {
            throw new BadRequestException(res.getMessage());
        }
    }



}