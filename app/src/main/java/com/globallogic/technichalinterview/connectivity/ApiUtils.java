package com.globallogic.technichalinterview.connectivity;

public class ApiUtils {

    private static String BASE_URL = "http://private-f0eea-mobilegllatam.apiary-mock.com/";

    public static ApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }

}
