package com.example.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        /*
        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
         */
        try{
            if(username.equals("test@test.com") && password.equals("testtest")){
                LoggedInUser testUser = new LoggedInUser(java.util.UUID.randomUUID().toString(),username);
                return new Result.Success<>(testUser);
            }
            else throw new Exception();
        }catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout(Context context) {
        SharedPreferences userSP = context.getSharedPreferences("userData", Context.MODE_PRIVATE);
        userSP.edit().clear().apply();
        // TODO: revoke authentication
    }
}
