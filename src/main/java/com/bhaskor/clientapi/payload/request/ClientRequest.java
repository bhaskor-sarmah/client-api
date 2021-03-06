package com.bhaskor.clientapi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {
    
    private String firstName;
    private String mobileNumber;
    private String idNumber;

}
