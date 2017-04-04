

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
		NearestCopartLocation nearCopart=new NearestCopartLocation();
		nearCopart.addressMap=nearCopart.getLocationInputandPutinMap(new File("Location.csv"));
		nearCopart.customerMap=nearCopart.getCustomerLocationMap(new File("CopartCustomerLocation.csv"));
		System.out.println("Enter Customer ID and Zip code");
		while(in.hasNext())
		{
		customerID=in.nextLine();
		zipCode=in.nextLine();
		if(customerID.length()>1 && zipCode.length()>1)
		     System.out.println(nearCopart.getNearestLocation(customerID, zipCode));
		System.out.println("Enter Customer ID and Zip code");
		}
		
	}

	public Location getNearestLocation(String customerID,String zipCode)
	{
		ZipCodeApi zipCodeApi=new ZipCodeApi();
		if(customerMap.containsKey(customerID))
			return (Location)addressMap.get(customerMap.get(customerID));
		if(nearLocation.containsKey(zipCode))
		{
			return (Location)addressMap.get(nearLocation.get(zipCode));
		}
		
		double distanceMin=Double.MAX_VALUE;
		String minZipCode=null;
		System.out.println("Fetching from API............");
		for(Object zip: addressMap.keySet())
		{
			double value=zipCodeApi.getDistance(zipCode, (String)zip); 
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
				//just skip misMatched ones
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
				//just skip mismatched locations
			}
		}
		
		return hm;
	}
}
