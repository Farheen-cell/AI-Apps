package com.masai.service;

import com.masai.config.AppConstants;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenAIServiceImpl implements OpenAIService{

    @Override
    public String getResponse(String prompt) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"prompt\": \"" + prompt + "\"}");
        Request request = new Request.Builder()
                .url(AppConstants.OPENAI_API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + AppConstants.API_KEY)
                .build();

        try (Response response = AppConstants.client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
