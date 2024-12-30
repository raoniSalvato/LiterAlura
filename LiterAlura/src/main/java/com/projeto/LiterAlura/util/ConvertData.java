package com.projeto.LiterAlura.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ConvertData implements IConvertData {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getFirstObject(String json, String rootKey, Class<T> clazz) {
        try {
            JsonNode rootNode = mapper.readTree(json);
            JsonNode firstItem = rootNode.get(rootKey).get(0);
            return mapper.treeToValue(firstItem, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON para objeto: " + e.getMessage(), e);
        }
    }
}