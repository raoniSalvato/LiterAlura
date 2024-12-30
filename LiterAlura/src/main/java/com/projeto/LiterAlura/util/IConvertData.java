package com.projeto.LiterAlura.util;

import org.springframework.stereotype.Component;

@Component
public interface IConvertData {

    <T> T getData(String json, Class<T> tClass);

    <T> T getFirstObject(String json, String rootKey, Class<T> clazz);
}