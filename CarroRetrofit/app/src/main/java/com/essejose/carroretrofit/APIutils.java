package com.essejose.carroretrofit;

/**
 * Created by essejose on 19/06/2017.
 */

public class APIutils {

    private APIutils() {}

    public static final String BASE_URL = "http://10.3.1.22:3000";

    public static CarroAPI getCarroAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(CarroAPI.class);
    }
}
