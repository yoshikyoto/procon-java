import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main {
	static HashMap<String, String[]> groups;
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		while(true) {
			int n = sc.nextInt();
			if(n == 0) break;
			
			Cell[] cell = new Cell[n];
			for (int i = 0; i < n; i++) {
				double x = sc.nextDouble();
				double y = sc.nextDouble();
				double z = sc.nextDouble();
				double r = sc.nextDouble();
				cell[i] = new Cell(x, y, z, r);
			}
			
			ArrayList<Isle> arr = new ArrayList<Isle>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					// コストを求める。
					double xx = sq(cell[i].x - cell[j].x);
					double yy = sq(cell[i].y - cell[j].y);
					double zz = sq(cell[i].z - cell[j].z);
					double dist = Math.sqrt(xx + yy + zz) - Math.abs(cell[i].r + cell[j].r);
					dist = Math.max(0, dist);
					Isle isle = new Isle(i, j, dist);
					arr.add(isle);
				}
			}
			
			// コストでソート
			Collections.sort(arr, new IsleComp());
			
			int nn = arr.size();
			double ans = 0;
			UnionFindTree uft = new UnionFindTree(nn+1);
			for (int i = 0; i < nn; i++) {
				Isle isle = arr.get(i);
				if(uft.same(isle.x, isle.y)) continue;
				// sameでなければcostを払って連結
				ans += isle.cost;
				uft.unite(isle.x, isle.y);
			}
			
			System.out.printf("%.3f\n", ans);
		}
	}

	/**
	 * 二乗
	 */
	public static double sq(double d) {
		return d * d;
	}
}

/**
 * セル
 */
class Cell {
	double x, y, z, r;

	public Cell(double x, double y, double z, double r) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.r = r;
	}
}

/**
 * セルをコストでソートする用
 * @author yoshiyuki_sakamoto
 *
 */
class Isle {
	int x, y;
	double cost;
	public Isle(int x, int y, double dist) {
		this.x = x;
		this.y = y;
		this.cost = dist;
	}
	@Override
	public String toString() {
		return x + " " + y + " " + cost;
	}
}

class IsleComp implements Comparator<Isle> {
	@Override
	public int compare(Isle a, Isle b) {
		if(a.cost == b.cost){
			return 0;
		}else if(a.cost > b.cost){
			return 1;
		}else{
			return -1;
		}
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


