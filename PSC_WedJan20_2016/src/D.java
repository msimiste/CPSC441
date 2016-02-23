import java.util.*;

public class D {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int cases = in.nextInt();

		while ((cases--) > 0) {

			long sum = 0;
			int kids = in.nextInt();
			int count = kids;
			while ((count--) > 0) {
				sum += in.nextLong() % kids;
			}

			if (sum % kids == 0) {
				System.out.println("YES");
			} else
				System.out.println("NO");
		}

	}

}
