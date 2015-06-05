package topcoder.SRM660Div2;

public class PrivateD2party {
	public int getsz(int[] a){
		int n = a.length;
		
		
		Person[] persons = new Person[n];
		for (int i = 0; i < n; i++) {
			persons[i] = new Person(i);
		}
		
		for (int i = 0; i < n; i++) {
			if(a[i] == i) continue;
			persons[i].hate = persons[a[i]];
		}
		
		int ans = n;
		for (int i = 0; i < n; i++) {
			if(persons[i].loopindex == 0){
				if(dfs(persons[i], 1, i+1) != 0){
					ans --;
				}
			}
		}
		return ans;
	}
	
	public int dfs(Person person, int i, int loopindex){
		if(person.hate == null) return 0;
		if(person.loopindex != 0){
			if(person.loopindex == loopindex){
				// ループ発見
				return i - person.cnt;
			}else{
				// 探索済み
				return 0;
			}
		}
		person.loopindex = loopindex;
		person.cnt = i;
		return dfs(person.hate, i+1, loopindex);
	}
	
	class Person{
		public Person hate = null;
		public int cnt = 0;
		public int loopindex = 0;
		public int index;
		
		Person(int i){
			index = i;
		}
	}
}