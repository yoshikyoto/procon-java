import java.util.HashMap;


public class LiveConcert {
	public int maxHappiness(int[] h, String[] s) {
		int m = h.length;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < m; i++) {
			String idol = s[i];
			int point = h[i];
			if(!map.containsKey(idol)) {
				map.put(idol, point);
				continue;
			}
			int val = map.get(idol);
			if(val < point) {
				map.put(idol, point);
			}
		}
		
		int ans = 0;
		for(String idol : map.keySet()) {
			ans += map.get(idol);
		}
		return ans;
	}
}
