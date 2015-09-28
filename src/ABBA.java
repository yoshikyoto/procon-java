
public class ABBA {
	public String canObtain(String initial, String target) {
		int limit = target.length() - initial.length();
		
		for (int i = 0; i < limit; i++) {
			int len = target.length();
			if(target.charAt(len - 1) != 'A') {
				target = target.substring(0, len - 1);
				target = new StringBuffer(target).reverse().toString();
			} else {
				target = target.substring(0, len - 1);
			}
		}
		if(target.equals(initial)) {
			return "Possible";
		} else {
			return "Impossible";
		}
	}
}
