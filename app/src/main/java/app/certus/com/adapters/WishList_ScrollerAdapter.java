package app.certus.com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.certus.com.certusmobile.R;
import app.certus.com.model.WishListItem;

/**
 * Created by shanaka on 2/27/16.
 */
public class WishList_ScrollerAdapter extends RecyclerView.Adapter<WishList_ScrollerAdapter.WishListHolder> {
    private List<WishListItem> wish_list_item;
    private Context context;

    public WishList_ScrollerAdapter(Context context, List<WishListItem> wish_list_item) {
        this.context = context;
        this.wish_list_item = wish_list_item;
    }

    @Override
    public WishListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_list, null);
        WishListHolder wishListHolder = new WishListHolder(layoutView);
        return wishListHolder;
    }

    @Override
    public void onBindViewHolder(WishListHolder holder, int position) {
        WishListItem item = wish_list_item.get(position);
        holder.pro_name.setText(item.getProduct_name());
        holder.pro_brand.setText(item.getProduct_brand());
        holder.pro_price.setText("Rs " + item.getProduct_price());
        holder.product_img.setImageResource(item.getImg_id());
    }

    @Override
    public int getItemCount() {
        return wish_list_item != null ? wish_list_item.size() : 0;
    }

    class WishListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView pro_name;
        TextView pro_price;
        TextView pro_brand;
        ImageView product_img;

        public WishListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            pro_name = (TextView) itemView.findViewById(R.id.wish_list_pro_name);
            pro_price = (TextView) itemView.findViewById(R.id.wish_list_pro_price);
            pro_brand = (TextView) itemView.findViewById(R.id.wish_list_pro_brand);
            product_img = (ImageView) itemView.findViewById(R.id.wish_list_img);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        }

    }
}
