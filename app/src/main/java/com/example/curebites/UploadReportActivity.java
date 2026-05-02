package com.example.curebites;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class UploadReportActivity extends AppCompatActivity {

    // The container where new file cards are added dynamically
    private LinearLayout llDynamicCards;

    // Track how many files have been uploaded
    // Used to check if user uploaded at least one file before proceeding
    private int uploadedFileCount = 0;

    // File picker launcher
    // This opens the phone's file manager and returns the selected file
    private final ActivityResultLauncher<Intent> filePickerLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK
                                && result.getData() != null) {
                            Uri fileUri = result.getData().getData();
                            if (fileUri != null) handlePickedFile(fileUri);
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_report);

        // ── Back arrow ─────────────────────────────────────────────
        // Goes back to the previous screen
        findViewById(R.id.ivBack).setOnClickListener(v -> finish());

        // ── Upload box tap ─────────────────────────────────────────
        // Opens file picker when user taps the dashed upload area
        findViewById(R.id.llUploadBox).setOnClickListener(v -> openFilePicker());

        // ── "+ Add more" tap ───────────────────────────────────────
        // Also opens file picker to add another file
        findViewById(R.id.tvAddMore).setOnClickListener(v -> openFilePicker());

        // ── Proceed button ─────────────────────────────────────────
        // Navigates to HealingPlanActivity when tapped
        // Shows warning if no file has been uploaded yet
        Button btnProceed = findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(v -> {

            if (uploadedFileCount == 0) {
                // No files uploaded yet — warn the user
                Toast.makeText(this,
                        "Please upload at least one report first.",
                        Toast.LENGTH_SHORT).show();
            } else {
                // At least one file uploaded — go to Healing Plan screen
                Intent intent = new Intent(this, HealingPlanActivity.class);
                startActivity(intent);
            }
        });

        // ── Dynamic cards container ────────────────────────────────
        // New file cards will be added here when user picks files
        llDynamicCards = findViewById(R.id.llDynamicCards);
    }

    /**
     * openFilePicker()
     *
     * Opens Android's built-in file chooser.
     * Allows PDF, PNG, and JPG files only (matching the UI hint).
     */
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES,
                new String[]{"application/pdf", "image/png", "image/jpeg"});
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        filePickerLauncher.launch(intent);
    }

    /**
     * handlePickedFile(Uri fileUri)
     *
     * Called after the user selects a file from the picker.
     * 1. Reads the file name and size from the system.
     * 2. Checks the file is under 10 MB.
     * 3. Adds a new card to the list showing the file.
     * 4. Increments the uploaded file counter.
     *
     * @param fileUri  The URI of the file selected by the user.
     */
    private void handlePickedFile(Uri fileUri) {
        String fileName = "unknown_file";
        long fileSizeBytes = 0;

        // Read file name and size using ContentResolver
        try (Cursor cursor = getContentResolver().query(
                fileUri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIdx = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIdx = cursor.getColumnIndex(OpenableColumns.SIZE);
                if (nameIdx != -1) fileName = cursor.getString(nameIdx);
                if (sizeIdx != -1) fileSizeBytes = cursor.getLong(sizeIdx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ── Check 10 MB size limit ──
        if (fileSizeBytes > 10L * 1024 * 1024) {
            Toast.makeText(this,
                    "File too large. Max 10 MB.",
                    Toast.LENGTH_SHORT).show();
            return; // Stop here — don't add the file
        }

        // ── Convert bytes to readable size string ──
        // Shows "2.4 MB" or "500 KB" depending on size
        String sizeLabel = fileSizeBytes >= 1024 * 1024
                ? String.format("%.1f MB", fileSizeBytes / (1024.0 * 1024.0))
                : String.format("%.0f KB", fileSizeBytes / 1024.0);

        // ── Add card to the list ──
        addReportCard(fileName, sizeLabel);

        // ── Increment uploaded file count ──
        // This allows the Proceed button to work correctly
        uploadedFileCount++;
    }

    /**
     * addReportCard(fileName, fileSize)
     *
     * Dynamically creates a new file card view and adds it
     * to the llDynamicCards container.
     * Matches the same design as the static cards in XML.
     *
     * @param fileName  Display name of the file.
     * @param fileSize  Human-readable size string (e.g. "2.4 MB").
     */
    private void addReportCard(String fileName, String fileSize) {
        int dp14 = dpToPx(14);
        int dp36 = dpToPx(36);
        int dp12 = dpToPx(12);
        int dp10 = dpToPx(10);

        // ── Outer card row ──
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setGravity(Gravity.CENTER_VERTICAL);
        card.setPadding(dp14, dp14, dp14, dp14);
        card.setBackgroundResource(R.drawable.bg_report_item);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        cardParams.setMargins(0, 0, 0, dp10);
        card.setLayoutParams(cardParams);

        // ── Document icon on the left ──
        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams(dp36, dp36));
        icon.setImageResource(R.drawable.ic_document);
        icon.setAlpha(0.5f);
        card.addView(icon);

        // ── File name + size column ──
        LinearLayout textCol = new LinearLayout(this);
        textCol.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams colParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        colParams.setMarginStart(dp12);
        textCol.setLayoutParams(colParams);

        // File name (bold)
        TextView tvName = new TextView(this);
        tvName.setText(fileName);
        tvName.setTextColor(0xFF111111);
        tvName.setTextSize(14);
        tvName.setTypeface(null, android.graphics.Typeface.BOLD);
        textCol.addView(tvName);

        // File size (gray)
        TextView tvSize = new TextView(this);
        tvSize.setText(fileSize);
        tvSize.setTextColor(0xFF9E9E9E);
        tvSize.setTextSize(12);
        LinearLayout.LayoutParams sizeParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        sizeParams.setMargins(0, dpToPx(2), 0, 0);
        tvSize.setLayoutParams(sizeParams);
        textCol.addView(tvSize);
        card.addView(textCol);

        // ── "Analysed" green badge on the right ──
        TextView badge = new TextView(this);
        badge.setText("Analysed");
        badge.setTextColor(0xFF1B8B5A);
        badge.setTextSize(12);
        badge.setTypeface(null, android.graphics.Typeface.BOLD);
        badge.setBackgroundResource(R.drawable.bg_badge_green);
        badge.setPadding(dpToPx(12), dpToPx(5), dpToPx(12), dpToPx(5));
        card.addView(badge);

        // ── Add the finished card to the screen ──
        llDynamicCards.addView(card);
    }

    /**
     * dpToPx(dp)
     * Converts dp units to pixels for the current screen density.
     *
     * @param dp  Value in density-independent pixels.
     * @return    Equivalent value in actual pixels.
     */
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}