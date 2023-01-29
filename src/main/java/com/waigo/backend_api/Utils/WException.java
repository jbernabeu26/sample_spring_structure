package com.waigo.backend_api.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class WException extends RuntimeException{
    public WException(String code, String errorMessage, Throwable err){
        super(code+"-"+errorMessage,err);
    }

    public WException(String code, String errorMessage){
        super(code+"-"+errorMessage);
    }


}
