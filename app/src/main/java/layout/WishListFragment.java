package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.certus.com.adapters.WishList_ScrollerAdapter;
import app.certus.com.certusmobile.R;
import app.certus.com.model.WishListItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class WishListFragment extends Fragment {

    private StaggeredGridLayoutManager wishListLayoutManager;
    private RecyclerView wishList_recyclerView;

    public WishListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        wishList_recyclerView = (RecyclerView) view.findViewById(R.id.wish_list_recycler_view);
        wishList_recyclerView.setHasFixedSize(true);
        wishListLayoutManager = new StaggeredGridLayoutManager(2, 1);
        wishList_recyclerView.setLayoutManager(wishListLayoutManager);
        WishList_ScrollerAdapter wishList_scrollerAdapter = new WishList_ScrollerAdapter(getActivity(), getData());
        wishList_recyclerView.setAdapter(wishList_scrollerAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private List<WishListItem> getData() {
        List<WishListItem> wishListItems = new ArrayList<>();
        wishListItems.add(new WishListItem(R.drawable.item_test_2, "Product 1", "Brand 1", 2300.00));
        wishListItems.add(new WishListItem(R.drawable.img_test, "Product 2", "Brand 2", 1950.00));
        wishListItems.add(new WishListItem(R.drawable.item_test_2, "Product 3", "Brand 3", 1670.00));
        wishListItems.add(new WishListItem(R.drawable.img_test, "Product 4", "Brand 4", 2750.00));
        wishListItems.add(new WishListItem(R.drawable.item_test_2, "Product 5", "Brand 5", 1280.00));
        wishListItems.add(new WishListItem(R.drawable.img_test, "Product 6", "Brand 6", 1700.00));
        return wishListItems;
    }
}

