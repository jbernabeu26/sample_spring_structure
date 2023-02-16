package com.waigo.backend_api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TranslatorExceptions {

    @Autowired
    @Qualifier("messageSource")
    MessageSource messageSource;
    public Map<String, Map<String, Set<String>>> translateExceptionMessages(List<String> codes){

        Map<String, Map<String, Set<String>>> errorsMap = codes.stream()
                .map(errors -> errors.split("\\.",3))
                .collect(Collectors.groupingBy(
                        entity -> entity[0],
                        Collectors.groupingBy(
                                field -> field[1],
                                Collectors.mapping(error -> messageSource.getMessage(error[0]+"."+error[1]+"."+error[2],null, LocaleContextHolder.getLocale()), Collectors.toSet())
                        )
                ));


        return errorsMap;
    }

    public String translateExceptionMessage(String code){
        return messageSource.getMessage(code,null, LocaleContextHolder.getLocale());
    }
}
