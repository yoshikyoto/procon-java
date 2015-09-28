
public class LuckyXor {
	public int construct(int a) {
		for(int b = a + 1; b <= 100; b++) {
			if(check(a ^ b)) {
				return b;
			}
		}
		return -1;
	}
	
	public boolean check(int n) {
		String str = String.valueOf(n);
		int len = str.length();
		
		for(int i = 0; i <= len; i++) {
			if(i == len) return true;
			if(str.charAt(i) != '7') break;
		}
		
		for(int i = 0; i <= len; i++) {
			if(i == len) return true;
			if(str.charAt(i) != '4') break;
		}
		
		return false;
	}
}
