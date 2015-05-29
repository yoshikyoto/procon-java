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
		while(true){
			char trump = br.readLine().charAt(0);
			if(trump == '#') break;
			
			// cards[player][turn]
			String[][] cards = new String[4][13];
			for (int i = 0; i < 4; i++) {
				cards[i] = br.readLine().split(" ");
			}

			int leader = 0; // 最初のリーダーはNorth
			int[] win_count = new int[4];
			
			for(int turn = 0; turn < 13; turn++){
				char lead_suit = getSuit(cards[leader][turn]);
				int winner = 0, max_point = 0;

				for(int player = 0; player < 4; player++){
					char suit = getSuit(cards[player][turn]);
					if(suit != lead_suit && suit != trump) continue;

					int point = getInt(cards[player][turn]);
					if(suit == trump) point += 15;
					if(max_point < point){
						max_point = point;
						winner = player;
					}
				}

				win_count[winner]++;
				leader = winner;
			}

			// 最終的に誰が勝ったか
			int ns = win_count[0] + win_count[2];
			int ew = win_count[1] + win_count[3];

			if(ns > ew){
				System.out.println("NS " + (ns - 6));
			}else{
				System.out.println("EW " + (ew - 6));
			}
		}
	}

	static char getSuit(String str){
		return str.charAt(1);
	}

	static int getInt(String str){
		char c = str.charAt(0);
		switch(c){
		case 'T': return 10;
		case 'J': return 11;
		case 'Q': return 12;
		case 'K': return 13;
		case 'A': return 14;
		default: return (int)(c - '0');
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


