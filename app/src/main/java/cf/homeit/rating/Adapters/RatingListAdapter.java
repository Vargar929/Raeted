package cf.homeit.rating.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import cf.homeit.rating.DataModel.RatingList;
import cf.homeit.rating.R;
import cf.homeit.rating.SQLiteHelper.DBHelper;
import cf.homeit.rating.ViewHolder.RatingViewHolder;

public class RatingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    ArrayList<RatingList> arrayList;
    DBHelper readingListDB;

    public RatingListAdapter(Context mContext, ArrayList<RatingList> arrayList) {
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rating_item_view, viewGroup, false);
        return new RatingViewHolder(view);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        final RatingViewHolder viewHolder = (RatingViewHolder) holder;
        readingListDB = new DBHelper(mContext);
        viewHolder.readingListTitle.setText(arrayList.get(i).getrValue());
        viewHolder.readingListUrl.setText(arrayList.get(i).getrTime());
        viewHolder.itemView.setOnLongClickListener(v -> {
            PopupMenu popup = new PopupMenu(holder.itemView.getContext(), holder.itemView);

            popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) item -> {
                switch (item.getItemId()) {
                    case R.id.delete:
                        //handle menu3 click
//                        deleteFromFirebase(postKey,"switches",requireActivity().getApplicationContext());
                        break;
                }
                return false;
            });
            // here you can inflate your menu
            popup.inflate(R.menu.options_menu);
            popup.setGravity(Gravity.TOP);

            // if you want icon with menu items then write this try-catch block.
            try {
                Field mFieldPopup=popup.getClass().getDeclaredField("mPopup");
                mFieldPopup.setAccessible(true);
                MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popup);
                if (mPopup != null) {
                    mPopup.setForceShowIcon(true);
                }
            } catch (Exception e) {
            }
            popup.show();
            return false;
        });
    }


    @Override
    public int getItemCount() {
        return (arrayList == null) ? 0 : arrayList.size();
    }


}
