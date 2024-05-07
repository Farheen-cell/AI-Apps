package com.masai.config;

import okhttp3.OkHttpClient;

public class AppConstants {

    public static final String API_KEY = "YOUR_KEY";
    public static final OkHttpClient client = new OkHttpClient();
    public static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";
}
