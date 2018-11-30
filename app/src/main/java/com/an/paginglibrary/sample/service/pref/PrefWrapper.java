package com.an.paginglibrary.sample.service.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.an.paginglibrary.sample.service.dto.UserDTO;
import com.google.gson.Gson;

/**
 * Shared Preferences wrapper
 * Created by hungdn on 23/8/2017.
 */

public class PrefWrapper {
  private static PrefWrapper sInstance;

  public static final String MY_PREFERENCES = "Pref";
  public static final String KEY_USER = "user";

  private static Context mContext;

  private PrefWrapper(Context context) {
    mContext = context;
  }

  public static SharedPreferences getPreference() {
    return mContext.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
  }

  public static void init(Context context) {
    sInstance = new PrefWrapper(context);
  }

  public static PrefWrapper getInstance() {
    return sInstance;
  }

  /**
   * Save User as json
   */
  public void saveUser(String userSession) {
    SharedPreferences.Editor editor = getPreference().edit();
    editor.putString(KEY_USER, userSession);
    editor.commit();
  }

  /**
   * Get User from saved json
   */
  public UserDTO getUser() {
    String userJson = getPreference().getString(KEY_USER, null);
    if (userJson == null) {
      return null;
    }

    return new Gson().fromJson(userJson, UserDTO.class);
  }

  /**
   * Remove setting by {@code key}
   */
  public void remove(String key) {
    getPreference().edit()
            .remove(key)
            .apply();
  }

}
