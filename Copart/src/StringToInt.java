import java.util.Scanner;

public class StringToInt {
public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	//taking input
	System.out.println("Enter String:");
	String input = in.next();
	//checking if the String contains numeric characters only
	
	if(input.matches("[0-9]+"))
	{
		//calling the function to convert string to integer
		int resultInt = convertStringtoInt(input);
		
		System.out.println("Resulting Integer: "+resultInt);
	}
	else
		System.out.println("Enter valid String");
	
	
	
	
}

private static int convertStringtoInt(String input) {
	// TODO Auto-generated method stub
	int output=0; 
	int step=1;
	
	for(int i=input.length()-1;i>=0;i--){
	
		
		
		//taking the ASCII value of each character and converting it to int by subtracting it with 0 and then adjusting its position with the step (units place, hundred's place and so on).
		output+=(input.charAt(i)-'0')*step;
		step = step*10;
		
		
	}
	
	return output;
}


}



