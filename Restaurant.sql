create database if not exists ‘mysql’;
create table `mysql`.Menu
(
	Id INT not null primary key,
	Name_Dish VARCHAR(200) not null,
	Category VARCHAR(200) not null,
	Cost INT not null,
	Availability VARCHAR(200) not null,
	Quantity INT not null
);
INSERT INTO Menu VALUES( 9, "RedVelvet Cupcakes","Dessert",5,"AllDay",1);		
INSERT INTO Menu VALUES( 8, "Paleo Jelly Donut Cupcakes","Dessert",8,"AllDay",2);	
INSERT INTO Menu VALUES( 7, "Real Pistachio Cupcakes","Dessert",10,"AllDay",1);		
INSERT INTO Menu VALUES( 6, "Grilled Portobellos","MainCourse",16,"dinner",2);		
INSERT INTO Menu VALUES( 5, "Mexican Sweet Potato Veggie Medley","MainCourse",20,"lunch",3);						
INSERT INTO Menu VALUES( 4, "Tomato Galette","MainCourse",15,"breakfast",4);		
INSERT INTO Menu VALUES( 3, "Fried Ravioli","Appetizer",11,"breakfast",1);		
INSERT INTO Menu VALUES( 2, "Mini Manchego Tart","Appetizer",12,"lunch",3);
INSERT INTO Menu VALUES( 1, "Stuffed Cherry Peppers","Appetizer",10,"dinner",2);
create table `mysql`.Restaurant_Tables
(
	TableNo INT not null primary key,
	Availability VARCHAR(3) not null,
	NoSeats INT default 0 not null,
	ServiceType VARCHAR(200)
);
INSERT INTO Restaurant_Tables VALUES(12,"no",4,"Buffet Dining");			
INSERT INTO Restaurant_Tables VALUES(11,"yes",6,"Fine Dining");				
INSERT INTO Restaurant_Tables VALUES(10,"no",8,"Buffet Dining");			
INSERT INTO Restaurant_Tables VALUES(9,"no",6,"Casual Dining");				
INSERT INTO Restaurant_Tables VALUES(8,"yes",4,"Buffet Dining");			
INSERT INTO Restaurant_Tables VALUES(7,"no",4,"Fine Dining");				
INSERT INTO Restaurant_Tables VALUES(6,"yes",2,"Fine Dining");				
INSERT INTO Restaurant_Tables VALUES(5,"no",8,"Casual Dining");				
INSERT INTO Restaurant_Tables VALUES(4,"yes",6,"Casual Dining");			
INSERT INTO Restaurant_Tables VALUES(3,"no",4,"Buffet Dining");				
INSERT INTO Restaurant_Tables VALUES(2,"yes",2,"Casual Dining");			
INSERT INTO Restaurant_Tables VALUES(1,"yes",4,"Fine Dining");				
											
												 
