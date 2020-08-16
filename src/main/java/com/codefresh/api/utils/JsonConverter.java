package com.codefresh.api.utils;

import com.google.gson.Gson;
import java.lang.reflect.Type;

public class JsonConverter {


  /**
   * Converts class object to json
   *
   * @param json the data json
   * @param classOf the class that we want to convert
   * @return generated object
   */
  public static <T> String convertObjectToJson(T json, Type classOf) {
    Gson gson = new Gson();
    return gson.toJson(json, classOf);
  }
}