package com.masai.controller;
import com.masai.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ConversationController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/conversation")
    public ResponseEntity<String> converse(@RequestBody String userInput) {
        try {
            String response = openAIService.getResponse(userInput);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while processing your request.");
        }
    }
}
