package com.tutorialspoint.exception;

import com.tutorialspoint.response.Result;

public class ResultException extends RuntimeException {

    Result<Object> result;

    public ResultException(Result<Object> result){
        super(result.getMessage());
        this.result = result;
    }

    public Result<Object> getResult(){
        return result;
    }
}
