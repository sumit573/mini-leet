package com.minileet.service;

import com.minileet.model.Problem;

import java.util.List;
import java.util.Optional;

public interface ProblemService {

    List<Problem> getAll();

    Optional<Problem> getById(Long id);

    Problem create(Problem problem);

    Optional<Problem> update(Long id, Problem newData);

    boolean delete(Long id);
}
