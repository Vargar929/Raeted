package cf.homeit.raeted.Fragmnts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import cf.homeit.raeted.R;

public class ViewRatingFragment extends Fragment {
    public ViewRatingFragment(){ }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_rating_fragment, container, false);
        return view;
    }
}
