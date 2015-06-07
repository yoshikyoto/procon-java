import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main extends MyUtil {
	public static void main(String[] args) throws Exception {
		while(true) {
			int n = readIntMap(0);	// 街の数
			int m = readIntMap(1);	// 制限時間
			int l = readIntMap(2);	// 冷凍施設のある街の数
			int k = readIntMap(3); 	// 道路の数
			int a = readIntMap(4);	// スタート
			int h = readIntMap(5);	// ゴール
			if(n+m+l+k+a+h == 0) break;
			
//			int inf = 1 << 28;
			int[][] g = new int[n][n];
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < n; j++) {
//					g[i][j] = inf;
//				}
//			}
			
			boolean[] canFreeze = new boolean[n];
			canFreeze[a] = true;
			canFreeze[h] = true;
			if(l != 0){
				int list[] = readIntMap();
				for (int i = 0; i < l; i++) {
					canFreeze[list[i]] = true;
				}
			}else{
				readLine();
			}
			
			for (int i = 0; i < k; i++) {
				int x = readIntMap(0);
				int y = readIntMap(1);
				int t = readIntMap(2);
				g[x][y] = t;
				g[y][x] = t;
			}
			
			warshallFloyd(g, n);
			
			/*
			for (int i = 0; i < n; i++) {
				StringBuffer buf = new StringBuffer();
				for (int j = 0; j < n; j++) {
					buf.append(g[i][j] + "\t");
				}
				System.out.println(buf.toString().trim());
			}
			*/
			
			// 凍らせられない街のみにする
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(!canFreeze[i] || !canFreeze[j]){
						g[i][j] = 0;
					}
				}
			}
			
			// 届かないエッジを除く
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(g[i][j] > m){
						g[i][j] = 0;
					}
				}
			}
			
			// この状態でもう一回ワーシャルフロイド
			warshallFloyd(g, n);
			
			if(g[a][h] == 0){
				System.out.println("Help!");
			}else if(g[a][h] <= m){
				System.out.println(g[a][h]);
			}else{
				System.out.println(g[a][h] * 2 - m);
			}
		}
	}
	
	/**
	 * ワーシャルフロイド法
	 * コストが0だとエッジがないとみなされる版
	 */
	static void warshallFloyd(int[][] g, int n){
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(i == j) continue;
					if(g[i][k] == 0 || g[k][j] == 0) continue;
					if(g[i][j] == 0) {
						g[i][j] = g[i][k] + g[k][j]; 
					} else {
						g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
					}
				}
			}
		}
	}
}

/**
 * 複素数
 */
class Complex {
	static Complex I = new Complex(0, 1);
	
	double r = 0.0;
	double i = 0.0;
	Complex(double r, double i){
		this.r = r;
		this.i = i;
	}
	public Complex plus(Complex a){
		return new Complex(r * a.r, i + a.i);
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


