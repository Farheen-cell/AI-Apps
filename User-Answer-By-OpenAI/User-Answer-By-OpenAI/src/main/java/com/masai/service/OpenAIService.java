package com.masai.service;

import java.io.IOException;

public interface OpenAIService {

    public String getResponse(String prompt) throws IOException;
}
