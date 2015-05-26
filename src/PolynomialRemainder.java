
public class PolynomialRemainder {
	long mod = 1000000000L;
	int answer = -1;
	public int findRoot(int a, int b, int c){
		if(c == 0) return 0;
		if(a == 0 && b == 0) return -1;
		dfs((long)a, (long)b, (long)c, 1L, 0);
		return answer;
	}
	
	public void dfs(long a, long b, long c, long p, int ans){
		// System.out.println("dfs " + a + " " + b + " " + c + " " + v);
		if(answer != -1) return;
		if(p >= 1000000000L){
			System.out.println(ans);
			answer = ans;
			return;
		}
		
		for(long i = 0; i <= 9; i++){
			long sum = a * ans * ans + b * ans + c;
			System.out.println(sum);
			// sum = sum % mod;
			p *= 10L;
			if(sum % p == 0){
				dfs(a, b, c, p, ans + (int)i * (int)p);
			}
		}
	}
}
