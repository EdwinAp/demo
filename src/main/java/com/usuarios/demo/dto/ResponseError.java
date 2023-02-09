package com.usuarios.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class ResponseError implements Serializable {

    private Integer codeError;
    private String messageError;
    private Object requestBodyExample;

}
