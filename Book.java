// Name: Roxie Reginold       Student Number: 501087897
/* A book IS A product that has additional information - e.g. title, author

 	 A book also comes in different formats ("Paperback", "Hardcover", "EBook")
 	 
 	 The format is specified as a specific "stock type" in get/set/reduce stockCount methods.

*/
public class Book extends Product
{
  private String author;
  private String title;
  private int year; //added year
  // Stock related information NOTE: inherited stockCount variable is used for EBooks
  int paperbackStock;
  int hardcoverStock;
// adding year
  
  public Book(String name, String id, double price, int paperbackStock, int hardcoverStock, String title, String author, int year)
  {
  	 // Make use of the constructor in the super class Product. Initialize additional Book instance variables. 
  	 // Set category to BOOKS
      super(name, id, price, 100000, Product.Category.BOOKS); // intilize intance variable in super class
      this.title = title; // initilize other given instance variable
      this.author = author;
      this.paperbackStock = paperbackStock;
      this.hardcoverStock = hardcoverStock;
      this.year = year;
  }


  // Check if a valid format  
  public boolean validOptions(String productOptions)
  {
  	// check productOptions for "Paperback" or "Hardcover" or "EBook"
  	// if it is one of these, return true, else return false

  if (productOptions.equalsIgnoreCase("Paperback") || productOptions.equalsIgnoreCase("Hardcover") || productOptions.equalsIgnoreCase("EBook") ){
      return true;
  }
  	return false;
  }
  
  // Override getStockCount() in super class.
  public int getStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and return) the number of stock for "Paperback" etc
  	// Use the variables paperbackStock and hardcoverStock at the top. 
  	// For "EBook", use the inherited stockCount variable.
    if (productOptions.equals("Paperback")){
        return paperbackStock;
    }else if (productOptions.equals("Hardcover")){
        return hardcoverStock;
    }else if(productOptions.equals("EBook")){
        //super.getStockCount("EBook");
        return super.getStockCount("EBook");
    }
    return 0;
	}
  
  public void setStockCount(int stockCount, String productOptions)
	{
    // Use the productOptions to check for (and set) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
        if (productOptions.equals("Paperback")){
            this.paperbackStock =  stockCount;
        }else if (productOptions.equals("Hardcover")){
            this.hardcoverStock =  stockCount;
        }else if(productOptions.equals("EBook")) {
            //super.getStockCount("EBook");
            super.setStockCount(stockCount, productOptions);
        }
	}
  
  /*
   * When a book is ordered, reduce the stock count for the specific stock type
   */
  public void reduceStockCount(String productOptions)
	{
  	// Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
   	// Use the variables paperbackStock and hardcoverStock at the top. 
   	// For "EBook", set the inherited stockCount variable.
        if (productOptions.equals("Paperback")){
            paperbackStock --;
        }else if (productOptions.equals("Hardcover")){
            hardcoverStock -- ;
        }else if(productOptions.equals("EBook")){
            //super.getStockCount("EBook");
            int stock = super.getStockCount(productOptions) -1;
            super.setStockCount(stock, productOptions);
        }
	}
  /*
   * Print product information in super class and append Book specific information title and author
   */
  public void print()
  {
  	// Replace the line below.
  	// Make use of the super class print() method and append the title and author info. See the video
      super.print();
      System.out.print(" Book Title: " + title + " Author: " + author + " Year: " + year); // add additional book information when printing
  }


}
