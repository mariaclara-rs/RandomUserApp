package com.example.randomuser;

import java.util.List;

public class Results {
    private List<Result> results;

    public Results(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
