import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			Zone zone = new Zone(br.readLine());
			System.out.println(zone.solve());
		}
	}
}

class Zone{
	ArrayList<Zone> children;
	int value = 0;
	
	Zone(String str){
		str = str.substring(1, str.length() - 1);
		
		try{
			// 値だったらparseInt
			value = Integer.parseInt(str);
		}catch(NumberFormatException e){
			// 値じゃなかったら木構造的にparse
			children = new ArrayList<Zone>();
			int depth = 0;
			int start = 0;
			for (int i = 0; i < str.length(); i++) {
				switch(str.charAt(i)){
				case '[':
					if(depth == 0) start = i;
					depth++;
					break;
				case ']':
					depth--;
					if(depth == 0){
						children.add(new Zone(str.substring(start, i+1)));
					}
					break;
				}
			}
		}
	}
	
	int solve(){
		// valueは必ず奇数なのでこれでいい
		if(value != 0) return (value + 1) / 2;
		
		// 再帰的にsolve
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(Zone child : children){
			arr.add(child.solve());
		}
		
		// 過半数を返す
		Collections.sort(arr);
		int sum = 0;
		int maj = (arr.size() + 1) / 2; // arrのsizeはかならず奇数なので
		for (int i = 0; i < maj; i++) {
			sum += arr.get(i);
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


