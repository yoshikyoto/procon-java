import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main extends MyUtil{
	public static void main(String[] args) throws Exception{
		// 入出力は自作クラスを使って行っている
		int n = readIntMap(0);
		int m = readIntMap(1);
		int[] b = readIntMap();
		int[] p = readIntMap();
		
		int[][] rl_p = new int[2][n];
		
		int bit = 0;
		int index = 0;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < p[i]; j++){
				rl_p[bit][index] = 1;
				index++;
			}
			bit = (bit + 1) % 2;
		}
		
		// zero_start_p は、0からスタートしてランレングス符号化したもの
		// one_start_p は1からスタート
		int[] zero_start_p = rl_p[1];
		int[] one_start_p = rl_p[0];
		
		int ans = 1 << 28;
		
		// すくなくとも、1と0の数が等しくないと、swapによって一致させられない
		if(popupBitCount(b) == popupBitCount(zero_start_p)){
			ans = Math.min(ans, solve(b, zero_start_p));
		}

		if(popupBitCount(b) == popupBitCount(one_start_p)){
			ans = Math.min(ans, solve(b, one_start_p));
		}
		
		System.out.println(ans);
	}
	
	/**
	 * ランレングス符号化された2つの配列を入力とし、
	 * 2つを一致させるために必要なswapの回数を求める
	 * @return swap回数
	 */
	static int solve(int[] b, int[] p){
		int len = b.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			if(b[i] != p[i]){
				sum += searchSwap(p, i);
			}
		}
		return sum;
	}
	
	/**
	 * iより右の位置で、一番近くで入れ替えられる場所を探して入れ替える。
	 * @return 入れ替えるのに必要なswapの回数
	 */
	static int searchSwap(int[] p, int i){
		int len = p.length;
		for(int j = i + 1; j < len; j++){
			if(p[i] != p[j]){
				return swap(p, i, j);
			}
		}
		return 0;
	}
	
	/**
	 * iとjを入れ替えるために、隣通しのswapをひたすら行う
	 * @return swapの回数
	 */
	static int swap(int[] p, int i, int j){
		int cnt = 0;
		for(int k = j-1; k >= i; k--){
			int tmp = p[k];
			p[k] = p[k+1];
			p[k+1] = tmp;
			cnt++;
		}
		return cnt;
	}
	
	/**
	 * 配列を入力として、1が立っているbitの数を数える
	 */
	static int popupBitCount(int[] arr){
		int len = arr.length;
		int sum = 0;
		for(int i = 0; i < len; i++){
			if(arr[i] != 0) sum++;
		}
		return sum;
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


