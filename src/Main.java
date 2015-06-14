import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main {
	static Scanner sc = new Scanner(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		while(true) {
			int n = sc.nextInt();
			if(n == 0) break;
			String[] ans = solve(n);
			System.out.println(ans[0] + " " + ans[1]);
		}
		sc.close();
	}
	
	static String[] solve(int n) {
		int[] p = new int[n];
		int[] t = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = sc.nextInt();
			t[i] = sc.nextInt();
		}
		int inf = 1<<28;
		int[] prev_distance = new int[4];
		Arrays.fill(prev_distance, inf);
		
		// 最初の風船
		if(p[0] > t[0]){
			String[] ans = {"NG", "1"};
			return ans;
		}
		prev_distance[1] = p[0];
		
		
		for (int i = 1; i < n; i++) {
			if(getMin(prev_distance) == inf) {
				String[] ans = {"NG", ""+i};
				return ans;
			}
			
			int[] next_distance = new int[4];
			Arrays.fill(next_distance, inf);
			
			for(int j = 1; j < 4; j++) {
				if(prev_distance[j] != inf) {
					// 家に戻って風船に行くまでの距離
					int distance = p[i-1] + p[i];
					// 家に戻って風船に行くまでの時間
					int time = p[i-1] * (j+1) + p[i];
					// 間に合う場合は値を更新
					if(t[i-1] + time <= t[i]) {
						next_distance[1] = Math.min(next_distance[1], prev_distance[j] + distance);
					}
					
					// 家に戻らないパターン
					// 風船がいっぱいでない
					if(j != 3) {
						distance = Math.abs(p[i] - p[i-1]);
						time = distance * (j+1);
						if(t[i-1] + time <= t[i]) {
							next_distance[j+1] = Math.min(next_distance[j+1], prev_distance[j] + distance);
						}
					}
				}
			}

			prev_distance = next_distance;
		}
		int mindist = getMin(prev_distance);
		if(mindist == inf) {
			String[] ans = {"NG", ""+n};
			return ans;
		}else{
			int dist = mindist + p[n-1];
			String[] ans = {"OK", ""+dist};
			return ans;
		}
	}
	static int getMin(int[] a) {
		int ans = 1<<28;
		for (int i = 0; i < a.length; i++) {
			ans = Math.min(ans, a[i]);
		}
		return ans;
	}
}

/**
 * 複素数
 */
class Complex {
	static Complex I = new Complex(0, 1);
	
	double r = 0.0;
	double i = 0.0;
	Complex(double r, double i) {
		this.r = r;
		this.i = i;
	}
	
	/**
	 * 和
	 */
	public Complex add(Complex a) {
		return new Complex(r * a.r, i + a.i);
	}
	
	/**
	 * 積
	 */
	public Complex mul(Complex a) {
		return new Complex(
				r * a.r - i * a.i,
				r * a.i + i * a.r);
	}
	
	public Complex divide(double a) {
		return new Complex(r/a, i/a);
	}
	
	public Complex inverse() {
		double b = 1 / (r*r + i*i);
		return new Complex(r/b, -i/b);
	}
	    

	@Override
	public String toString(){
		return r + "+" + i + "i";
	}
}




// --- ここから下はライブラリ ----------
/**
 * MyUtil
 * @author yoshikyoto
 */
class MyIO {
	public static int toInt(boolean[] a){
		int pow = 1, ret = 0, l = a.length;
		for(int i = 0; i < l; i++){
			if(a[i]) ret += pow;
			pow *= 2;
		}
		return ret;
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static int ins[];
	public static int[] readIntMap() throws IOException{return parseInt(readLine().split(" "));}
	public static int readIntMap(int i) throws Exception{
		if(i == 0) ins = readIntMap();
		return ins[i];
	}
	public static int[][] readIntMap(int n, int m) throws IOException{
		int[][] ret = new int[n][];
		for(int i = 0; i < n; i++) ret[i] = readIntMap();
		return ret;
	}
	public static int[] readIntToMap(int n) throws IOException{
		int[] ret = new int[n];
		for(int i = 0; i < n; i++) ret[i] = readInt();
		return ret;
	}
	public static int[] readNoDistIntMap() throws IOException{
		String[] strs = readLine().split("");
		int l = strs.length;
		int[] ret = new int[l-1];
		for(int i = 1; i < l; i++)
			ret[i-1] = parseInt(strs[i]);
		return ret;
	}
	public static String readLine() throws IOException{return br.readLine();}
	public static int readInt() throws IOException{return Integer.parseInt(br.readLine());}
	public static int[] parseInt(String[] arr){
		int[] res = new int[arr.length];
		for(int i = 0; i < arr.length; i++)res[i] = Integer.parseInt(arr[i]);
		return res;
	}
	public static double[] parseDouble(String[] arr){
		double[] res = new double[arr.length];
		for(int i = 0; i < arr.length; i++)res[i] = Double.parseDouble(arr[i]);
		return res;
	}
	public static boolean[] parseBool(String[] arr){
		int[] t = parseInt(arr);
		boolean[] res = new boolean[t.length];
		for(int i = 0; i < t.length; i++){
			if(t[i] == 1){res[i] = true;
			}else{res[i] = false;}
		}
		return res;
	}
	public static int parseInt(Object o){
		return Integer.parseInt(o.toString());
	}
}


