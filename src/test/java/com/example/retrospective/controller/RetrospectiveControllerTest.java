package com.example.retrospective.controller;

import com.example.retrospective.RetrospectiveApplication;
import com.example.retrospective.domain.Retrospective;
import com.example.retrospective.service.RetrospectiveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RetrospectiveController.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RetrospectiveApplication.class)
class RetrospectiveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RetrospectiveService retrospectiveService;

    @Test
    void testCreateRetrospective() throws Exception {
        Retrospective retrospective = Retrospective.builder().name("Sprint 1 Retro")
                .summary("First sprint retrospective").date(LocalDate.now())
                .participants(Arrays.asList("John", "Arya")).build();

        when(retrospectiveService.createRetrospective(any(Retrospective.class))).thenReturn(retrospective);

        mockMvc.perform(post("/retrospectives")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(retrospective)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sprint 1 Retro"))
                .andExpect(jsonPath("$.summary").value("First sprint retrospective"));
    }

    private String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

