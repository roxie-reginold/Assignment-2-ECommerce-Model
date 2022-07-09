Name: Roxie Reginold              
Student Number: 501087897
Does the program/files compile: Yes

~How the Shoe class is implemented~
- the array is formated according to the sizes, since there are 5 size options the array is size 10 to store the stock count
- every size takes up 2 fixed spaces in the array, which are the size number for the even indicies and the stock count for the odd indicies
for example [6,1000]  represents the size in array[0] and 1000 in array[1] is the stock for the size
- there are 2 arrays for the 2 colours brown and black

~ How the CART and CARTITEM class's work~
- the CARTITEM class extends from PRODUCT as printing the item out is the same with added option
	- it models a item which is a product with a product option
- the CART class models a cart using an Arraylist
	- its an object that allows each customer to have thier own and also remove and add items when needed
	- also allows to get the actual list of items

~How the ADDTOCART action works~
- the terminal takes in user imput such as productId and customerId, then I created a new method in ECommerceSystem.java called getOptions()
that takes in the productId and prints out the possible valid options for that product (if you would want to add shoes or other products to fie check getOptions() to update or add the product ID to the conditions to ensure the options are printed out
- after printing out the options, the user can input options based on what is displayed, and it then continues to addtoCart()

~How the REMCARTITEM action works~
- after inputting Product ID and Customer ID, the removeItem() method only removes the first product that matches the Product ID
- if the customer has 2 of the same products in their cart, it should only remove one copy

~Implementing products and product order stats with Maps~
- products is using a TreeMap to ensure all products are printed in order of ID
- prodOrderStats uses a HashMap to get the Product stats when needed (like for STATS function)
	- this prodOrderStats HashMap stores the Product as a key and the the number of orders as the value

What aspects of the program works: Everything (Bonus not implemented)

What aspects of the program does not work: All commands should work

Notes: 

- I am not sure how to add extra shoe products to the products file (when I do it gives me a error), but it should work 
(try add something for the blank lines since the irellevant data will be disgarded anyway ~ see createProducts() in ECommerceSystem.java)
- Adding a product only works if I add it before the last product in the file (see added shoe product in products.txt file)
- if adding a product to the txt file after the last product, it would only work if the black lines are filed with something, example:
			SHOES
			Running Shoes
			20.0
			0
			0
- STATS: if the number of orders are the same for products (not 0), then it might be ordered based on the recent item added to cart/ ordered (did not focus on this detail, just made sure that orders are presented from greatest to least)
	- if all of the number of orders are 0, then it prints the products by name