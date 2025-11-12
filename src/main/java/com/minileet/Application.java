package com.minileet;

import com.minileet.model.Problem;
import com.minileet.service.ProblemService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ProblemService ps = new ProblemService();
        Problem p1 = new Problem(null, "Two Sum", "Find two numbers that add to target", "EASY", new String[]{"Array", "HashMap"});
        ps.createProblem(p1);
        System.out.println("🧩 Added: " + p1.getTitle());
        System.out.println("📋 Total problems: " + ps.listProblems().size());
    }
}