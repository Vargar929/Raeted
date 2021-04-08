package cf.homeit.rating.ViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import cf.homeit.rating.R;

public class RatingViewHolder extends RecyclerView.ViewHolder{
        public MaterialTextView readingListUrl, readingListTitle;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            readingListUrl = itemView.findViewById(R.id.result);
            readingListTitle = itemView.findViewById(R.id.date_result);
        }
    }

