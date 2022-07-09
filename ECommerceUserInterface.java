// Name: Roxie Reginold       Student Number: 501087897
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
//import org.apache.commons.lang.WordUtils;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			try{


			if (action == null || action.equals(""))
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts();
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks();
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				
					String name = "";
					String address = "";

					System.out.print("Name: "); // get name from keyboard
					if (scanner.hasNextLine())
						name = scanner.nextLine();

					System.out.print("\nAddress: "); // get address from keyboard
					if (scanner.hasNextLine())
						address = scanner.nextLine();

					amazon.createCustomer(name, address);
				
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";

					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine()) // get order number from keyboard
						orderNumber = scanner.nextLine();
					// Ship order to customer (see ECommerceSystem for the correct method to use

				    amazon.shipOrder(orderNumber); // get product order

					//if (s != null){
					//	s.print(); // print reference to product order if the shipOrder method is successful
					//}

			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
				amazon.printOrderHistory(customerId);


			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
			  // Get product Id from scanner
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
			  // Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Order the product. Check for valid orderNumber string and return exception messaged when caught
					amazon.orderProduct(productId, customerId, "");

				// Print Order Number string returned from method in ECommerceSystem
				//if (orderNum != null){ // order is successful print order number
				//	System.out.println("Order #" + orderNum);
				//}

			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book format and store in options string
				if (scanner.hasNextLine())
					options = scanner.nextLine();

				// Order product. Check for error mesage set in ECommerceSystem
				amazon.orderProduct(productId, customerId, options);
				// Print order number string if order number is not null
				//if (orderNum != null) { // if order is successful print order number
				//	System.out.println("Order #" + orderNum);
				//}
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options	
				if (scanner.hasNextLine())
					options = scanner.nextLine();
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if (scanner.hasNextLine())
					options += " " + scanner.nextLine();

				//order shoes
				amazon.orderProduct(productId, customerId, options);
				// Print order number string if order number is not null
				//f (orderNum != null) {
				//	System.out.println("Order #" + orderNum);
				//}
			}


			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();
				// cancel order. Check for error
				amazon.cancelOrder(orderNumber);

			}
			else if (action.equalsIgnoreCase("PRINTBYPRICE")) // sort and products by price
			{
				amazon.sortByPrice();
			}
			else if (action.equalsIgnoreCase("PRINTBYNAME")) // sort and products by name (alphabetic)
			{
				amazon.sortByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort customers by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			//new actions
			else if (action.equalsIgnoreCase("ADDTOCART")) // adds product to customer's cart
			{

				String productId = "";
				String customerId = "";
				String option = "";
				System.out.print("Product Id: ");
				// Get product Id from scanner
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				amazon.getOptions(productId); // print valid options for product
				System.out.print("\nOption (example: 6 Black, Paperback): ");
				// Get option from scanner
				if (scanner.hasNextLine())
					option = scanner.nextLine();

				// add products to cart.
				amazon.addToCart(productId, customerId, option);

				// Print Order Number string returned from method in ECommerceSystem
				//if (ordered != null){ // order is successful print order number
				//	System.out.println(ordered);
				//}


			}
			else if (action.equalsIgnoreCase("REMCARTITEM")) // remove item from cart
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
				// Get product Id from scanner
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				// remove product from customers cart.
				amazon.removeItem(productId, customerId);

				// Print Order Number string returned from method in ECommerceSystem
				//if (confirmation != null){ // order is successful print order number
				//	System.out.println(confirmation);
				//}
			}
			else if (action.equalsIgnoreCase("PRINTCART")) // print customers cart
			{
				String customerId = "";
				System.out.print("\nCustomer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				amazon.printCart(customerId); // print cart from given customer ID

			}
			else if (action.equalsIgnoreCase("ORDERITEMS")) // order items in customers cart
			{
				String customerId = "";
				System.out.print("\nCustomer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();

				amazon.orderItems(customerId); // order items in given customers cart
				 //if(confirmation != null){
				//	 System.out.println(confirmation);
				 //}
			}
			else if (action.equalsIgnoreCase("STATS")) // print number of orders for each product
			{
				amazon.productOrderStatistics(); // print products with number of orders
			}
			
		} // end of try
		// catching expections and printing messages
		catch(UnknownCustomerException e){
			System.out.println(e.getMessage());
		}catch(UnknownProductException e){
				System.out.println(e.getMessage());
		}catch(InvalidProductOptionException e){
				System.out.println(e.getMessage());
		}catch(OutOfStockException e){
				System.out.println(e.getMessage());
		}catch(InvalidCustomerNameException e){
				System.out.println(e.getMessage());
		}catch(InvalidCustomerNameAddressException e){
				System.out.println(e.getMessage());
		}catch(InvalidCustomerAddressException e){
				System.out.println(e.getMessage());
		}catch(InvalidOrderNumberException e){
				System.out.println(e.getMessage());
		}catch(UnknownProductCustomerException e) {
				System.out.println(e.getMessage());
		}
		finally {
				if (!(action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT")))
			
					System.out.print("\n>");// continue user input if user does not exit
		}

		}
	}
}
