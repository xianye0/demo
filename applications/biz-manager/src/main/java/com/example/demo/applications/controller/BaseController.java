package com.example.demo.applications.controller;

import com.example.demo.applications.exception.ResponseException;
import com.example.demo.plugins.message.response.ResponsesBuilder;
import com.example.demo.plugins.model.response.ResponseCode;
import com.example.demo.plugins.model.response.Responses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: rock
 * @date: 2019/1/30
 * @Description:
 */
@Slf4j
public class BaseController {
    @Autowired
    ResponsesBuilder responsesBuilder;


    @ExceptionHandler(Exception.class)
    public Responses handleException(Exception e) {
        log.error("request handler exception is : ", e);
        if (e.getClass() == ResponseException.class) {
            ResponseException re = (ResponseException) e;
            if (re.getArr() != null) {
                return responsesBuilder.fullCodeArrError(re.getCode(), re.getArr());
            } else if (re.getDesc() != null) {
                return responsesBuilder.fullCodeDescError(re.getCode(), re.getDesc());
            } else {
                return responsesBuilder.fullCodeError(((ResponseException) e).getCode(),((ResponseException) e).getData());
            }
        }

        if (e.getClass() == IllegalArgumentException.class) {
            return responsesBuilder.fullCodeError(ResponseCode.PARAM_INVALID);
        }

        return responsesBuilder.systemError();
    }
}
