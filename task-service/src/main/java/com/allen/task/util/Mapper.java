package com.allen.task.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

public class Mapper {
    private Mapper() {
        throw new IllegalThreadStateException("No se puede instanciar un utilitario");
    }

    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
