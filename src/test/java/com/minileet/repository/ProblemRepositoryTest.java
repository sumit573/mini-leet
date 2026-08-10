package com.minileet.repository;

import com.minileet.model.Problem;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProblemRepositoryTest {

    @Test
    void saveAndFindById() {
        ProblemRepository repo = new ProblemRepository();
        Problem p = new Problem(null, "Two Sum", "two-sum", "Find two numbers that add to target", "EASY");

        Problem saved = repo.save(p);
        assertNotNull(saved.getId(), "saved id should not be null");

        Optional<Problem> byId = repo.findById(saved.getId());
        assertTrue(byId.isPresent());
        assertEquals("Two Sum", byId.get().getTitle());
    }

    @Test
    void findAllFindBySlugAndDelete() {
        ProblemRepository repo = new ProblemRepository();
        Problem a = repo.save(new Problem(null, "A", "a", "desc A", "EASY"));
        Problem b = repo.save(new Problem(null, "B", "b", "desc B", "MEDIUM"));

        List<Problem> all = repo.findAll();
        assertEquals(2, all.size());

        Optional<Problem> bySlug = repo.findBySlug("b");
        assertTrue(bySlug.isPresent());
        assertEquals("B", bySlug.get().getTitle());

        repo.delete(a.getId());
        assertFalse(repo.findById(a.getId()).isPresent());
        assertEquals(1, repo.findAll().size());
    }
}

