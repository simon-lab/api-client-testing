package com.saimen.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ValidationContext {
    private final List<String> errors = new ArrayList<>();

    public void addError(String msg) {
        if (msg != null && !msg.isBlank())
            errors.add(msg);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private String joinErrors() {
        StringJoiner sj = new StringJoiner(", ");
        errors.forEach(sj::add);
        return sj.toString();
    }

    public ValidationResult toResult() {
        if (hasErrors()) {
            return new ValidationResult("ERROR", joinErrors());
        }
        return new ValidationResult("OK", "Semua field sudah sesuai expected dan specification");
    }
}
