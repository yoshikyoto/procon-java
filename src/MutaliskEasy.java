import java.util.Arrays;


public class MutaliskEasy {
	public int minimalAttacks(int[] x){
		int l = x.length;
		
		if(l == 1) return (int)Math.ceil(x[0] / 9.0);
		
		if(l == 2) return dfs2(x);
		
		return dfs(x);
	}
	
	public int dfs2(int[] x){
		System.out.println(Arrays.toString(x));
		if(x[0] <= 0 && x[1] <= 0) return 0;
		int ret = 540;
		
		x[0] -= 9; x[1] -= 3;
		ret = Math.min(ret, 1 + dfs2(x));
		
		x[0] += 6; x[1] -= 6;
		ret = Math.min(ret, 1 + dfs2(x));
		
		return ret;
	}
	
	public int dfs(int[] x){
		return 1;
	}
}
