// package com.saimen.api.dto;

// import java.util.LinkedHashMap;
// import java.util.Map;

// public class ValidationReport {
// private String overallStatus;
// private Map<String, FieldResult> results = new LinkedHashMap<>();

// public void put(String field, FieldResult fr) {
// results.put(field, fr);
// }

// public Map<String, FieldResult> getResults() {
// return results;
// }

// public String getOverallStatus() {
// return overallStatus;
// }

// public void setOverallStatus(String s) {
// this.overallStatus = s;
// }

// public void finalizeStatus() {
// boolean anyError = results.values().stream().anyMatch(r ->
// "ERROR".equals(r.getStatus()));
// this.overallStatus = anyError ? "ERROR" : "OK";
// }
// }
