package com.waigo.backend_api.category.application.dto;


import lombok.Builder;
import org.springframework.stereotype.Component;


public record AddCategoryRequest(int id, String name) {

}
