package com.minileet.service;

import com.minileet.model.Problem;

import java.util.*;

public class ProblemService {

    private final Map<String, Problem> problems = new HashMap<>();

    public Problem createProblem(Problem problem) {
        problem.setId(UUID.randomUUID().toString());
        problems.put(problem.getId(), problem);
        return problem;
    }

    public List<Problem> listProblems() {
        return new ArrayList<>(problems.values());
    }

    public Problem getProblems(String id) {
        return problems.get(id);
    }

    public boolean deleteProblem(String id) {
        return problems.remove(id) != null;
    }
}
