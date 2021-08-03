import java.io.*;
import java.util.*;
// 210328

/*
	1. 산술평균 : 전체합 / 개수, 반올림 : Math.round(float() ) -> 결과는 int형
	2. 중앙값 : 정렬 후 N/2번째 수
	3. 최빈값 : 
		1) int형 count 배열 사용
		   음수도 처리하기 위해 양수화 
	    2) HashMap 사용 ? 
	    
	4. 범위 : 정렬 후 최대 (N-1번째 수) - 최소 (0번째 수)
*/

public class Main_BJ_2108_통계학 {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_BJ_2108_통계학.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = stoi(br.readLine());
		int[] arr = new int[N];
		int[] count = new int[4000*2+1];
		double sum=0;
		int modeCnt =0;
		
		// 입력
		for(int i=0; i<N; i++) {
			arr[i] = stoi(br.readLine());
			sum += arr[i]; // 1. 산술평균
			count[arr[i]+4000]++; // 3. 최빈값
			if(modeCnt < count[arr[i]+4000]) modeCnt = count[arr[i]+4000]; // 3. 최빈값
		}
		
		// 1. 산술 평균 - 소수점 이하 첫째 자리에서 반올림, sum을 double형으로
		System.out.println(Math.round(sum / N));
	
		// 2. 중앙값
		Arrays.sort(arr);
		System.out.println(arr[N/2]);
		
		// 3. 최빈값, 최빈값 여러 개일 때는 두번째로 작은 값 출력
		ArrayList<Integer> modes = new ArrayList<>();
		for(int i=0; i<4000*2+1; i++) {
			if(count[i]==modeCnt) modes.add(i-4000);
		}
		
		Collections.sort(modes);
		if(modes.size()==1) System.out.println(modes.get(0));
		else System.out.println(modes.get(1));
		
		// 4. 범위 출력
		System.out.println(arr[N-1] - arr[0] );		

		br.close();
	}
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
