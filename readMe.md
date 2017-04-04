README

1. String to Int Conversion - (by Apurva Alekar)
	java file - StringToInt.java
	steps to run : 
		1.	javac StringToInt.java
		2.	java StringToInt
	
2. License Keys - (by Sarvesh Pandit)
	java File - licenseKeys.java
	steps to run : 
		1.	javac licenseKeys.java
		2.	java licenseKeys
		
3.Most Appropriate Facility  
	1.two supporting CSV files needed.(CopartCustomerLocation.csv , copartLocation.csv)
	2.CopartCustomerLocation.csv contains customer ids and their respective Copart locations
	3.copartLocation.csv contains city and state of all copart facilites.
	4. We have used ZipCodeAPI to compare the distances and getting the zipCodes from city and vice Versa.
	5. The program needs JSON file to parse response from the API.
	
	4.java Files - NearestCopartLocation,ZipCodeAPIs
	5.Place 2 files 
	6.steps to run : 
		1.	javac NearestCopartLocation.java 	
		2.	javac ZipCodeApi.java
		3. java NearestCopartLocation
		
Problem : The CSV(CopartLocation File) file we are giving as input consists of <City,State> format. The API we are using to get zip code given city and state are giving null for some states.
So the program is not moving forward.