package com.example.tomek.howlongapp.util;

import com.example.tomek.howlongapp.data.model.Restaurant;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/*
* This class is used to deserialize JSON response from Google
* Maps Place Details Service to Restaurant object.
* */

public class AppointmentDeserializer implements JsonDeserializer<Restaurant> {
    @Override
    public Restaurant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jObject = json.getAsJsonObject();
        if(jObject.get("status").getAsString().equals("OK")){
            JsonObject result = jObject.getAsJsonObject("result");
            String name = result.get("name").getAsString();
            String google_id = result.get("id").getAsString();
            String address = result.get("formatted_address").getAsString();
            String photo_reference = result.has("photos")? result.get("photos").getAsJsonArray().get(0).getAsJsonObject().get("photo_reference").getAsString() : "";
            return new Restaurant(name,address,google_id,photo_reference);
        } else{
            return null;
        }
    }
}
