package app.certus.com.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.certus.com.certusmobile.MyApplication;
import app.certus.com.certusmobile.R;
import app.certus.com.model.CartDetails;
import app.certus.com.model.CompleteCartProItem;

/**
 * Created by shanaka on 2/27/16.
 */
public class CartItem_ScrollerAdapter extends RecyclerView.Adapter<CartItem_ScrollerAdapter.CartItemViewHolder> {

    private LayoutInflater inflater;
    private List<CompleteCartProItem> completeCartProItems = new ArrayList<>();
    private Context context;


    public CartItem_ScrollerAdapter(Context context,  List<CompleteCartProItem> completeCartProItems) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.completeCartProItems = completeCartProItems;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        CartItemViewHolder cartItemViewHolder = new CartItemViewHolder(view);
        return cartItemViewHolder;
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, int position) {
        CompleteCartProItem proItem = completeCartProItems.get(position);
        CartDetails details = (CartDetails) MyApplication.getAndroidSession().getAttribute("cart");
        holder.cart_pro_name.setText(proItem.getP_name());
        holder.cart_pro_price.setText("Rs " + (proItem.getP_dscPer() != 0 ? details.getDiscountPrice(proItem.getP_price(), proItem.getP_dscPer()) : proItem.getP_price()));
        holder.cart_pro_qnty.setText(details.getQntyOfProduct(proItem.getPid(), proItem.getP_size()) + "");
        holder.cart_pro_size.setText(proItem.getP_size());
        String image_url = "http://10.0.2.2:8080/ECommerceApp/" + proItem.getP_img();
        Picasso.with(context).load(image_url).into(holder.cart_pro_img);

    }

    @Override
    public int getItemCount() {
        return completeCartProItems != null ? completeCartProItems.size() : 0;
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_pro_name;
        TextView cart_pro_price;
        TextView cart_pro_qnty;
        TextView cart_pro_size;
        ImageView cart_pro_img;
        ImageButton cart_remove_btn;
        Button cart_change;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            cart_pro_name = (TextView) itemView.findViewById(R.id.cart_item_product_name);
            cart_pro_price = (TextView) itemView.findViewById(R.id.cart_item_product_price);
            cart_pro_size = (TextView) itemView.findViewById(R.id.cart_item_size);
            cart_pro_img = (ImageView) itemView.findViewById(R.id.cart_product_img);
            cart_pro_qnty = (TextView) itemView.findViewById(R.id.cart_item_qnty);
            //===============================================================================================
            cart_remove_btn = (ImageButton) itemView.findViewById(R.id.remove_item_btn);
            cart_change = (Button) itemView.findViewById(R.id.cart_item_change_btn);
            cart_pro_img.setOnClickListener(this);
            cart_remove_btn.setOnClickListener(this);
            cart_change.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
//            if (v instanceof ImageButton) {
//                Toast t = Toast.makeText(v.getContext(), "Remove product - "
//                        + proInfoEntities.get(getAdapterPosition()).getP_name(), Toast.LENGTH_SHORT);
//                t.show();
//            } else if (v instanceof ImageView) {
//                Toast t = Toast.makeText(v.getContext(), "View detail product - "
//                        + proInfoEntities.get(getAdapterPosition()).getP_name(), Toast.LENGTH_SHORT);
//                t.show();
//            } else if (v instanceof Button) {
//                Toast t = Toast.makeText(v.getContext(), "Edit product - "
//                        + proInfoEntities.get(getAdapterPosition()).getP_name(), Toast.LENGTH_SHORT);
//                t.show();
//            }
        }
    }
}
