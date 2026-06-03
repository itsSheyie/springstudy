package com.lakunle.springstudy.controller;

import com.lakunle.springstudy.model.Sitcom;
import com.lakunle.springstudy.service.SitcomService;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SitcomController.class)
class SitcomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SitcomService sitcomService;

    @Autowired
    private ObjectMapper objectMapper;

    private Sitcom sitcom;


    @BeforeEach
    void SetUp(){
        sitcom = new Sitcom("Friends", 1994, 10, "David Crane");
    }

    @Test
    void saveSitcom_shouldReturn200() throws Exception{
        when(sitcomService.saveSitcom(any(Sitcom.class))).thenReturn("Saved!");

        mockMvc.perform(post("/sitcoms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sitcom)))
                .andExpect(status().isOk())
                .andExpect(content().string("Saved!"));
    }

    @Test
    void getAllSitcom_shouldReturnList() throws Exception{
        when(sitcomService.getAllSitcoms()).thenReturn(List.of(sitcom));

        mockMvc.perform(get("/sitcoms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Friends"));
    }



    @Test
    void getSitcomByName_shouldReturnSitcom() throws Exception{
        when(sitcomService.getByName("Friends")).thenReturn(Optional.of(sitcom));

        mockMvc.perform(get("/sitcoms/name/Friends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Friends"));
    }

    @Test
    void getSitcomByName_shouldReturn404() throws Exception{
        when(sitcomService.getByName("Unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/sitcoms/name/Unknown"))
                .andExpect(status().isNotFound());
    }

    @Test
    void watchSitcom_shouldReturnMessage() throws Exception{
        when(sitcomService.watchSitcom("Friends")).thenReturn("Friends has been watched 1 times");

        mockMvc.perform(get("/sitcoms/watch/Friends"))
                .andExpect(status().isOk())
                .andExpect(content().string("Friends has been watched 1 times"));
    }

    @Test
    void deleteSitcom_shouldReturnMessage() throws Exception{
        when(sitcomService.deleteSitcom("Friends", 1994, "David Crane")).thenReturn("Friends Removed!");

        mockMvc.perform(delete("/sitcoms/Friends")
                        .param("ygitear", "1994")
                        .param("producer", "David Crane"))
                .andExpect(status().isOk())
                .andExpect(content().string("Friends Removed!"));
    }


}
