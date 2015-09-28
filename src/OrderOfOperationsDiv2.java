import java.util.Arrays;


public class OrderOfOperationsDiv2 {
	public int n, m;
	public int inf = 1<<28;
	
	public int minTime(String[] s) {
		System.out.println(inf);
		n = s.length;
		char[][] instructions = new char[n][];
		for (int i = 0; i < n; i++) {
			instructions[i] = s[i].toCharArray();
		}
		m = instructions[0].length;
		
		int cnt[] = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(instructions[i][j] == '1') {
					cnt[i]++;
				}
			}
		}
		
		int ans = 0;
		for(int i = 0; i < n; i++) {
			int cost = execute(instructions, cnt);
			if(cost == inf) break;
			ans += cost;
		}
		return ans;
	}
	
	public int execute(char[][] instructions, int[] cnt) {
		int minCnt = inf;
		int minIndex = 0;
		for (int i = 0; i < n; i++) {
			if(cnt[i] < minCnt) {
				minCnt = cnt[i];
				minIndex = i;
			}
		}
		
		if(minCnt == m + 1) return 0;
		int ret = cnt[minIndex] * cnt[minIndex];
		for (int j = 0; j < m; j++) {
			if(instructions[minIndex][j] == '0') continue;
			for (int i = 0; i < n; i++) {
				if(instructions[i][j] == '1') {
					instructions[i][j] = '0';
					cnt[i]--;
				}
			}
		}
		cnt[minIndex] = inf;
		return ret;
	}
}
