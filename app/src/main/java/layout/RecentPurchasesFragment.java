package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.certus.com.certusmobile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentPurchasesFragment extends Fragment {


    public RecentPurchasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recent_purchases, container, false);
        return view;
    }

}
