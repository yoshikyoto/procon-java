package topcoder.SRM660Div2;

public class Cyclemin {
	public String bestmod(String s, int k){
		int len = s.length();
		String ans = null;
		for (int i = 0; i < len; i++) {
			char c = s.charAt(0);
			s = s.substring(1) + c;
			String replaced_s = replace(s, k);
			if(ans == null){
				ans = replaced_s;
			}else if(ans.compareTo(replaced_s) > 0){
				ans = replaced_s;
			}
		}
		return ans;
	}
	
	String replace(String s, int k){
		if(k == 0) return s;
		char[] c = s.toCharArray();
		for(int i = 0; i < c.length; i++){
			if(c[i] != 'a'){
				c[i] = 'a';
				k--;
				if(k == 0) break;
			}
		}
		return String.valueOf(c);
	}
}
