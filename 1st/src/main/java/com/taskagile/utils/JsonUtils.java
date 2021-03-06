package com.taskagile.utils;

import java.io.IOException;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtils {

  private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

  public static String toJson(Object object) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.writeValueAsString(object);
    } catch(JsonProcessingException e) {
      log.error("Failed to convert object to json", e);
      return null;
    }
  }

  public static <T> T toObject(String json, Class<T> clazz) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.readValue(json, clazz);
    } catch (IOException e) {
      log.error("Failed to convert string `" + json + "` class `" + clazz.getName() + "`", e);
      return null;
    }
  }

  public static void write(Writer writer, Object value) throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(writer, value);
  }
}
