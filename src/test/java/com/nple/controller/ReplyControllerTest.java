package com.nple.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nple.domain.Reply;
import com.nple.persistence.replyRepositories.ReplyRepository;
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


@SpringBootTest
@AutoConfigureMockMvc
class ReplyControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ReplyRepository replyRepo;

    @Autowired
    WebApplicationContext ctx;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    public void testAddReplyToImages() throws Exception {
        Reply reply = Reply.builder()
                .replyContent("hi")
                .replyer("kim to clip 1")
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(reply);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/book/clip/replies/image/1")
                    .content(data)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testAddReplyToWord() throws Exception {
        Reply reply = Reply.builder()
                .replyContent("hi")
                .replyer("kim to clip 1")
                .build();
        ObjectMapper mapper = new ObjectMapper();
        String data = mapper.writeValueAsString(reply);

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/book/clip/replies/word/1")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetRepliesOfImage() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/book/clip/replies/image/1")
                        .content("0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetRepliesOfWord() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/book/clip/replies/word/1")
                        .content("0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    // 여기서부터 작성

    @Test
    public void testRemoveReply() throws Exception {
        this.mockMvc.perform(delete("/book/clip/replies/1/805"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUpdateReply() throws Exception {
        String data = "{\n" +
                "  \"replyer\" : \"김은총\",\n" +
                "  \"replyContent\" : \"수정되었다\"\n" +
                "}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/book/clip/replies/1/2")
                        .content(data)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}