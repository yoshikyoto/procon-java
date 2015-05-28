import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main extends MyUtil{
	public static void main(String[] args) throws Exception{
		while (true) {
			// 入力は独自クラスを使っている
			int[] in = readIntMap();
			if(in[0] + in[1] == 0) break;
			cnt = 0;
			max = 0;
			check(10, in[0], in[1], 0, "");
			if(cnt == 0){
				System.out.println("error");
			}else if (cnt > 1){
				System.out.println("rejected");
			}else{
				System.out.println(max + " " + ans);
			}
		}
	}
	
	static int cnt = 0;
	static int max = 0;
	static String ans = "";
	static void check(int i, int t, int num, int sum, String buf){
		if(num < i){
			sum += num;
			buf = num + buf;
			if(max < sum && sum <= t){
				max = sum;
				ans = buf;
				cnt = 1;
			}else if(max == sum){
				cnt++;
			}
			return;
		}
		
		// iで分断しない場合
		// System.out.println(num + " を分断しない");
		check(i*10, t, num, sum, buf);
		
		// iで分断する場合
		int next_num = num / i;
		int bundan = num % i;
		int next_sum = sum + bundan;
		// System.out.println(num + " を " + next_num + " と分断");
		check(10, t, next_num, next_sum, " " + bundan + buf);
	}
}

// --- ここから下はライブラリ ----------
/**
 * MyUtil
 * @author yoshikyoto
 */
class MyUtil {
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


