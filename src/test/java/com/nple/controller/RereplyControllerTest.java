package com.nple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nple.domain.Rereply;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RereplyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebApplicationContext ctx;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    public void testGettingListofRereply() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                                .get("/rereplies/1")
                                .content("0")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testAddingRereply() throws Exception {
        Rereply result = Rereply.builder()
                .replyContent("new Reply")
                .replyer("Me")
                .build();
        String data = new ObjectMapper().writeValueAsString(result);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/rereplies/1")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testRemovingRereply() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/rereplies/121"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUpdatingRereply() throws Exception {
        Rereply result = Rereply.builder()
                .replyContent("new Reply: modified")
                .replyer("Me")
                .build();
        String data = new ObjectMapper().writeValueAsString(result);

        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/rereplies/122")
                .content(data)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }




}