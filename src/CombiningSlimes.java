import java.util.Arrays;


public class CombiningSlimes {
	public int maxMascots(int[] a) {
		Arrays.sort(a);
		int len = a.length;
		
		int sum = a[0];
		int score = 0;
		for (int i = 1; i < len; i++) {
			int size = a[i];
			score += sum * size;
			sum += size;
		}
		return score;
	}
}
