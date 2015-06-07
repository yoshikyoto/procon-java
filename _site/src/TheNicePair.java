import java.util.Arrays;


public class TheNicePair {
	public int solve(int[] a){
		int len = a.length;
		int[][] table = new int[len][1002];
		boolean m1flag = true;
		for(int i = 0; i < len; i++){
			for(int j = 2; j <= a[i]; j++){
				if(a[i] % j == 0){
					table[i][j] = 1;
					m1flag = false;
				}
			}
		}
		
		if(m1flag) return -1;
		
		int ans = 0;
		
		for(int b = 0; b < len-1; b++){
			int[] cnt = new int[1002];
			for(int i = 2; i <= 1001; i++) cnt[i] = table[b][i];
			
			for(int e = b+1; e < len; e++){
				int l = e - b + 1;
				// System.out.println(b + "\t" + e + "\t" + l);
				for(int i = 2; i <= 1001; i++){
					cnt[i] += table[e][i];
					// System.out.println(Arrays.toString(cnt));
					if(b == 0 && e == 4 && i == 1000) System.out.println(i + " " + cnt[i] + " " + l);
					if(cnt[i] >= (l + l%2)/2 - 0.01 && ans < (l-1)){
						// System.out.println("update");
						ans = l-1;
						break;
					}
				}
			}
		}
		return ans;
	}
}
