package com.nple.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void testUpdateClips() throws Exception {
        String requestbody = "{\n" +
                "  \"iclip\": [\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"11\",\n" +
                "    \"imageDir\":\"/root/image1\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"12\",\n" +
                "    \"imageDir\":\"/root/image2\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"13\",\n" +
                "    \"imageDir\":\"/root/image3\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"14\",\n" +
                "    \"imageDir\":\"/root/image4\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"18\",\n" +
                "    \"imageDir\":\"/root/image5\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"19\",\n" +
                "    \"imageDir\":\"/root/image6\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"110\",\n" +
                "    \"imageDir\":\"/root/image7\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"111\",\n" +
                "    \"imageDir\":\"/root/image8\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"112\",\n" +
                "    \"imageDir\":\"/root/image9\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"113\",\n" +
                "    \"imageDir\":\"/root/image10\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"114\",\n" +
                "    \"imageDir\":\"/root/image11\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"wclip\":[\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"15\",\n" +
                "    \"paragraph\":\"book content 01\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"16\",\n" +
                "    \"paragraph\":\"book content 02\"\n" +
                "    },\n" +
                "    {\n" +
                "    \"contentInnerNum\": \"17\",\n" +
                "    \"paragraph\":\"book content 03\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .put("/book/clip/3")
                        .content(requestbody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}