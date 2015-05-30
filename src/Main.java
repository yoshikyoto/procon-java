import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main extends MyUtil{
	public static void main(String[] args) throws Exception{
		while(true){
			int n = readInt();
			if(n == 0) break;
			
			Guy[] guys = new Guy[n];
			for(int i = 0; i < n; i++){
				int m = readIntMap(0);
				int l = readIntMap(1);
				
				Guy guy = new Guy();
				guy.l = l;
				guys[i] = guy;
				for(int j = 0; j < m; j++){
					int[] in = readIntMap();
					guy.start.add(in[0] - 6);
					guy.end.add(in[1] - 6);
				}
			}
			
			System.out.println(dfs(guys, 0, n, new boolean[16]));
		}
	}
	
	static int dfs(Guy[] guys, int i, int n, boolean[] schedule){
		if(i >= n) return 0;
		// 使わない場合
		int ret0 = dfs(guys, i + 1, n, schedule);
		
		// 使える場合は使ってもいい
		int ret1 = 0;
		if(canFill(schedule, guys[i])){
			boolean[] schedule_cp = cp(schedule);
			fill(schedule_cp, guys[i]);
			ret1 = guys[i].l + dfs(guys, i + 1, n, schedule_cp);
		}
		
		return Math.max(ret0, ret1);
	}
	
	/**
	 * 予定を入れられるならtrue,入れられないならfalse
	 */
	static boolean canFill(boolean[] schedule, Guy guy){
		for(int i = 0; i < guy.start.size(); i++){
			int s = guy.start.get(i);
			int e = guy.end.get(i);
			for(int j= s; j < e; j++){
				if(schedule[j]) return false;
			}
		}
		return true;
	}
	
	static void fill(boolean[] schedule, Guy guy){
		for(int i = 0; i < guy.start.size(); i++){
			int s = guy.start.get(i);
			int e = guy.end.get(i);
			for(int j = s; j < e; j++){
				schedule[j] = true;
			}
		}
	}
	
	public static boolean[] cp(boolean[] a) {
		boolean[] b = new boolean[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i];
		return b;
	}
}

class Guy{
	int l;
	ArrayList<Integer> start = new ArrayList<Integer>();
	ArrayList<Integer> end = new ArrayList<Integer>();
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


