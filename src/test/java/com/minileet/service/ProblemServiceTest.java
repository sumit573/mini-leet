package com.minileet.service;

import com.minileet.model.Problem;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ProblemServiceTest {

    private ProblemService service;

    @BeforeEach
    void setup() {
        service = new ProblemService();
    }

    @Test
    void createAndList() {
        Problem p = new Problem(null, "Two Sum", "desc", "EASY", new String[]{"array"});
        service.createProblem(p);
        assertEquals(1, service.listProblems().size());
        assertNotNull(service.getProblem(p.getId()));
    }

    @Test
    void deleteWorks() {
        Problem p = new Problem(null, "Tmp", "desc", "EASY", new String[]{});
        service.createProblem(p);
        assertTrue(service.deleteProblem(p.getId()));
        assertNull(service.getProblem(p.getId()));
    }
}
