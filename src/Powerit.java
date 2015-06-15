import java.math.BigInteger;


public class Powerit {
	public int calc(int n, int k, int m) {
		BigInteger mod = new BigInteger("" + m);
		BigInteger two = new BigInteger("2");
		
		// 2^k-1 を求める
		BigInteger kata = two.pow(k);
		kata = kata.subtract(BigInteger.ONE);

		BigInteger ans = BigInteger.ZERO;
		for (int i = 1; i <= n; i++) {
			BigInteger tai = new BigInteger("" + i);
			tai = tai.modPow(kata, mod);
			ans = ans.add(tai).remainder(mod);
		}
	
		return ans.intValue();
	}
}
