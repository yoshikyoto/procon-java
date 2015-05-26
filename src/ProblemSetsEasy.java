
public class ProblemSetsEasy {
	public int maxSets(int E, int EM, int M, int MH, int H){
		int ans = 0;
		while(true){
			if(E > 0 && M > 0 && H > 0){
				ans++;
				E--; M--; H--;
			}
			if(E == 0){
				EM--;
				E++;
			}
			if(H == 0){
				MH--;
				H++;
			}
			if(M==0){
				if(E+EM > MH+H){
					EM--;
					M++;
				}else{
					MH--;
					M++;
				}
			}
			if(EM < 0 || MH < 0) return ans;
		}
	}
}
