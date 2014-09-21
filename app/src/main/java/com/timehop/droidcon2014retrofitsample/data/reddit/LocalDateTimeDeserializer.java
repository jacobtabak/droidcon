package com.timehop.droidcon2014retrofitsample.data.reddit;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.joda.time.LocalDateTime;

import java.lang.reflect.Type;

public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
  @Override
  public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return new LocalDateTime(json.getAsLong() * 1000);
  }
}
