package com.example.curebites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * UploadedReportsAdapter
 *
 * Connects the List<ReportModel> data to the RecyclerView in UploadReportActivity.
 *
 * How a RecyclerView Adapter works (simple explanation):
 *   - onCreateViewHolder()  → inflates (creates) the XML layout for one row.
 *   - onBindViewHolder()    → fills that row with data from the list.
 *   - getItemCount()        → tells RecyclerView how many rows to show.
 */
public class UploadedReportsAdapter
        extends RecyclerView.Adapter<UploadedReportsAdapter.ReportViewHolder> {

    private final Context           context;    // Needed to inflate layouts
    private final List<ReportModel> reportList; // The data we want to display

    /**
     * Constructor called from UploadReportActivity.
     *
     * @param context    The activity context.
     * @param reportList The list of reports to display.
     */
    public UploadedReportsAdapter(Context context, List<ReportModel> reportList) {
        this.context    = context;
        this.reportList = reportList;
    }

    // ──────────────────────────────────────────────────────────────────
    /**
     * onCreateViewHolder()
     * Called by RecyclerView when it needs a NEW row view.
     * We inflate item_uploaded_report.xml and wrap it in a ViewHolder.
     */
    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the row layout
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_uploaded_report, parent, false);
        return new ReportViewHolder(view);
    }

    // ──────────────────────────────────────────────────────────────────
    /**
     * onBindViewHolder()
     * Called by RecyclerView to fill an existing row with data.
     * This runs once per visible row, so keep it fast.
     *
     * @param holder   The ViewHolder containing the row's views.
     * @param position The index in reportList for this row.
     */
    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        ReportModel report = reportList.get(position);

        // Set file name (e.g. "Blood_Report_Nov24.pdf")
        holder.tvFileName.setText(report.getFileName());

        // Set file size (e.g. "2.4 MB")
        holder.tvFileSize.setText(report.getFileSize());

        // Set status badge text (e.g. "Analysed" or "Uploading…")
        holder.tvStatus.setText(report.getStatus());

        // Change badge color based on status:
        //   "Analysed"   → green text (matches the design)
        //   anything else → gray text (still uploading or error)
        if ("Analysed".equals(report.getStatus())) {
            holder.tvStatus.setTextColor(
                    context.getResources().getColor(R.color.green_primary, null));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_badge_green);
        } else {
            holder.tvStatus.setTextColor(
                    context.getResources().getColor(android.R.color.darker_gray, null));
            holder.tvStatus.setBackgroundResource(R.drawable.bg_badge_gray);
        }
    }

    // ──────────────────────────────────────────────────────────────────
    /**
     * getItemCount()
     * Tells RecyclerView how many rows exist.
     */
    @Override
    public int getItemCount() {
        return reportList.size();
    }

    // ──────────────────────────────────────────────────────────────────
    /**
     * ReportViewHolder
     * A ViewHolder caches references to each view inside a row layout.
     * This avoids calling findViewById() every time a row scrolls into view,
     * which would be slow.
     */
    static class ReportViewHolder extends RecyclerView.ViewHolder {

        TextView tvFileName; // The file name label
        TextView tvFileSize; // The file size label
        TextView tvStatus;   // The status badge ("Analysed")

        ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tvFileName);
            tvFileSize = itemView.findViewById(R.id.tvFileSize);
            tvStatus   = itemView.findViewById(R.id.tvStatus);
        }
    }
}