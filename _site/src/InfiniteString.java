
public class InfiniteString {
	public String equal(String s, String t){
		int sl = s.length();
		int tl = t.length();
		
		StringBuffer ss = new StringBuffer();
		StringBuffer tt = new StringBuffer();
		
		for(int i = 0; i < tl; i++)
			ss.append(s);
		
		for(int i = 0; i < sl; i++)
			tt.append(t);
		
		String sss = ss.toString();
		String ttt = tt.toString();
		
		if(sss.equals(ttt)){
			return "Equal";
		}else{
			return "Not equal";
		}
	}
}
