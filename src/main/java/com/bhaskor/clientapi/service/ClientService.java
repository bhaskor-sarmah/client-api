package com.bhaskor.clientapi.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bhaskor.clientapi.entity.ClientEntity;
import com.bhaskor.clientapi.payload.request.ClientRequest;
import com.bhaskor.clientapi.payload.response.JsonResponse;
import com.bhaskor.clientapi.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public JsonResponse createClient(ClientEntity client) {
        JsonResponse res = new JsonResponse();

        client = repository.save(client);

        if (client == null) {
            res.setMessage("Failed Saving Client Data");
            res.setStatus(false);
            return res;
        }

        res.setMessage("Client Saved Successfully");
        res.setPayload(client);
        res.setStatus(true);
        return res;
    }

    public JsonResponse getClient(@Valid ClientRequest client) {
        JsonResponse res = new JsonResponse();

        // The Priority Defined here are ID > FIRST_NAME > MOBILE_NUMBER
        if (client.getIdNumber() != null && !client.getIdNumber().trim().equals("")) {
            // Id is provided

            // Using Optional because maximum one record can be found since mobile number is unique
            Optional<ClientEntity> clientOptional = repository.findByIdNumber(client.getIdNumber());

            if(clientOptional.isPresent()){
                res.setPayload(clientOptional.get());
                res.setStatus(true);
                res.setMessage("Data Fetch Successfully");
            }else{
                res.setStatus(false);
                res.setMessage("No Data Found for the Given National ID");
            }

        } else if (client.getFirstName() != null && !client.getFirstName().trim().equals("")) {
            // First Name is provided
            
            // Using List since multiple client can have the same first name
            List<ClientEntity> clientList = repository.findByFirstName(client.getFirstName());
            
            if(clientList!= null && !clientList.isEmpty()){
                res.setPayload(clientList);
                res.setStatus(true);
                res.setMessage("Data Fetch Successfully");
            }else{
                res.setStatus(false);
                res.setMessage("No Data Found for the Given First Name");
            }
        } else if (client.getMobileNumber() != null && !client.getMobileNumber().trim().equals("")) {
            // Mobile Number is provided

            // Using Optional because maximum one record can be found since mobile number is unique
            Optional<ClientEntity> clientOptional = repository.findByMobileNumber(client.getMobileNumber());
            if(clientOptional.isPresent()){
                res.setPayload(clientOptional.get());
                res.setStatus(true);
                res.setMessage("Data Fetch Successfully");
            }else{
                res.setStatus(false);
                res.setMessage("No Data Found for the Given Mobile Number");
            }
        }

        return res;
    }

}
