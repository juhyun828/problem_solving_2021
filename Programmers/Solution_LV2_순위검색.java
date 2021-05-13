import java.util.*;
// 210513

class Solution_LV2_순위검색 {
	static Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

	public int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		// 1. 지원자 정보로 가능한 모든 조건들 구하기
		for (String i : info) {
			combination("", i.split(" "), 0);
		}
		
		// 정렬 : 이분탐색을 실행할 때 마다 정렬하는 것보다, 한번에 전체를 정렬해놓는게 좋다. 
		// 같은 리스트를 여러번 탐색해야 하는 경우도 있기 때문
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Collections.sort(map.get(key));
		}

		// 2. 이분탐색으로 특정 점수를 넘는 인원 수 구하기
		for (int i = 0; i < query.length; i++) {
			String key = "";
			String[] conditions = query[i].split(" "); // 7번째가 점수
			int score = Integer.parseInt(conditions[7]);

			for (int j = 0; j <= 6; j++) {
				if (conditions[j].equals("and"))
					continue;
				key += conditions[j];
			}
			
			int res = binarySearch(key, score);
			answer[i] = res;
		}

		return answer;
	}

	// 1. 지원자 정보로 가능한 모든 조건들 구하기
	static void combination(String selected, String[] infoArr, int L) {

		if (L == 4) { // L번째 조건은 점수
			int score = Integer.parseInt(infoArr[4]);
			if (map.containsKey(selected)) {
				map.get(selected).add(score);

			} else {
				map.put(selected, new ArrayList<Integer>());
				map.get(selected).add(score);
			}
			return;

		}

		// L번째 조건 선택
		combination(selected + infoArr[L], infoArr, L + 1);
		// L번째 조건 선택x
		combination(selected + "-", infoArr, L + 1);

	}

	// 2. 이분 탐색
	static int binarySearch(String key, int score) {
		if (!map.containsKey(key))
			return 0;

//		Collections.sort(map.get(key));
		ArrayList<Integer> list = map.get(key);

		int start = 0, end = list.size() - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (list.get(mid) < score) { // mid 왼쪽은 살펴볼 필요x
				start = mid + 1;
			} else { // mid 오른쪽은 살펴볼 필요 x
				end = mid - 1;
			}
		}
		return list.size() - start;
	}

}