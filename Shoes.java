// Name: Roxie Reginold       
    A Shoe is a Product with size and colour options
 */
public class Shoes extends Product{ // create a shoe class that is a sub class of Product

    // initialize colour stock option arrays with size stocks
     private final int six = 6;
     private final int seven = 7;
     private final int eight = 8;
     private final int nine = 9;
     private final int ten = 10;
     private int[] blackStock = {six,100000,seven,100000,eight,100000,nine,100000,ten,100000};
     private int[] brownStock = {six,100000,seven,100000,eight,100000,nine,100000,ten,100000};
    // each size takes up 2 spaces in the arrays

    public Shoes(String name, String id, double price){
        super(name, id, price, 0, Product.Category.SHOES); // use super class to initialize instance variables

    }
     // Check if a valid format
     public boolean validOptions(String productOptions)
     {
        // check if product option is a proper size and colour
         String Options[] = productOptions.split(" "); //split string into elements of an array

         if(Options.length != 2){ // iff the number of elements is greater than 2 return false
             return false;
         }
         String colour = Options[1];
         int size = Integer.parseInt(Options[0]);
         if ((colour.equals("Black") || colour.equals("Brown")) && (size >= 6 && size<=10) ){
             return true; // return true if given string is a valid format for shoes
         }else{
             return false; // return false if not a valid format
         }

     }

     public int getStockCount(String productOptions)
     {

         String Options[] = productOptions.split(" ");
         String  colour = Options[1];
         int size = Integer.parseInt(Options[0]);
         if(colour.equals("Black")){ // return the size stocks from the aray of Black shoes
             if(size== 6){
                 return blackStock[1];
             }else if (size == 7){
                 return blackStock[3];
             }else if (size == 8){
                 return blackStock[5];
             }else if (size == 9){
                 return blackStock[7];
             }else if (size == 10){
                 return blackStock[9];
             }
         }
         if(colour.equals("Brown")){ // return the size stocks from the array of Brown shoes
             if(size== 6){
                 return brownStock[1];
             }else if (size == 7){
                 return brownStock[3];
             }else if (size == 8){
                 return brownStock[5];
             }else if (size == 9){
                 return brownStock[7];
             }else if (size == 10){
                 return brownStock[9];
             }
         }
        return 0;
     }

     public void setStockCount(int stockCount, String productOptions)
     {

         String Options[] = productOptions.split(" ");
         String  colour = Options[1];
         int size = Integer.parseInt(Options[0]);
         if(colour.equals("Black")){ // set the new stock Counts for the sizes in the array for Black shoes
             if(size== 6){
                 blackStock[1] = stockCount;
             }else if (size == 7){
                 blackStock[3]= stockCount;
             }else if (size == 8){
                 blackStock[5]= stockCount;
             }else if (size == 9){
                 blackStock[7]= stockCount;
             }else if (size == 10){
                 blackStock[9]= stockCount;
             }
         }
         if(colour.equals("Brown")){// set the new stock Counts for the sizes in the array for Brown shoes
             if(size== 6){
                 brownStock[1] = stockCount;
             }else if (size == 7){
                 brownStock[3]= stockCount;
             }else if (size == 8){
                 brownStock[5]= stockCount;
             }else if (size == 9){
                 brownStock[7]= stockCount;
             }else if (size == 10){
                 brownStock[9]= stockCount;
             }
         }
     }

     public void reduceStockCount(String productOptions)
     {
         // Use the productOptions to check for (and reduce) the number of stock for "Paperback" etc
         // Use the variables paperbackStock and hardcoverStock at the top.
         // For "EBook", set the inherited stockCount variable.
         String Options[] = productOptions.split(" ");
         String  colour = Options[1];
         int size = Integer.parseInt(Options[0]); // decrease the stock Counts for the sizes in the array for Black shoes
         if(colour.equals("Black")){
             if(size== 6){
                 blackStock[1] = blackStock[1]--;
             }else if (size == 7){
                 blackStock[3]= blackStock[3]--;
             }else if (size == 8){
                 blackStock[5]= blackStock[5]--;
             }else if (size == 9){
                 blackStock[7]= blackStock[7]--;
             }else if (size == 10){
                 blackStock[9]= blackStock[9]--;
             }
         }
         if(colour.equals("Brown")){// decrease the stock Counts for the sizes in the array for Brown shoes
             if(size== 6){
                 brownStock[1] =brownStock[1]--;
             }else if (size == 7){
                 brownStock[3]= brownStock[3]--;
             }else if (size == 8){
                 brownStock[5]= brownStock[5]--;
             }else if (size == 9){
                 brownStock[7]= brownStock[7]--;
             }else if (size == 10){
                 brownStock[9]= brownStock[9]--;
             }
         }
     }

}







