import java.util.*;
public class C {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		String input = in.next();
		
		String initialState = input.substring(0,1);
		String remaining = input.substring(1);
		
		
		int policy1Count = 0;
		int policy2Count = 0;
		int policy3Count = 0;
		String state = initialState;

		//policy1 loop
		for(int i =0; i<remaining.length(); i++){
			
			
			String temp = remaining.charAt(i) + "";
			
			if(!temp.equals(state)){ 
				state = temp;
				policy1Count++;
			}
			if(!state.equals("U")){
				policy1Count++;
				state = "U";
			}
		
		}
		
		state = initialState;
		//policy2 loop
		for(int i = 0; i<remaining.length(); i++){
			
			String temp = remaining.charAt(i) + "";
			
			if(!temp.equals(state)){ 
				state = temp;
				policy2Count++;
			}
			if(!state.equals("D")){
				policy2Count++;
				state = "D";
			}
		}
		
		state = initialState;
		//policy3 loop
		for(int i = 0; i<remaining.length(); i++){
			
			String temp = remaining.charAt(i) + "";
			
			if(!temp.equals(state)){ 
				state = temp;
				policy3Count++;
			}			
		}
		
		System.out.println(policy1Count);
		System.out.println(policy2Count);
		System.out.println(policy3Count);
		
		
		
		
	}

}
