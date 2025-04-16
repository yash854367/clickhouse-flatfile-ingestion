package com.ingestion.clickhouseflatfile.model;

public class IngestionResult {
    private int totalRecords;
    private String status;
    private String message;

    public IngestionResult(int totalRecords, String status, String message) {
        this.totalRecords = totalRecords;
        this.status = status;
        this.message = message;
    }

    public int getTotalRecords() { return totalRecords; }
    public void setTotalRecords(int totalRecords) { this.totalRecords = totalRecords; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

