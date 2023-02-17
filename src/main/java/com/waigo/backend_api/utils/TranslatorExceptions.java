package com.waigo.backend_api.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Qualifier("messageSource")
public class TranslatorExceptions {


    private final MessageSource messageSource;

    public Map<String, Map<String, Set<String>>> translateExceptionMessages(List<String> codes) {
        return codes.stream()
                .map(errors -> errors.split("\\.", 3))
                .collect(Collectors.groupingBy(
                        entity -> entity[0],
                        Collectors.groupingBy(
                                field -> field[1],
                                Collectors.mapping(error -> messageSource.getMessage(error[0] + "." + error[1] + "." + error[2], null, LocaleContextHolder.getLocale()), Collectors.toSet())
                        )
                ));


    }

    public String translateExceptionMessage(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }
}
