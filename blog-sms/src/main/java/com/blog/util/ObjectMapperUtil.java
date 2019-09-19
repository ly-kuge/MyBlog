package com.blog.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String objectToString(T t) {
        if (t == null) {
            return null;
        } else {
            try {
                return t.getClass().equals(String.class) ? (String) t : objectMapper.writeValueAsString(t);
            } catch (JsonProcessingException e) {
                return null;
            }
        }
    }


    public static <T> T stringToObject(String s, Class<T> clazz) {
        if (s == null) {
            return null;
        } else {
            try {
                return clazz.getName().equals(String.class.getName()) ? (T) s : objectMapper.readValue(s, clazz);
            } catch (Exception e) {
                return null;
            }
        }

    }

}
