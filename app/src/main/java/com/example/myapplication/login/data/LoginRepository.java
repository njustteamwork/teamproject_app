package com.example.myapplication.login.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.login.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout(Context context) {
        user = null;
        dataSource.logout(context);
    }

    private void setLoggedInUser(LoggedInUser user, Context loginContext) {
        this.user = user;
        SharedPreferences userSP = loginContext.getSharedPreferences("userData", Context.MODE_PRIVATE);
        userSP.edit().clear().apply();
        userSP.edit().putString("name",user.getDisplayName())
                .putString("UUID",user.getUserId())
                .apply();
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password, Context loginContext) {
        // handle login
        Result<LoggedInUser> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData(),loginContext);
        }
        return result;
    }
}
