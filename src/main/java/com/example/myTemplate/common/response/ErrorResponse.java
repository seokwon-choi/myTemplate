package com.example.myTemplate.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ErrorResponse {

    private String statusCode;
    private String requestUrl;
    private String message;
    private String resultCode;
    private List<Error> errorList;


}
