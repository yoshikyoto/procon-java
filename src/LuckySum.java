import java.util.ArrayList;
import java.util.Collections;


public class LuckySum {
	public long construct(String note) {
		StringBuffer buf4 = new StringBuffer();
		long[] arr4 = new long[14];
		for(int i = 0; i < 14; i++) {
			buf4.append(4);
			arr4[i] = Long.parseLong(buf4.toString());
		}
		
		StringBuffer buf7 = new StringBuffer();
		long[] arr7 = new long[14];
		for(int i = 0; i < 14; i++) {
			buf7.append(7);
			arr7[i] = Long.parseLong(buf7.toString());
		}
		
		ArrayList<Long> list = new ArrayList<Long>();
		for(int i = 0; i < 14; i++) {
			for(int j = 0; j < 14; j++) {
				list.add(arr4[i] + arr4[j]);
				list.add(arr4[i] + arr7[j]);
				list.add(arr7[i] + arr4[j]);
				list.add(arr7[i] + arr7[j]);
			}
		}
		Collections.sort(list);
		
		int len = list.size();

		for(int i = 0; i < len; i++) {
			long ans = list.get(i);
			String str = String.valueOf(ans);
			if(check(str, note)) {
				return ans;
			}
		}
		return -1L;
	}
	
	public boolean check(String str, String note) {
		if(str.length() != note.length()) return false;
		int len = note.length();
		for(int j = 0; j < len; j++) {
			char nchar = note.charAt(j);
			if(nchar == '?') continue;
			if(str.charAt(j) != nchar) return false;
		}
		return true;
	}
}
