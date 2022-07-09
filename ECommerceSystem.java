// Name: Roxie Reginold       
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
 * Models a simple ECommerce system. Keeps track of products for sale, registered customers, product orders and
 * orders that have been shipped to a customer
 */
public class ECommerceSystem
{
    //private ArrayList<Product>  products = new ArrayList<Product>();
    //private Map<String, Product> products = new HashMap<String, Product>();// map of products
    private Map<String, Product> products = new TreeMap<String, Product>();// map of products with number of orders
    private Map<Product, Integer> prodOrderStats = new HashMap<Product, Integer>();// map of product stats
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    //String errMsg = null;
    
    // Random number generator
    Random random = new Random();

    /*
    Creates Products from file
     */
    private ArrayList<Product> createProducts() throws FileNotFoundException{ // throws File not found exception
        ArrayList<Product> products = new ArrayList<Product>(); //temporary list of products
        File prodsFile = new File("products.txt"); //open products file
        Scanner in = new Scanner(prodsFile); // use Scanner to get file input
        String[] prodinfo = new String[5]; // create array to store a products info
        while (in.hasNextLine()){
            for(int i = 0; i < prodinfo.length; i++){
                String line = in.nextLine();
                String lineTrim = line.trim();

                if (lineTrim.length() == 0 || line == null){ // check if line is not empty
                    prodinfo[i] = null;
                }else{
                    prodinfo[i] = line; // add line to array of product info
                }
            }
            String name = prodinfo[1]; // get product name
            double price = Double.parseDouble(prodinfo[2]); // get product price
            if (prodinfo[0].equals("GENERAL")){ // generate products based on Category and order of product info in temporary Array
                //Category category = Product.Category.GENERAL;
                int stock = Integer.parseInt(prodinfo[3]);
                String id = generateProductId();
                products.add(new Product(name, id, price, stock, Product.Category.GENERAL));
            }else if(prodinfo[0].equals("CLOTHING")){
                //Category category = Product.Category.CLOTHING;
                int stock = Integer.parseInt(prodinfo[3]);
                String id = generateProductId();
                products.add(new Product(name, id, price, stock, Product.Category.CLOTHING));
            }else if(prodinfo[0].equals("BOOKS")){
                //Category category = Product.Category.BOOKS;
                Scanner stocks = new Scanner(prodinfo[3]);
                int paperbackStock = stocks.nextInt();
                int hardcoverStock = stocks.nextInt();
                Scanner info = new Scanner(prodinfo[4]);
                info.useDelimiter(":");
                String title = info.next();
                String author = info.next();
                int year = info.nextInt();
                String id = generateProductId();
                products.add(new Book(name, id, price, paperbackStock, hardcoverStock, title, author, year));
            }else if(prodinfo[0].equals("FURNITURE")){
                //Category category = Product.Category.FURNITURE;
                int stock = Integer.parseInt(prodinfo[3]);
                String id = generateProductId();
                products.add(new Product(name, id, price, stock, Product.Category.FURNITURE));
            }else if(prodinfo[0].equals("COMPUTERS")){
                //Category category = Product.Category.COMPUTERS;
                int stock = Integer.parseInt(prodinfo[3]);
                String id = generateProductId();
                products.add(new Product(name, id, price, stock, Product.Category.COMPUTERS));
            }else if(prodinfo[0].equals("SHOES")){
                //Category category = Product.Category.SHOES;
                String id = generateProductId();
                products.add(new Shoes(name, id, price));
            }
        }
        return products; // return list of products
    }

    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
        try{
            ArrayList<Product> prods = createProducts(); // get returned products list
            for (int i = 0 ; i < prods.size() ; i ++){ // add products to map with Id as Key and Product as value
                products.put(prods.get(i).getId(), prods.get(i));
            }

            Set<String> productIds = products.keySet();
            //System.out.print(productIds);
            for(String productId: productIds){ // add intial value of stats of a product to the map
                Product p = products.get(productId);
                prodOrderStats.put(p,0);
            }

        }
    	catch (IOException e){ // catch IO exception
            e.getMessage();
            System.exit(1);
        }
    	// Create some products. Notice how generateProductId() method is used (BONUS: years added to books)
    	/*products.add(new Product("Acer Laptop", generateProductId(), 989.0, 99, Product.Category.COMPUTERS));
    	products.add(new Product("Apex Desk", generateProductId(), 1378.0, 12, Product.Category.FURNITURE));
    	products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "Ahm Gonna Make You Learn", "T. McInerney"));
    	products.add(new Product("DadBod Jeans", generateProductId(), 24.0, 50, Product.Category.CLOTHING));
    	products.add(new Product("Polo High Socks", generateProductId(), 5.0, 199, Product.Category.CLOTHING));
    	products.add(new Product("Tightie Whities", generateProductId(), 15.0, 99, Product.Category.CLOTHING));
    	products.add(new Book("Book", generateProductId(), 35.0, 4, 2, "How to Fool Your Prof", "D. Umbast"));
    	products.add(new Book("Book", generateProductId(), 45.0, 4, 2, "How to Escape from Prison", "A. Fugitive"));
    	products.add(new Book("Book", generateProductId(), 44.0, 14, 12, "Ahm Gonna Make You Learn More", "T. McInerney"));
    	products.add(new Product("Rock Hammer", generateProductId(), 10.0, 22, Product.Category.GENERAL));
    	*/
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin" ));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));

