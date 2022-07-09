// Name: Roxie Reginold       Student Number: 501087897
import java.util.ArrayList;

/*
 Models a Cart for a specific customer
 */
public class Cart {
    //private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    //private String id;
    private ArrayList<CartItem> cartItems;
    public Cart(){
        //this.id = id;
        this.cartItems = new ArrayList<CartItem>(); // create arraylist for a customer
    }
    // methods to add or remove items in list
    public void addToCart(CartItem item){
        cartItems.add(item);
    }

    public void removeFromCart(CartItem item){
        cartItems.remove(item);
    }

    public void removeFromCart(int index){
        cartItems.remove(index);
    } // needed as other remove method causes errors when used 

    public ArrayList<CartItem> getCartList (){
        return this.cartItems;
    } // get list of items when needed


}
