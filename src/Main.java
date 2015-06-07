import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;

/**
 * @author yoshikyoto
 */
class Main {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		int n = sc.nextInt();
		
		int nn = 2;
		while(nn <= n+n) nn *= nn;
		System.out.println(nn);
		
		Complex[] a = new Complex[nn];
		Complex[] b = new Complex[nn];
		for(int i = 0; i < nn; i++){
			if(i < n){
				a[i] = new Complex(sc.nextInt(), 0);
				b[i] = new Complex(sc.nextInt(), 0);
			}else{
				a[i] = new Complex(0, 0);
				b[i] = new Complex(0, 0);
			}
		}
		
		/*
		for (int i = 0; i < nn; i++) {
			System.out.println(a[i]);
		}
		for (int i = 0; i < nn; i++) {
			System.out.println(b[i]);
		}
		*/
		dft(a, nn);
		dft(b, nn);

		
		for (int i = 0; i < nn; i++) {
			System.out.println(a[i]);
		}
		for (int i = 0; i < nn; i++) {
			System.out.println(b[i]);
		}
		
		Complex[] ff = new Complex[nn];
		for (int i = 0; i < n; i++) {
			ff[i] = a[i].mul(b[i]);
		}
		for(int i = n; i < nn; i++){
			ff[i] = new Complex(0, 0);
		}
		
		//inv
		
		for(int i = n; i < nn; i++){
			ff[i].divide(nn);
		}
		
		System.out.println(0);
		for (int i = 0; i < 2*n-1; i++) {
			System.out.println(ff[i]);
		}
	}
	
	/**
	 * 高速フーリエ変換
	 * @param f
	 * @param nは2のx乗であることが前提となっている
	 */
	static void dft(Complex[] f, int n){
		if(n == 1){
			return;
		}
		
		Complex[] f0 = new Complex[n/2];
		Complex[] f1 = new Complex[n/2];
		for (int i = 0; i < n/2; i++) {
			f0[i] = f[2*i];
			f1[i] = f[2*i+1];
		}
		dft(f0, n/2);
		dft(f1, n/2);


		Complex zeta = new Complex(
				Math.cos(2 * Math.PI / n),
				Math.sin(2 * Math.PI / n));
		
		Complex powZeta = Complex.I;
		
		for (int i = 0; i < n; i++) {
			f[i] = f0[i % (n/2)].add(powZeta.mul(f1[i % (n/2)]));
			powZeta.mul(zeta);
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