        //Add Shoes (bonus). Created some shoes using generateCustomerId()
        //products.add(new Shoes("Running Shoes", generateProductId(), 20.0));
        //products.add(new Shoes("Dress Shoes", generateProductId(), 40.0));
    }
    
    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    //public String getErrorMessage() // removed errMsg
    //{
    //	return errMsg;
    //}
    
    public void printAllProducts()
    {

        Set<String> productIds = products.keySet();

        for(String productId: productIds){ // print products from map
            Product p = products.get(productId);
            p.print();
        }
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {

        Set<String> productIds = products.keySet();
        for(String productId: productIds){
            Product p = products.get(productId);
            if(p.getCategory() == Product.Category.BOOKS) {
                //System.out.print(productId);
                p.print();
            }
        }
    }


    // Print all current orders
    public void printAllOrders()
    {
        for (ProductOrder o : orders) //print orders in ArrayList
            o.print();
    }
    // Print all shipped orders
    public void printAllShippedOrders() // print all shipped orders in Arraylist
    {
        for (ProductOrder o : shippedOrders)
            o.print();
    }
    
    // Print all customers
    public void printCustomers() // print customers in customer arrayList
    {
        for (Customer c : customers)
            c.print();
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public void printOrderHistory(String customerId)
    {
      // Make sure customer exists - check using customerId
    	// If customer does not exist, set errMsg String and return false
    	// see video for an appropriate error message string
    	// ... code here

        boolean custExists = false; // set to true if customer exists in customers ArrayList
        for (Customer c : customers){
            if(c.getId().equals(customerId)) { // if the id of a customer matches to given customerID
                custExists = true; // customer exists so set custExists to true and leave loop
                break;
            }
        }

        if(custExists == false){ // if customer does not exist throw exception
            //errMsg = "Customer " + customerId + " Not Found"; // print error message
            //System.out.println(getErrorMessage());
            throw new UnknownCustomerException("Customer " +  customerId + " Not Found"); // throw exception
            //return false; // and return false

        }
    	// Print current orders of this customer
    	System.out.println("Current Orders of Customer " + customerId);
    	// enter code here
        for (ProductOrder o : orders){
            if((o.getCustomer().getId()).equals(customerId)){ // if customer id of order equals given id
                o.print(); // print order of the customer

            }
        }

    	// Print shipped orders of this customer
    	System.out.println("\nShipped Orders of Customer " + customerId);
    	//enter code here
        for (ProductOrder O : shippedOrders){
            if((O.getCustomer().getId()).equals(customerId)){ // if curstomer id of shipped order equals given id
                O.print(); // print shipped order of the customer

            }
        }
    	//return true; // return true since customer exists

    }
    
    public void orderProduct(String productId, String customerId, String productOptions)
    {
        // Check to see if product object with productId exists in array list of products
        // if it does not, set errMsg and return null (see video for appropriate error message string)
        // else get the Product object
        int indexProd = 0; // set variable of product index to zero
        boolean prodExists = false; // check  if prod exists
      
        Product prod = null;
        Set<String> productIds = products.keySet(); // find product in map using Keys
        for(String Id: productIds){
            Product p = products.get(Id);
            if(p.getId().equals(productId)) {
                prod = p; // store existing product
                prodExists = true; // set product exists to True
                break;
            }
            indexProd++; // increment product index until found
        }

    	// First check to see if customer object with customerId exists in array list customers
    	// if it does not, set errMsg and return null (see video for appropriate error message string)
    	// else get the Customer object

        int indexCust = 0; // set variable of customer index to zero
        boolean custExists = false; // check if customer exists
        for(Customer c : customers){
            if(c.getId().equals(customerId)){

                custExists = true; // customer exists
                break;
            }
            indexCust++; // incrament customer index until found
        }
        if (prodExists == false && custExists == false){ // throw exception if product and customer does not exist
            throw new UnknownProductCustomerException("Product " + productId + " and Customer " +  customerId + " Not Found");
        }
        if(prodExists == false){ // throw exception if product does not exist
            //errMsg = "Product " + productId + " Not Found"; // if product id does not exist
            //System.out.println(getErrorMessage()); // print error message and return
            //return null;
            throw new UnknownProductException("Product " + productId + " Not Found");
        }
        if(custExists == false){// throw exception if customer does not exist
            //errMsg = "Customer " +  customerId + " Not Found"; // customer does not exist
            //System.out.println(getErrorMessage()); // print out error message and return
            //return null;
            throw new UnknownCustomerException("Customer " +  customerId + " Not Found");

        }

    	// Check if the options are valid for this product (e.g. Paperback or Hardcover or EBook for Book product)
    	// See class Product and class Book for the method vaidOptions()
    	// If options are not valid, set errMsg string and return null;
        boolean optionsIsValid = false; // set valid option to false

        optionsIsValid = prod.validOptions(productOptions); // store return value of valid option check


        if(!(optionsIsValid)){ // if the option is not valid throw invalid product exceptions with messages to be printed
            if(prod.getCategory() == Product.Category.BOOKS) {
                //errMsg = "Product Book ProductId " + productId + " Invalid Options: " + productOptions;
               // System.out.println(getErrorMessage());
                //return null;

                throw new InvalidProductOptionException("Product Book ProductId " + productId + " Invalid Options: " + productOptions);
            }else if(prod.getCategory() == Product.Category.SHOES){
                //errMsg = "Product Shoe ProductId " + productId + " Invalid Options: " + productOptions;
                //System.out.println(getErrorMessage());
                //return null;
                throw new InvalidProductOptionException("Product Shoe ProductId " + productId + " Invalid Options: " + productOptions);
            }else{
                //errMsg = "Product option is not valid";
                //System.out.println(getErrorMessage());
                //return null;
                throw new InvalidProductOptionException("Product option is not valid");
            }
        }
    	// Check if the product has stock available (i.e. not 0)
    	// See class Product and class Book for the method getStockCount()
    	// If no stock available, set errMsg string and return null
        int stockAvailable = 0; // set stock to 0

        stockAvailable = prod.getStockCount(productOptions); // store result of stock count for the product option


        if(stockAvailable == 0){ // if the stock count is 0 throw exception
            //errMsg = "Stock not available";
            //System.out.println(getErrorMessage());
            //return null;
            throw new OutOfStockException("Stock not available");
        }

      // Create a ProductOrder, (make use of generateOrderNumber() method above)
    	// reduce stock count of product by 1 (see class Product and class Book)
    	// Add to orders list and return order number string
        String orderNum = generateOrderNumber(); // get new Order number
        
        orders.add(new ProductOrder(orderNum, prod, customers.get(indexCust), productOptions)); // create new order
        prod.reduceStockCount(productOptions); // reduce stock count for product option
        int newOrderCount = prodOrderStats.get(prod) + 1; // update number of orders
        prodOrderStats.put(prod, newOrderCount); // update map of stats
        
        //return orderNum;// return order number
        System.out.println("Order #" + orderNum); // print order message
    }
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public void createCustomer(String name, String address)
    {
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	// Repeat this check for address parameter

        if((address == null || address == "") && (name == null || name == "")){ // if address and name are entered empty throw exception
            //errMsg = "Invalid Customer Name and Address";
            //return false;
            throw new InvalidCustomerNameAddressException("Invalid Customer Name and Address");
        }
    	if(name == null || name == ""){ // is name is empty set throw exception
            //errMsg = "Invalid Customer Name";
            //return false;
            throw new InvalidCustomerNameException("Invalid Customer Name");
        }
        if(address == null || address == ""){ // if address is empty set throw exception
            //errMsg = "Invalid Customer Address";
            //return false;
            throw new InvalidCustomerAddressException("Invalid Customer Address");
        }

    	// Create a Customer object and add to array list
        customers.add(new Customer(generateCustomerId(), name , address)); // create a new customer
    	//return true; // return true
    }
    
    public void shipOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return null
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order

        int orderIndex = 0; // set order index to be found to zero
        boolean orderExists = false; // set order exists to false
        for(ProductOrder o : orders){
            if ((o.getOrderNumber()).equals(orderNumber)){ // if order exists set to true
                orderExists = true;
                shippedOrders.add(o); // add order to shipped orders
                orderIndex = shippedOrders.indexOf(o); // get index of added shipped order
                orders.remove(o); // remove from orders
                break; // exit loop
            }
            //orderIndex++;
        }
        if(orderExists == false){ // if order does not exist throw exception
            //errMsg = "Order " + orderNumber + " Not Found";
            //System.out.println(getErrorMessage());
            //return null;
            throw new InvalidOrderNumberException("Order " + orderNumber + " Not Found");
        }else{
            shippedOrders.get(orderIndex).print(); // return the reference to shipped order
        }

    }
    
    /*
     * Cancel a specific order based on order number
     */
    public void cancelOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false

        int orderIndex = 0; // set order index to 0
        boolean orderExists = false; // set order exists to false
        //ProductOrder order = null;
        for(ProductOrder o : orders){
            if((o.getOrderNumber()).equals(orderNumber)) { // if order number exists
                orderExists = true; // set order exists to true
                (o.getProduct()).increaseStockCount(o.getProductOption()); // increase stock count for product option
                int newOrderCount = prodOrderStats.get((o.getProduct())) - 1;
                prodOrderStats.put(o.getProduct(), newOrderCount);
                break;
            }
            orderIndex ++; // incrament until order is found
        }
        if(orderExists == false){ // if order does not exist throw exception
            //errMsg = "Order " + orderNumber + " Not Found";
            //System.out.println(getErrorMessage());
            //return false;
            throw new InvalidOrderNumberException("Order " + orderNumber + " Not Found");
        }

        orders.remove(orderIndex); // remove order and return true

    	//return true;
        System.out.println("Order " + orderNumber + " successfully cancelled");
    }

    class PriceComparator implements Comparator <Product>{ // comparator for products' price
       public int compare(Product p1, Product p2){
           if(p1.getPrice() == p2.getPrice()){
               return 0;
           }else if(p1.getPrice() > p2.getPrice()){
               return 1;
           }else{
               return -1;
           }

       }
   }

    class NameComparator implements Comparator <Product>{ // comparator for product's name
        public int compare(Product p1, Product p2){
            return p1.getName().compareTo(p2.getName());

        }
    }

    class CustomerNameComparator implements Comparator <Customer>{ // comparator for customer names
        public int compare(Customer c1, Customer c2){
            //return c1.getName().compareToIgnoreCase(c2.getName());
            return c1.getName().compareTo(c2.getName());
        }
    }



    // Sort products by increasing price
    public void sortByPrice()
    {
      Set<String> prodIds = products.keySet();
      ArrayList<Product> prods = new ArrayList<Product>();
      for (String id: prodIds){
          Product prod = products.get(id);
          prods.add(prod);
      }
  	  Collections.sort(prods, new PriceComparator());
        for (Product p : prods) {
            p.print();
        }
    }
    
    
    // Sort products alphabetically by product name
    public void sortByName()
    {
        Set<String> prodIds = products.keySet();
        ArrayList<Product> prods = new ArrayList<Product>();
        for (String id: prodIds){
            Product prod = products.get(id);
            prods.add(prod);
        }
  	  Collections.sort(prods, new NameComparator());
        for (Product p : prods) {
            p.print();
        }
    }
    
    
    // Sort customers alphabetically by customer's name
    public void sortCustomersByName()
    {
        Collections.sort(customers, new CustomerNameComparator());
    }
    // new methods
    /*
    add product items to customers cart
     */
    public void addToCart(String productId, String customerId, String productOptions){
        int indexProd = 0; // set variable of product index to zero
        boolean prodExists = false; // check  if prod exists
        Product prod = null;
        Set<String> productIds = products.keySet();
        for(String Id: productIds){
            Product p = products.get(Id);
            if(p.getId().equals(productId)) {
                prod = p; // get existing product
                //prod.setId(p.getId());
                prodExists = true; // set product exists to True
                break;
            }
            indexProd++; // increment product index until found
        }
       
        if(prodExists == false){
            //errMsg = "Product " + productId + " Not Found"; // if product id does not exist
            //System.out.println(getErrorMessage()); // print error message and return
            //return null;
            throw new UnknownProductException("Product " + productId + " Not Found"); //throw exception

        }

        //int indexCust = 0; // set variable of customer index to zero
        boolean custExists = false; // check if customer exists
        Customer cust = new Customer(customerId);
        for(Customer c : customers){
            if(c.getId().equals(customerId)){
                cust = c;
                custExists = true; // customer exists
                break;
            }
            //indexCust++; // incrament customer index until found
        }
        if(custExists == false){
            //errMsg = "Customer " +  customerId + " Not Found"; // customer does not exist
            //System.out.println(getErrorMessage()); // print out error message and return
            //return null;
            throw new UnknownCustomerException("Customer " +  customerId + " Not Found"); // throw unknown customer exception
        }
        
        boolean optionsIsValid = false; // set valid option to false

        optionsIsValid = prod.validOptions(productOptions); // store return value of valid option check
        
        if(!(optionsIsValid)){ // if the option is not valid throw exceptions with messages to be printed
            if((products.get(indexProd)).getCategory() == Product.Category.BOOKS) {
               // errMsg = "Product Book ProductId " + productId + " Invalid Options: " + productOptions;
                //System.out.println(getErrorMessage());
                //return null;
                throw new InvalidProductOptionException("Product Book ProductId " + productId + " Invalid Options: " + productOptions);
            }else if((products.get(indexProd)).getCategory() == Product.Category.SHOES){
                //errMsg = "Product Shoe ProductId " + productId + " Invalid Options: " + productOptions;
                //System.out.println(getErrorMessage());
                //return null;
                throw new InvalidProductOptionException("Product Shoe ProductId " + productId + " Invalid Options: " + productOptions);
            }else{
                //errMsg = "Product option is not valid";
                //System.out.println(getErrorMessage());
                //return null;
                throw new InvalidProductOptionException("Product option is not valid");
            }
        }
        int stockAvailable = 0; // set stock to 0

        stockAvailable = prod.getStockCount(productOptions); // store result of stock count for the product option
        
        if(stockAvailable == 0){ // if the stock count is 0 throw exception
            //errMsg = "Stock not available";
            //System.out.println(getErrorMessage());
            //return null;
            throw new OutOfStockException("Stock not available");
        }else{
            CartItem item = new CartItem(prod, productOptions); // create item
            //item.print();
            Cart c = cust.getCart(); // get cart of customer
            c.addToCart(item); // add item to cart
            System.out.println("Added Item to Cart of Customer " + customerId); // print message
        }

    }
    
    /*
    prints items in a customer's cart
     */
    public void printCart(String customerId)
    {
        boolean custExists = false; // set to true if customer exists in customers ArrayList
        Customer cust = new Customer(customerId); // set empty customer to store customer if exists
        for (Customer c : customers){
            if(c.getId().equals(customerId)) { // if the id of a customer matches to given customerID
                cust = c; // store existing customer
                custExists = true; // customer exists so set custExists to true and leave loop
                break;
            }
        }
        
        if(custExists == false){ // if customer does not exist throw exception
            //errMsg = "Customer " + customerId + " Not Found"; // print error message
            //System.out.println(getErrorMessage());
            //return false; // and return false
            throw new UnknownCustomerException("Customer " +  customerId + " Not Found");

        }
        // Print current orders of this customer
        System.out.println("Current Cart Items of Customer " + customerId);

        ArrayList<CartItem> cart = cust.getCart().getCartList();
        for(CartItem item: cart){ // print items in customers cart
            item.print();
        }

        //return true; // return true since customer exists

    }

    /*
    removes item from a customers cart
     */
    public void removeItem(String productId, String customerId){
        //int indexCust = 0; // set variable of customer index to zero
        boolean custExists = false; // check if customer exists
        Customer cust = null;
        for(Customer c : customers){
            if(c.getId().equals(customerId)){
                cust = c;
                custExists = true; // customer exists
                break;
            }
            //indexCust++; // incrament customer index until found
        }
        if(custExists == false){// customer does not exist throw exception
            //errMsg = "Customer " +  customerId + " Not Found"; 
            //System.out.println(getErrorMessage()); // print out error message and return
            //return null;
            throw new UnknownCustomerException("Customer " +  customerId + " Not Found");

        }

        //int indexProd = 0; // set variable of product index to zero
        boolean prodExists = false; // check  if prod exists
        //Product prod = null;
        String ID = "";
        Set<String> productIds = products.keySet();
        for(String Id: productIds){
            //Product p = products.get(Id);
            if(Id.equals(productId)) {
                ID = Id; // get id of product
                //prod.print();
                //prod.setId(p.getId());
                prodExists = true; // set product exists to True
                break;
            }
            //indexProd++; // increment product index until found
        }
      
        if(prodExists == false){
            //errMsg = "Product " + productId + " Not Found"; // if product id does not exist
            //System.out.println(getErrorMessage()); // print error message and return
            //return null;
            throw new UnknownProductException("Product " + productId + " Not Found"); // throw exception Unknown product

        }
        
        Cart cart = cust.getCart(); // get customers cart
        ArrayList<CartItem> custCart = cart.getCartList(); // get list from the cart
        int i = 0;
        for (CartItem item: custCart){
            //item.print();
            if(item.getCartItem().getId().equals(ID)){ // break from loop if product is the item
                break;
            }
            i++; // get index of item in array
        }
        cart.removeFromCart(i); // remove item from cart
        System.out.println("Removed Item from Cart of Customer " + customerId);


    }

    /*
    orders all items in customers cart and empties it
     */
    public void orderItems(String customerId){


        //int indexCust = 0; // set variable of customer index to zero
        boolean custExists = false; // check if customer exists
        Customer cust = new Customer(customerId);
        for(Customer c : customers){
            if(c.getId().equals(customerId)){
                cust = c;
                custExists = true; // customer exists
                break;
            }
            //indexCust++; // incrament customer index until found
        }
        if(custExists == false){
            //errMsg = "Customer " +  customerId + " Not Found"; // customer does not exist
            //System.out.println(getErrorMessage()); // print out error message and return
            //return null;
            throw new UnknownCustomerException("Customer " +  customerId + " Not Found"); // throw exception unknown customer

        }
        ArrayList<CartItem> cart = cust.getCart().getCartList(); // get cart list of customer
        
        for(CartItem item: cart){
            //item.print();
            String orderNum = generateOrderNumber(); //create order number and get product from cart
            Product prod = item.getCartItem();

            int stockAvailable = prod.getStockCount(item.getProductOption()); // store result of stock count for the product option
            if(stockAvailable == 0){ // if the stock count is 0 print error message and return
                //errMsg = "Stock not available";
                //System.out.println(getErrorMessage());
                //return null;
                throw new OutOfStockException("Stock not available for Product " + prod.getId() ); // throw out of stock exception
            }

            orders.add(new ProductOrder(orderNum, prod, cust, item.getProductOption())); //add product order
            int newOrderCount = prodOrderStats.get(prod) + 1;
            prodOrderStats.put(prod, newOrderCount); // update number of orders of product
            prod.reduceStockCount(item.getProductOption()); // reduce stock count
            //return orderNum;// return order number

        }
        cust.getCart().getCartList().clear(); // clear customers cart
       // String orderNum = generateOrderNumber(); // get new Order number
        System.out.println("All items with stock ordered from cart of Customer "  + customerId); // print message

    }

    class OrderedComparator implements Comparator <Product>{ // comparator for products' price
        public int compare(Product p1, Product p2){
            int num1 = prodOrderStats.get(p1);
            int num2 = prodOrderStats.get(p2);
            if(num1 == num2){
                if (num1 == 0){
                    return p1.getName().compareTo(p2.getName()); // if nums are 0 sort by name
                }else{
                    return 0; 
                }
            }else if(num1 < num2){
                return 1;
            }else{
                return -1;
            }
        }
    }
    /*
    prints stats of products
     */
    public void productOrderStatistics(){
        Set<Product> prods = prodOrderStats.keySet();
        ArrayList<Product> ProdsList = new ArrayList<Product>(); // create list of products to be sorted
        for (Product p: prods){
            ProdsList.add(p); // add products to temporary list
        }
        
        Collections.sort(ProdsList, new OrderedComparator()); // sort product stats
        for (Product p: ProdsList){ // print products with number of orders
            int value = prodOrderStats.get(p);
            //p.print();
            System.out.printf("\nId: %-5s Name: %-20s  # of Orders: %-10d", p.getId(), p.getName(), value);
           
        }


    }
    public void getOptions(String Id){ // print product options based on ID given (needed to be changed if more products are added

        //int id = Integer.parseInt(Id);
        Id = Id.trim();
        if (Id.equals("702") || Id.equals("706") || Id.equals("707") || Id.equals("708") || Id.equals("711")){ // book ID's
            System.out.print("\nBook Format: Paperback, Hardcover, EBook "); //print book options
        }else if (Id.equals("700") || Id.equals("701") || Id.equals("703") || Id.equals("704") || Id.equals("705")|| Id.equals("709")){
            System.out.print("\n~Press ENTER to skip~ "); // tell user to skip options for other product id's

        }else if (Id.equals("710")){ // print shoe options from shoe ID
            System.out.print("\nShoe Size: \"6\" \"7\" \"8\" \"9\" \"10\" ");
            System.out.print("\nShoe Color: \"Black\" \"Brown\" ");
        }

    }


}


