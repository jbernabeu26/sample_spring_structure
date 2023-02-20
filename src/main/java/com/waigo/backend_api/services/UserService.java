package com.waigo.backend_api.services;

import com.waigo.backend_api.user.domain.aggregate.CustomUser;

import java.util.List;

public interface UserService {
    // AQUI VAN DECLARADOS TODOS LOS METODOS QUE UTILIZAREMOS PARA LOS USUARIOS
    List<CustomUser> findAll();

    CustomUser addUser(CustomUser user);

}
