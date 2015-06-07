
public class EightRooks {
	public String isCorrect(String[] board){
		boolean[] flag = new boolean[8];
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(board[i].charAt(j) == 'R'){
					if(flag[j]){
						return "Incorrect";
					}else{
						flag[j] = true;
					}
				}
			}
		}
		
		for(int i = 0; i < 8; i++){
			if(!flag[i]) return "Incorrect";
		}
		return "Correct";
	}
}
