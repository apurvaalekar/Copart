

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NearestCopartLocation {
	
	Map addressMap;
	Map customerMap;
	Map nearLocation;
	NearestCopartLocation()
	{
		nearLocation=new HashMap<String,String>();
	}
	class Location
	{
		String city;
		String state;    
		
		
		public Location(String city,String state) { 
			this.city=city;
			this.state=state;
			
		}
		
		public String toString()
		{
			return this.state+" "+this.state+" ";
		}
	}
	
	public static void main(String args[])throws Exception
	{
		String customerID;
		String zipCode;
		Scanner in=new Scanner(System.in);
		NearestCopartLocation ncl=new NearestCopartLocation();
		ncl.addressMap=ncl.getLocationInputandPutinMap(new File("Location.csv"));
		ncl.customerMap=ncl.getCustomerLocationMap(new File("CopartCustomerLocation.csv"));
		System.out.println("Enter Customer ID and Zip code");
		while(in.hasNext())
		{
		customerID=in.nextLine();
		zipCode=in.nextLine();
		
	    System.out.println(ncl.getNearestLocation(customerID, zipCode));
		System.out.println("Enter Customer ID and Zip code");
		}
		
	}

	public Location getNearestLocation(String customerID,String zipCode)
	{
		ZipCodeApi zipCodeApi=new ZipCodeApi();
		//check if customer's data is already present then suggest the location directly.
		if(customerMap.containsKey(customerID))
			return (Location)addressMap.get(customerMap.get(customerID));
		//or if the location is already added in database
		if(nearLocation.containsKey(zipCode))
		{
			return (Location)addressMap.get(nearLocation.get(zipCode));
		}
		//otherwise calculate the distance.
		double distanceMin=Double.MAX_VALUE;
		String minZipCode=null;
		System.out.println("Fetching from API............");
		for(Object zip: addressMap.keySet())
		{
			//call to API to copmare two APIs
			double value=zipCodeApi.getDistance(zipCode, (String)zip); 
			//minimum calculation.
			if(value<distanceMin)
			{
				distanceMin=value;
				minZipCode=(String)zip;
			}
		}
		nearLocation.put(zipCode, minZipCode);
		return (Location)addressMap.get(minZipCode);
	}
	
	public Map getCustomerLocationMap(File f)throws Exception
	{
		Scanner s=new Scanner(f);
		HashMap<String,String> hm=new HashMap<>();
		while(s.hasNextLine())
		{
			try
			{
				String now=s.nextLine();
				String str[]=now.split(",");
				hm.put(str[0], str[1]);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return hm;
	}

	public Map getLocationInputandPutinMap(File f) throws FileNotFoundException
	{
		Scanner s=new Scanner(f);
		HashMap<String,Location> hm=new HashMap<>();
		while(s.hasNextLine())
		{
			try
			{
			String now=s.nextLine();
			String str[]=now.split(",");
			Location copartLoc=new Location(str[0], str[1]);
			ZipCodeApi api = new ZipCodeApi();
			String zipcode = api.getZipCodeFromPlace(str[0], str[1]);
			
			hm.put(zipcode, copartLoc);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return hm;
	}
}
