import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main extends MyUtil{
	static int h, w, n;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		h = readIntMap(0);
		w = readIntMap(1);
		n = readIntMap(2);
		char[][] table = new char[h][w];
		for(int i = 0; i < h; i++){
			table[i] = readLine().toCharArray();
		}

		if(solve(table)){
			System.out.println("YES");
		}else{
			System.out.println("NO");
		}

		// System.out.println(Arrays.deepToString(table));
	}

	static boolean solve(char[][] table){
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w-1; j++){
				char[][] table_swapped = swap(table, i, j);
				if(check(table_swapped)) return true;
			}
		}
		return false;
	}

	/**
	 * (x, y) の右と交換する
	 * @param table
	 * @param y
	 * @param x
	 * @return
	 */
	static char[][] swap(char[][] table, int y, int x){
		char[][] table_cp = new char[h][w];
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				table_cp[i][j] = table[i][j];
			}
		}
		table_cp[y][x] = table[y][x+1];
		table_cp[y][x+1] = table[y][x];
		// 入れ替えたら詰める
		tsume(table_cp);
		return table_cp;
	}

	/**
	 * 全部消えるかチェックする
	 * @param table
	 * @return
	 */
	static boolean check(char[][] table){
		char[][] table_cp = new char[h][w];
		// コピーとチェック
		boolean clear_flag = true;
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				table_cp[i][j] = table[i][j];
				if(table[i][j] != '.') clear_flag = false;
			}
		}
		// 全部消えていたらそこで終了
		if(clear_flag) return true;

		// 消えるところは消す
		boolean erase_flag = false;
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				if(table[i][j] == '.') continue;
				// System.out.println("for " + i + " " + j);
				// たて
				int vcnt = verticalCount(table, i, j);
				// System.out.println("vcnt: " + vcnt);
				if(vcnt >= n){
					erase_flag = true;
					for(int k = 0; k < vcnt; k++){
						table_cp[i+k][j] = '.';
					}
				}
				// よこ
				int hcnt = horizontalCount(table, i, j);
				if(hcnt >= n){
					erase_flag = true;
					for(int k = 0; k < hcnt; k++){
						table_cp[i][j+k] = '.';
					}
				}
			}
		}

		// 消えなかったらそこで終了
		
		if(!erase_flag) return false;

		// 消えた場合
		// 詰める
		for(int i = 1; i < h; i++){
			tsume(table_cp);
		}
		return check(table_cp);
	}

	static void tsume(char[][] table){
		// System.out.println(h-1);
		for(int i = h-1; i >= 1; i--){
			for(int j = 0; j < w; j++){
				if(table[i][j] == '.'){
					table[i][j] = table[i-1][j];
					table[i-1][j] = '.';
				}
			}
		}
	}

	static int verticalCount(char[][] table, int i, int j){
		int cnt = 1;
		char c = table[i][j];
		try{
			for(int k = 1; k <= h; k++){
				if(table[i+k][j] != c) throw new Exception();
				cnt++;
			}
		}catch(Exception e){
		}
		return cnt;
	}

	static int horizontalCount(char[][] table, int i, int j){
		int cnt = 1;
		char c = table[i][j];
		
		try{
			for(int k = 1; k <= w; k++){
				if(table[i][j+k] != c) throw new Exception();
				cnt++;
			}
		}catch(Exception e){
		}
		return cnt;
	}

	public static char[] cp(char[] a){
		char[] b = new char[a.length];
		for(int i = 0; i < a.length; i++) b[i] = a[i];
		return b;
	}
	
	public static void print(char[][] table){
		for(int i = 0; i < h; i++){
			StringBuffer buf = new StringBuffer();
			for(int j = 0; j < w; j++){
				buf.append(table[i][j]);
			}
			System.out.println(buf.toString());
		}
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


