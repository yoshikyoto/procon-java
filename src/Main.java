import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main extends MyUtil{
	static int n;
	public static void main(String[] args) throws Exception{
		while(true){
			n = readInt();
			if(n == 0) break;
			ArrayList<Node> list = new ArrayList<Node>();
			for(int i = 0; i < n; i++){
				Node node = new Node(readLine());
				list.add(node);
			}
			
			for(int i = 0; i < n; i++){
				System.out.println(list.get(i));
			}
		}
	}
	
}


class Node{
	static Node prev;
	// nextは兄弟ノード
	Node parent, child, next;
	int depth = 0;
	String name;
	
	public Node(String str){
		int i = 0;
		while(str.charAt(i) =='.')
			i++;
		depth = i;
		name = str.substring(i);
		
		while(prev != null){
			if(prev.depth == depth - 1)
				parent = prev;
			
			if(prev.depth == depth){
				prev.next = this;
				parent = prev.parent;
				break;
			}
			prev = prev.parent;
		}
		prev = this;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer(name);
		if(depth != 0){
			buf.insert(0, '+');
			if(parent.parent != null){
				parent.appendString(buf);
			}
		}
		return buf.toString();
	}
	
	public void appendString(StringBuffer buf){
		if(parent == null) return;

		if(next != null){
			buf.insert(0, '|');
		}else{
			buf.insert(0, ' ');
		}
		if(parent != null){
			parent.appendString(buf);
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


