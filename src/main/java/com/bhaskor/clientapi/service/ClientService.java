package com.bhaskor.clientapi.service;

import com.bhaskor.clientapi.entity.ClientEntity;
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

        if(client == null){
            res.setMessage("Failed Saving Client Data");
            res.setStatus(false);
            return res;
        }

        res.setMessage("Client Saved Successfully");
        res.setPayload(client);
        res.setStatus(true);
        return res;
    }
    


}
