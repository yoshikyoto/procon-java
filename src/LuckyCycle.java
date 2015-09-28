
public class LuckyCycle {
	public int[] getEdge(int[] edge1, int[] edge2, int[] weight) {
		int n = edge1.length + 1;
		int[][] g = new int[n][n];
		for(int i = 0; i < n - 1; i++) {
			g[edge1[i] - 1][edge2[i] - 1] = weight[i];
			g[edge2[i] - 1][edge1[i] - 1] = weight[i];
		}
		
		for(int i = 0; i < n - 1; i++) {
			int a = edge1[i] - 1;
			int b = edge2[i] - 1;
			int c = weight[i];
			// j-a-b-k
			//  l c r
			for(int j = 0; j < n; j++) {
				if(j == b) continue;
				int l = g[a][j];
				if(l == 0) continue;
				for(int k = 0; k < n; k++) {
					if(k == a) continue;
					int r = g[b][k];
					if(r == 0) continue;
					
					// check
					int sum = l + c + r;
					if(15 <= sum && sum <=18) {
						int[] ans = new int[3];
						ans[0] = j + 1;
						ans[1] = k + 1;
						ans[2] = 22 - sum;
						return ans;
					}
				}
			}
		}
		
		int[] ans = {};
		return ans;
	}
}
