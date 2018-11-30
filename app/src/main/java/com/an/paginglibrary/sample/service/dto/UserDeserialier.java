package com.an.paginglibrary.sample.service.dto;

import com.an.paginglibrary.sample.notification.NotificationDTO;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**UserDeserialier
 *
 * Created by hungdn on 8/31/2017.
 */

public class UserDeserialier implements JsonDeserializer<ResponseDTO> {
  @Override
  public ResponseDTO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject jsonObject = json.getAsJsonObject();

    ResponseDTO responseDTO = new ResponseDTO<>();

    if (jsonObject.get("success").getAsString().equalsIgnoreCase("1")) {
      responseDTO.setSuccess(true);
    } else if (jsonObject.get("success").getAsString().equalsIgnoreCase("0")) {
      responseDTO.setSuccess(false);
    } else {
      responseDTO.setSuccess(jsonObject.get("success").getAsBoolean());
    }

    if (jsonObject.get("message") != null) {
      responseDTO.setMessage(jsonObject.get("message").getAsString());
    }
    if (jsonObject.get("token") != null) {
      responseDTO.setToken(jsonObject.get("token").getAsString());
    }
    if (jsonObject.get("fullname") != null) {
      responseDTO.setFullName(jsonObject.get("fullname").getAsString());
    }
    if (jsonObject.get("username") != null) {
      responseDTO.setUserName(jsonObject.get("username").getAsString());
    }
    if (jsonObject.get("count") != null) {
      responseDTO.setCount(jsonObject.get("count").getAsInt());
    }

    if (jsonObject.get("data") != null) {
      TypeToken<List<NotificationDTO>> token = new TypeToken<List<NotificationDTO>>(){};
      List<NotificationDTO> notificationDTOS = new Gson().fromJson(jsonObject.getAsJsonArray("data"), token.getType());
      responseDTO.setData(notificationDTOS);
    }

    return responseDTO;
  }
}
