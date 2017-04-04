

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;



public class ZipCodeApi {
	public static void main(String[] args) {

		ZipCodeApi zipCode=new ZipCodeApi();
		Scanner in = new Scanner(System.in);
		String zip=in.next();
		System.out.println(zipCode.getPlaceFromZipCode(zip));
		in.close();
	}
	
	
	public double getDistance(String zip1,String zip2)
	{
		String Key="xXi2vsNnZAP7s3rDvqoXa58JrJdeogKVd2YJvM6unWCPZ3tvuSsEXBnpkADJRnE5";
		String url="https://www.zipcodeapi.com/rest/"+Key+"/distance.json/"+zip1+"/"+zip2+"/km";
		String inputLine;
		try {
			URL ur= new URL(url);
			HttpURLConnection con = (HttpURLConnection)ur.openConnection();
			con.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			inputLine = reader.readLine();
			JSONObject json =new JSONObject(inputLine);
			String distance=json.getString("distance");
			return Double.parseDouble(distance);
		} catch (Exception e) {
			return 99999999.99;
		}
	}
	public String getZipCodeFromPlace(String city,String state)
	{
		String clientKey="qtXFmM393lnHH9Rx3u3xQNo5ynROJQzajwPGjTPmed3WnUurpxYflmlUKcIt2hEm";

		String url = "https://www.zipcodeapi.com/rest/"+clientKey+"/city-zips.json/" + city+"/"+state ;
		
		try {
			URL ur= new URL(url);
			HttpURLConnection con = (HttpURLConnection)ur.openConnection();
			
			con.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			inputLine = reader.readLine();
			JSONObject json =new JSONObject(inputLine);
			JSONArray zipcodes = json.getJSONArray("zip_codes");
			String zipcode = (String) zipcodes.get(0);
			
			return zipcode;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getPlaceFromZipCode(String zipCode)
	{
		String clientKey="qtXFmM393lnHH9Rx3u3xQNo5ynROJQzajwPGjTPmed3WnUurpxYflmlUKcIt2hEm";

		String url = "https://www.zipcodeapi.com/rest/"+clientKey+"/info.json/" + zipCode + "/radians";
		
		try {
			URL ur= new URL(url);
			HttpURLConnection con = (HttpURLConnection)ur.openConnection();
			
			con.setRequestMethod("GET");
			
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			String inputLine;
			

			inputLine = reader.readLine();
				
		
			JSONObject json =new JSONObject(inputLine);
			String city=json.getString("city");
			String state= json.getString("state");
			return city+","+state;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
