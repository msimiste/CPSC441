import java.util.*;
public class A {

	public static void main(String[] args) {
		

		Scanner in = new Scanner(System.in);
		
		int cases = in.nextInt();
		while((cases--)>0){
			
			int out = in.nextInt();
			int N = in.nextInt();
			int S1 = (N*(N+1))/2;
			int S2 = (int) Math.pow(N, 2);
			int S3 = (N*(N+1));
			
			System.out.print(out + " " + S1 + " " + S2 + " " + S3 + "\n");
		}

	}

}
