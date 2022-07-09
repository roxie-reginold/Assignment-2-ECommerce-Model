// Name: Roxie Reginold       Student Number: 501087897
/*
 * class CartItem defines an item in a cart list of a specific customer
 * Also stores product option for the cart item chosen by the customer (e.g. paperback book, certain size of a product etc)
 */
public class CartItem extends Product{

    private Product product;
    private String productOptions;

    public CartItem(Product product){
        this.product = product;
        this.productOptions = "";
    }

    public CartItem(Product product, String productOptions){
        this.product = product;
        this.productOptions = productOptions;
    }

// get and set methods for item product and option
    public Product getCartItem()
    {
        return product;
    }
    public void setCartItem(Product product)
    {
        this.product = product;
    }

    public String getProductOption()
    {
        return productOptions;
    }
    public void setProductOption(String option)
    {
        this.productOptions = option;
    }


    public void print() // print using product's print method and printing options
    {
        product.print();
        System.out.print(" Options: " + productOptions);
    }

}
