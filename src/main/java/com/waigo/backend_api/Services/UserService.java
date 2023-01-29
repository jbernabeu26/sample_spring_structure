package com.waigo.backend_api.Services;

import com.waigo.backend_api.Model.Entities.User;

import java.util.List;

public interface UserService {
    // AQUI VAN DECLARADOS TODOS LOS METODOS QUE UTILIZAREMOS PARA LOS USUARIOS
    List<User> findAll();

}
