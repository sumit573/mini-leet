package com.minileet.service.impl;

import com.minileet.model.Problem;
import com.minileet.service.ProblemService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final Map<Long, Problem> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Problem> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Problem> getById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Problem create(Problem problem) {
        Long newId = idGenerator.getAndIncrement();
        problem.setId(newId);
        storage.put(newId, problem);
        return problem;
    }

    @Override
    public Optional<Problem> update(Long id, Problem newData) {
        Problem existing = storage.get(id);
        if (existing == null) {
            return Optional.empty();
        }

        existing.setTitle(newData.getTitle());
        existing.setSlug(newData.getSlug());
        existing.setDescription(newData.getDescription());
        existing.setDifficulty(newData.getDifficulty());

        storage.put(id, existing);
        return Optional.of(existing);
    }

    @Override
    public boolean delete(Long id) {
        return storage.remove(id) != null;
    }
}