// exception classes for custom exceptions to be thrown
 class UnknownCustomerException extends RuntimeException{
    public UnknownCustomerException(){}

    public UnknownCustomerException(String message){
        super(message);
    }
}

 class UnknownProductException extends RuntimeException{
    public UnknownProductException(){}

    public UnknownProductException(String message){
        super(message);
    }
}

 class InvalidProductOptionException extends RuntimeException{
    public InvalidProductOptionException(){}

    public InvalidProductOptionException(String message){
        super(message);
    }
}

 class OutOfStockException extends RuntimeException{
    public OutOfStockException(){}

    public OutOfStockException(String message){
        super(message);
    }
}

class InvalidCustomerNameException extends RuntimeException{
    public InvalidCustomerNameException(){}

    public InvalidCustomerNameException(String message){
        super(message);
    }
}

class InvalidCustomerNameAddressException extends RuntimeException{
    public InvalidCustomerNameAddressException(){}

    public InvalidCustomerNameAddressException(String message){
        super(message);
    }
}

class InvalidCustomerAddressException extends RuntimeException{
    public InvalidCustomerAddressException(){}

    public InvalidCustomerAddressException(String message){
        super(message);
    }
}

class InvalidOrderNumberException extends RuntimeException{
    public InvalidOrderNumberException(){}

    public InvalidOrderNumberException(String message){
        super(message);
    }
}
class UnknownProductCustomerException extends RuntimeException{
    public UnknownProductCustomerException(){}

    public UnknownProductCustomerException(String message){
        super(message);
    }
}
