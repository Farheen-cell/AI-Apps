package com.masai.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masai.controller.ConversationController;
import com.masai.service.OpenAIService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConversationControllerTest {

    @Mock
    private OpenAIService openAIService;

    @InjectMocks
    private ConversationController conversationController;

    private MockMvc mockMvc;

    public ConversationControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(conversationController).build();
    }

    @Test
    void converse_Success() throws Exception {
        String userInput = "Hello";
        String gptResponse = "{\"choices\":[{\"text\":\"Hi there!\"}]}";

        when(openAIService.getResponse(any())).thenReturn(gptResponse);

        mockMvc.perform(post("/api/conversation")
                        .contentType(MediaType.APPLICATION_JSON) // Specify expected content type
                        .content(new ObjectMapper().writeValueAsString(userInput)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Expect JSON content type
                .andExpect(content().json(gptResponse));
    }


    @Test
    void converse_Error() throws Exception {
        String userInput = "Hello";

        when(openAIService.getResponse(any())).thenThrow(new IOException());

        mockMvc.perform(post("/api/conversation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userInput)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string("Error occurred while processing your request."));
    }
}

