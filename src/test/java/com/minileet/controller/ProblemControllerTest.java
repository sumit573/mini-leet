package com.minileet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minileet.model.Problem;
import com.minileet.service.ProblemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProblemController.class)
class ProblemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProblemService problemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllShouldReturnList() throws Exception {
        Problem a = new Problem(1L, "A", "a", "d", "EASY");
        Problem b = new Problem(2L, "B", "b", "d", "MEDIUM");
        given(problemService.getAll()).willReturn(Arrays.asList(a, b));

        mockMvc.perform(get("/api/problems"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("A"));
    }

    @Test
    void getByIdFound() throws Exception {
        Problem p = new Problem(1L, "A", "a", "d", "EASY");
        given(problemService.getById(1L)).willReturn(Optional.of(p));

        mockMvc.perform(get("/api/problems/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("A"));
    }

    @Test
    void getByIdNotFound() throws Exception {
        given(problemService.getById(99L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/problems/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createShouldReturnCreated() throws Exception {
        Problem input = new Problem(null, "New", "new", "desc", "EASY");
        Problem created = new Problem(5L, "New", "new", "desc", "EASY");
        given(problemService.create(any(Problem.class))).willReturn(created);

        mockMvc.perform(post("/api/problems")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5));

        verify(problemService).create(any(Problem.class));
    }

    @Test
    void updateFoundAndNotFound() throws Exception {
        Problem update = new Problem(null, "U", "u", "d", "MEDIUM");
        Problem updated = new Problem(1L, "U", "u", "d", "MEDIUM");
        given(problemService.update(eq(1L), any(Problem.class))).willReturn(Optional.of(updated));
        given(problemService.update(eq(99L), any(Problem.class))).willReturn(Optional.empty());

        mockMvc.perform(put("/api/problems/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("U"));

        mockMvc.perform(put("/api/problems/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(update)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteFoundAndNotFound() throws Exception {
        given(problemService.delete(1L)).willReturn(true);
        given(problemService.delete(99L)).willReturn(false);

        mockMvc.perform(delete("/api/problems/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(delete("/api/problems/99"))
                .andExpect(status().isNotFound());
    }
}

