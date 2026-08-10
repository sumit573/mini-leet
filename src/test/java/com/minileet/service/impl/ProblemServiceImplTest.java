package com.minileet.service.impl;

import com.minileet.model.Problem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProblemServiceImplTest {

    private ProblemServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new ProblemServiceImpl();
    }

    @Test
    void createAndGetByIdAndGetAll() {
        Problem p = new Problem(null, "Sum", "sum", "sum desc", "EASY");
        Problem created = service.create(p);
        assertNotNull(created.getId());

        Optional<Problem> fetched = service.getById(created.getId());
        assertTrue(fetched.isPresent());
        assertEquals("Sum", fetched.get().getTitle());

        List<Problem> all = service.getAll();
        assertEquals(1, all.size());
    }

    @Test
    void updateExistingAndNonExisting() {
        Problem p = service.create(new Problem(null, "A", "a", "d", "EASY"));

        Problem updateData = new Problem(null, "A-up", "a-up", "d-up", "MEDIUM");
        Optional<Problem> updated = service.update(p.getId(), updateData);
        assertTrue(updated.isPresent());
        assertEquals("A-up", updated.get().getTitle());
        assertEquals("MEDIUM", updated.get().getDifficulty());

        Optional<Problem> notFound = service.update(999L, updateData);
        assertFalse(notFound.isPresent());
    }

    @Test
    void deleteReturnsTrueOrFalse() {
        Problem p = service.create(new Problem(null, "X", "x", "d", "HARD"));
        boolean deleted = service.delete(p.getId());
        assertTrue(deleted);

        boolean deletedAgain = service.delete(p.getId());
        assertFalse(deletedAgain);
    }
}

