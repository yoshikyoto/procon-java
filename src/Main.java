import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main {
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		
		while(true){	
			int n = sc.nextInt();
			if(n == 0) break;
			
			boolean[][] schedule = new boolean[n][30];
			
			for(int i = 0; i < n; i++){
				int m = sc.nextInt();
				for (int j = 0; j < m; j++) {
					schedule[i][sc.nextInt() - 1] = true;
				}
			}
			
			System.out.println(solve(n, schedule));
		}
	}
	
	static int solve(int n, boolean[][] schedule){
		boolean[][] canHave = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			canHave[i][i] = true;
		}
		
		for (int day = 0; day < 30; day++) {
			// day日にaさんとbさんの予定が合う場合は地図を交換できる
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < a; b++) {
					if(schedule[a][day] && schedule[b][day]){
						// 地図をシェアする
						share(n, canHave, a, b);
					}
				}
			}
			
			// 地図は集まったかどうかをチェックする
			if(check(n, canHave)){
				return day + 1;
			}
		}
		return -1;
	}
	
	static void share(int n, boolean[][] canHave, int a, int b){
		for (int i = 0; i < n; i++) {
			boolean tmp = canHave[a][i] || canHave[b][i];
			canHave[a][i] = tmp;
			canHave[b][i] = tmp;
		}
	}
	
	/**
	 * 誰か一人でも、地図を全部集められる人がいたらtrue
	 */
	static boolean check(int n, boolean[][] canHave){
		for (int person = 0; person < n; person++) {
			if(check(n, person, canHave)) return true;
		}
		return false;
	}
	
	/**
	 * person番目の人が地図を全部集められるならtrue
	 */
	static boolean check(int n, int person, boolean[][] canHave){
		for (int i = 0; i < n; i++) {
			if(!canHave[person][i]) return false;
		}
		return true;
	}
}

/**
 * UnionFindTree 
 * @author yoshikyoto
 */
class UnionFindTree {
	public int[] parent, rank;
	public int n;
	public int count;

	// 初期化
	UnionFindTree(int n) {
		this.n = n;
		count = n;
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	// 根を求める
	int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}

	// xとyの集合を結合
	void unite(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return;
		}
		if (rank[x] < rank[y]) {
			parent[x] = y;
			count--;
		} else {
			parent[y] = x;
			if (rank[x] == rank[y])
				rank[x]++;
			count--;
		}
	}

	// xとyが同じ集合か
	boolean same(int x, int y) {
		return find(x) == find(y);
	}
}

class BinaryIndexedTree{
    int n;
    int[] bit;
    BinaryIndexedTree(int n){
        this.n = n;
        bit = new int[n+1];
    }
    int sum(int i){
        int sum = 0;
        while(i > 0){
            sum += bit[i];
            i -= i & -i;
        }
        return sum;
    }
    void add(int i, int v){
        while(i <= n){
            bit[i] += v;
            i += i & -i;
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


