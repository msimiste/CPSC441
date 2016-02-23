import java.util.*;

public class B {

	public static class NODE {

		double x;
		double y;
		double[] distances;

		public NODE(double a, double b) {
			x = a;
			y = b;
		}

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int cases = in.nextInt();
		double dist = 0;

		while ((cases--) > 0) {

			in.nextLine();
			double min;
			int freckles = in.nextInt();
			int size = freckles;
			ArrayList<NODE> unreachedNodes = new ArrayList<NODE>();
			ArrayList<NODE> reachedNodes = new ArrayList<NODE>();

			while ((freckles--) > 0) {

				double a = in.nextDouble();
				double b = in.nextDouble();
				NODE test = new NODE(a,b);
				test.distances = new double [size];

				unreachedNodes.add(test);

			}
			reachedNodes.add(unreachedNodes.remove(0));

			while (unreachedNodes.size() > 0) {
				int location = 0;
				min = 5000000;
				
				for (NODE b : reachedNodes) {
					for (NODE n : unreachedNodes) {
						double x1 = b.x;
						double y1 = b.y;
						double x2 = n.x;
						double y2 = n.y;

						double temp = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
						if ((temp < min) && (temp > 0)) {
							min = temp;
							location = unreachedNodes.indexOf(n);
						}
						b.distances[unreachedNodes.indexOf(n)] = temp;
					}

				}
				reachedNodes.add(unreachedNodes.remove(location));
				dist += min;
			}
			System.out.format("%.2f",dist);
			if(cases>0)
			{
				System.out.println();
				dist = 0;
			}

		}

	}
}
