package com.example.demo.plugins.message.response;

import com.example.demo.plugins.model.enumtype.LocaleTypeEnum;
import com.example.demo.plugins.model.response.ResponseCode;
import com.example.demo.plugins.model.response.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


/**
 * @author: rock
 * @date: 2018/7/24 15:18
 * @description:
 */
@Component
public class ResponsesBuilder {

    @Autowired
    private MessageSource messageSource;



    public Responses success() {
        return new Responses(ResponseCode.SUCCESS,
                messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + ResponseCode.SUCCESS, new Object[] {},
                        LocaleContextHolder.getLocale()));
    }

    public <T> Responses success(T data) {
        return new Responses(ResponseCode.SUCCESS, data,
                messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + ResponseCode.SUCCESS, new Object[] {},
                        LocaleContextHolder.getLocale()));
    }

    public Responses systemError() {
        return new Responses(ResponseCode.SYSTEM_ERROR,
                messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + ResponseCode.SYSTEM_ERROR,
                        new Object[] {}, LocaleContextHolder.getLocale()));
    }

    /**
     * 填充带错误参数的返回对象
     *
     * @param code  错误码
     * @return 返回对象
     */
    public Responses fullCodeError(int code) {
        return new Responses(code, messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + code, new Object[] {},
                LocaleContextHolder.getLocale()));
    }

    /**
     * 填充带错误参数的返回对象,并带有返回数据
     *
     * @param code  错误码
     * @return 返回对象
     */
    public <T> Responses fullCodeError(int code, T data) {
        return new Responses(code, data, messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + code,
                new Object[] {}, LocaleContextHolder.getLocale()));
    }

    /**
     * 填充带错误参数,并添加相应的DESC在错误描述后面
     *
     * @param code  错误码
     * @return 返回对象
     */
    public  Responses fullCodeDescError(int code, String desc) {
        return new Responses(code, null, messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + code,
                new Object[] {}, LocaleContextHolder.getLocale()) + "  " + desc);
    }

    public Responses fullCodeArrError(int code, Object[] arr) {
        return new Responses(code, null, messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + code,
                arr, LocaleContextHolder.getLocale()));
    }

    public <T>  Responses fullCodeArrError(int code,T data, Object[] arr) {
        return new Responses(code, data, messageSource.getMessage(LocaleTypeEnum.SYS_CODE.getValue() + code,
                arr, LocaleContextHolder.getLocale()));
    }
}
