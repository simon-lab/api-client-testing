package com.saimen.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ValidationContext {
    private final List<String> errors = new ArrayList<>();
    private final List<String> success = new ArrayList<>();

    public void addError(String msg) {
        if (msg != null && !msg.isBlank())
            errors.add(msg);
    }

    public void addSuccess(String msg) {
        if (msg != null && !msg.isBlank())
            success.add(msg);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public boolean hasSuccess() {
        return !success.isEmpty();
    }

    private String joinErrors() {
        StringJoiner sj = new StringJoiner(", ");
        errors.forEach(sj::add);
        return sj.toString();
    }

    private String joinSuccess() {
        StringJoiner sj = new StringJoiner(", ");
        success.forEach(sj::add);
        return sj.toString();
    }

    public ValidationResult toResult() {
        if (hasErrors()) {
            return new ValidationResult("ERROR", joinErrors());
        }
        return new ValidationResult("OK", "Semua field sudah sesuai expected dan specification");
    }

    public ValidationResult successResult() {
        if (hasSuccess()) {
            return new ValidationResult("OK", joinSuccess());
        }
        return toResult();
    }
}
