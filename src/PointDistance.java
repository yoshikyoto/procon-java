
public class PointDistance {
	public int[] findPoint(int x1, int y1, int x2, int y2) {
		for (int x3 = -100; x3 <= 100; x3++) {
			for(int y3 = -100; y3 <=100; y3++) {
				double ac = dist(x1, y1, x3, y3);
				double bc = dist(x2, y2, x3, y3);
				if(ac > bc) {
					int[] ans = new int[2];
					ans[0] = x3;
					ans[1] = y3;
					return ans;
				}
			}
		}
		return new int[2];
	}
	
	// square を snippet から実装してください
	public static int sqdist(int x1, int y1, int x2, int y2) {
		return sq(x1 - x2) + sq(y1 - y2);
	}

	public static double sqdist(double x1, double y1, double x2, double y2) {
		return sq(x1 - x2) + sq(y1 - y2);
	}

	public static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(sqdist(x1, y1, x2, y2));
	}

	public static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt(sqdist(x1, y1, x2, y2));
	}
	
	public static int sq(int i) {
		return i * i;
	}

	public static double sq(double d) {
		return d * d;
	}
}
