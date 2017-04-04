import java.util.*;
public class licenseKeys {
	public static void main(String[] args){
	   Scanner sc=new Scanner(System.in);  //input from the user
	   int k=sc.nextInt();
	   String s=sc.next();
	   System.out.println(dashes(s,k));   
		}
	
	public static String dashes(String s, int k){
	   int count=0;
	   StringBuilder sb=new StringBuilder();    
	   for(int i=s.length()-1;i>=0;i--){
	       char c=s.charAt(i);
	       if(c!='-'){        //check if c=='-'
	           if(count!=k){
	        	   sb.append(c);   //append char if count!=k and increment count
	               count++;
	                } 
	           else{
	               sb.append("-");
	               count = 0;        //if count==k, append '-' and make count=0.
	               i++;
	                }
	            }
	        }

	   return sb.reverse().toString().toUpperCase();
	    }

}
