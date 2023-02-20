package com.waigo.backend_api.services;

import com.waigo.backend_api.user.domain.aggregate.CustomUser;
import com.waigo.backend_api.common.utils.TranslatorExceptions;
import com.waigo.backend_api.common.utils.WException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.user.infrastructure.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TranslatorExceptions translatorExceptions;

    public UserServiceImpl(UserRepository userRepository, TranslatorExceptions injectedTranslatorException) {
        this.userRepository = userRepository;
        this.translatorExceptions = injectedTranslatorException;
    }


    public List<CustomUser> findAll() {
        return (List<CustomUser>) userRepository.findAll();
    }


    public CustomUser addUser(CustomUser user) {
        CustomUser userAdded;

        try {
            userAdded = userRepository.save(user);
        } catch (ConstraintViolationException exception) {
            String codeError = exception.getConstraintViolations().iterator().next().getMessage();
            String messageError = translatorExceptions.translateExceptionMessage(codeError);
            throw new WException(messageError);
        } catch (Exception exception) {
            throw new WException("Failed to create user");
        }

        return userAdded;
    }

}
