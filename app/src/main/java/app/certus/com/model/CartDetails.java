package app.certus.com.model;

import com.annimon.stream.Stream;

import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by shanaka on 2/27/16.
 */
public class CartDetails {

    private CopyOnWriteArrayList<CartItem> shoppingList;
    private double cart_total;

    public CartDetails() {
        this.shoppingList = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList<CartItem> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(CopyOnWriteArrayList<CartItem> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public double getCart_total() {
        return cart_total;
    }

    public void setCart_total(double cart_total) {
        this.cart_total = cart_total;
    }

    public boolean addItem(CartItem item) {
        boolean flag = false;
        for (CartItem itm : this.shoppingList) {
            if (itm.isSameCartItem(item)) {
                itm.setQnty(item.getQnty() + itm.getQnty());
                flag = true;
                break;
            }
        }
        if (!flag) {
            this.shoppingList.add(item);
            flag = true;
        }
        return flag;

    }

    public int getTotalItemsOfTheCart() {
        int qntyT = 0;
        for (CartItem item : this.getShoppingList()) {
            qntyT += item.getQnty();
        }
        return qntyT;
    }
    public double getDiscountPrice(double price, int percentage) {
        return (int) (price - (price * (percentage / 100.0f)));
    }

    public void updateItemCount(int pro_id, int qnty, String size) {
        for (CartItem item : this.shoppingList) {
            if (item.getProduct_id() == pro_id && item.getSize().equals(size)) {
                this.shoppingList.get(this.shoppingList.indexOf(item)).setQnty(qnty);
            }
        }
    }

    public int getQntyOfProduct(int pid, String size) {
        return Stream.of(getShoppingList())
                .filter(i -> i.getProduct_id() == pid && i.getSize().equals(size))
                .findFirst().get().getQnty();

    }

    public void updateProductSize(int pro_id, String sizePre, String sizeNew) {
        for (CartItem item : this.shoppingList) {
            if (item.getProduct_id() == pro_id && item.getSize().equals(sizePre)) {
                this.shoppingList.get(this.shoppingList.indexOf(item)).setSize(sizeNew);
            }
        }
    }

    public void removeItem(int item_id, String size) {
        for (CartItem item : this.shoppingList) {
            if (item.getProduct_id() == item_id && item.getSize().equals(size)) {
                this.shoppingList.remove(item);
            }
        }
    }
}
