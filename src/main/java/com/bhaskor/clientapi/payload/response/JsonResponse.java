package com.bhaskor.clientapi.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse {
    
    private boolean status;

    private Object payload;

    private String message;

}
