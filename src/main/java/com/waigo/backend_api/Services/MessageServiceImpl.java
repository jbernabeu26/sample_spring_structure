package com.waigo.backend_api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waigo.backend_api.Model.Repositories.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{
    
    @Autowired
    MessageRepository messageRepository;
}
