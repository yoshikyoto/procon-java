
public class ChessFloor {
	public int minimumChanges(String[] floor) {
		int n = floor.length;
		char[][] table = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				table[i][j] = floor[i].charAt(j);
			}
		}
		
		int ans = n*n;
		for(int i = 0; i < 26; i++) {
			for(int j = 0; j < 26; j++) {
				if(i == j) continue;
				char a = (char)((int)'a' + i);
				char b = (char)((int)'a' + j);
				ans = Math.min(ans, count(table, n, a, 0) + count(table, n, b, 1));
			}
		}
		return ans;
	}
	
	public int count(char[][] table, int n, char c, int m) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if((i + j) % 2 == m) {
					if(table[i][j] != c) {
						cnt++;
					}
				}
			}
		}
		return cnt;
	}
}
