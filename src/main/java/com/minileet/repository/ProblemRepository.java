package com.minileet.repository;

import com.minileet.model.Problem;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProblemRepository {

    private final Map<Long, Problem> db = new HashMap<>();
    private Long counter = 1L;

    public Problem save(Problem problem) {
        if (problem.getId() == null) {
            problem.setId(counter++);
        }
        db.put(problem.getId(), problem);
        return problem;
    }

    public Optional<Problem> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<Problem> findAll() {
        return new ArrayList<>(db.values());
    }

    public Optional<Problem> findBySlug(String slug) {
        return db.values().stream()
                .filter(p -> p.getSlug().equalsIgnoreCase(slug))
                .findFirst();
    }

    public void delete(Long id) {
        db.remove(id);
    }
}
