package com.waigo.backend_api.category.application.dto;

import org.springframework.stereotype.Component;
@Component
public class AddCategoryRequest {
    private String nombre;
    private int idDto;

    public AddCategoryRequest(String nombre, int idDto) {
        this.nombre = nombre;
        this.idDto = idDto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdDto() {
        return idDto;
    }

    public void setIdDto(int idDto) {
        this.idDto = idDto;
    }
}
