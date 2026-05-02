package com.example.curebites;

/**
 * ReportModel
 *
 * A simple data class (POJO) that holds the information for ONE uploaded report.
 * Think of it as a single row in the uploaded-reports list.
 *
 * Fields:
 *   fileName  – e.g. "Blood_Report_Nov24.pdf"
 *   fileSize  – human-readable string, e.g. "2.4 MB"
 *   status    – e.g. "Analysed" or "Uploading…"
 */
public class ReportModel {

    private String fileName;
    private String fileSize;
    private String status;

    /**
     * Constructor used when creating a new report entry.
     *
     * @param fileName  Display name of the file.
     * @param fileSize  Human-readable size string.
     * @param status    Current status label.
     */
    public ReportModel(String fileName, String fileSize, String status) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.status   = status;
    }

    // ── Getters (used by the Adapter to read data) ──────────────────

    public String getFileName() { return fileName; }
    public String getFileSize() { return fileSize; }
    public String getStatus()   { return status;   }

    // ── Setters (useful if you want to update status after upload) ──

    public void setStatus(String status) { this.status = status; }
}
