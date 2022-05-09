PROJECT REQUIREMENT
-input.xml file and the photos attached must be in the same folder as the project in order for it to run efficiently.
-The jar file must be also in the same folder as the input.xml file and the photos.

NOTE
The uml diagram is in the project folder made using UMLET. There is also a screenshot of the diagram to make sure
it is viewed by the TAs on any PC.

PROJECT OVERVIEW
-First, the user is prompted to enter his username and password to log in
-If the user is a new CLIENT, he can sign up to add his data to the xml file then sign in afterwards.
-CLIENT: If the user is a client, he moves to the scene where he chooses the specifications of his table
	 (number of seats, smoking or no smoking, time of reservation) if a table is avaliable with the 
	 the same requirements, he moves to the menu to make his order. After he checks out, the reservation
	 is saved with his order,table number and under his name in the xml file.
	 The client can reserve more than one table under his name with different orders.
-MANAGER: If the user is the manager, he moves to his scene where he can view all details about the reservations made
          and the total income of the restaurant.
-WAITER: If the user is a waiter, he moves to his scene where he can view the reservations made but only with
         the client name, table number and time.
-COOK:   If the user is a cook, he moves to his scene where he can view the orders of each table and the time
         the order should be ready by.

TEAMWORK
We both collaborated on the reading of the xml file and the uml diagram
Student.1(6221) designed the client and the manager part and edited the gui.
Student.2(6309) designed the cook and the waiter part and edited the gui.