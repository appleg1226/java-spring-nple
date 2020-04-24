package com.nple.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nple.domain.Clip;
import com.nple.persistence.clipRepositories.ImageClipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class ClipControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ImageClipRepository repo;

    @Test
    public void testGettingClipListFromBook() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/book/clip/2"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // jackson을 이용하여 json 결과를 정리
        String reresult = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<Object> clips = mapper.readValue(reresult, new TypeReference<List<Object>>() {});
        clips.forEach(System.out::println);
    }

    @Test
    public void testAddingClips() throws Exception {
        String requestbody = "{\n" +
                "  \"iclip\": [\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"1\",\n" +
                "    \"imageDir\":\"/root/image1\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"2\",\n" +
                "    \"imageDir\":\"/root/image2\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"3\",\n" +
                "    \"imageDir\":\"/root/image3\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"wclip\":[\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"4\",\n" +
                "    \"paragraph\":\"book content\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"5\",\n" +
                "    \"paragraph\":\"book content\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/book/clip/2")
                    .content(requestbody)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

}