package com.waigo.backend_api.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TranslatorExceptions {

    @Autowired
    @Qualifier("messageSource")
    MessageSource messageSource;
    public List<String> translateExceptionMessages(List<String> codes){
        List<String> errors = new ArrayList<>();
        for(String messageCode: codes){
            String code = messageCode.split(": ")[1];
            String error = messageSource.getMessage(code,null, LocaleContextHolder.getLocale());
            errors.add(error);
        }

        return errors;
    }
}
