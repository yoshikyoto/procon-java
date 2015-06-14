import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;


class Main {
	static Scanner sc = new Scanner(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		while(true) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int d = sc.nextInt();
			if(a + b + d == 0) break;
			int[] ans = solve(a, b, d);
			System.out.println(ans[0] + " " + ans[1]);
		}
	}
	
	static void comp(int[] ans, int acount, int bcount) {
		if(ans[0] + ans[1] > acount + bcount) {
			ans[0] = acount;
			ans[1] = bcount;
		}
	}
	
	static int[] solve(int a, int b, int d) {
		int inf = 1 << 28;
		int[] ans = {inf, inf};
		
		int acnt = d / a;
		acnt++;
		int bcnt = 0;
		while(acnt >= 0) {
			int sum = a * acnt + b * bcnt;
			if(sum == d) {
				comp(ans, acnt, bcnt);
				acnt--;
			} else if(sum > d) {
				acnt--;
			} else {
				bcnt++;
			}
		}
		
		int asum = a;
		int bsum = 0;
		while(asum != bsum) {
			int diff = asum - bsum;
			if(Math.abs(diff) == d) {
				comp(ans, asum / a, bsum / b);
				break;
			}
			if(diff < d) {
				asum += a;
			} else {
				bsum += b;
			}
		}
		
		asum = 0;
		bsum = b;
		while(asum != bsum) {
			int diff = bsum - asum;
			if(Math.abs(diff) == d) {
				comp(ans, asum / a, bsum / b);
				break;
			}
			if(diff < d) {
				bsum += b;
			} else {
				asum += a;
			}
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


