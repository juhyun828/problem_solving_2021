import java.util.*;
// 210504

class Solution_LV2_수식최대화_fail {
	static HashMap<String, Integer> map;
	static int[] res; // "*", "+", "-"
	static boolean[] v;
	static ArrayList<String> expList;
    
	public long solution(String 	expression) {
		expList = new ArrayList<>();
		String num = "";
		for(char ch: expression.toCharArray()) {
			if(ch=='*' || ch=='-' || ch=='+') {
				expList.add(num);
				expList.add(Character.toString(ch));
				num = "";
			} else {
				num += Character.toString(ch);
			}
		}

		// 1. 우선순위 정하기
		res = new int[3];
		v = new boolean[3];
		permutation(0, 0);
		
		// 2. 후위 표현식으로 바꾸기
		
		// 3. 후위 표현식 계산
		
        long answer = 0;
        return answer;
    }
	
	static void permutation(int start, int L) {
		if(L>=3) {
			System.out.println(Arrays.toString(res));
			map = new HashMap<String, Integer>();
			map.put("*", res[0]);
			map.put("+", res[1]);
			map.put("-", res[2]);
			// 2. 후위 표현식으로 바꾸기
			ArrayList<String> postList = convertToPostfix();
			System.out.println(postList);
			long postRes = cal(postList);
			System.out.println(postRes);
			return;
		}
		
		for(int i=0; i<3; i++) {
			if(v[i]) continue;
			res[L] = i;
			v[i] = true;
			permutation(i+1, L+1);
			v[i] = false;
		}
	}

	// 2. 후위 표현식으로 바꾸기
	static ArrayList<String> convertToPostfix() {
		ArrayList<String> postList = new ArrayList<>();
		// 스택
		Stack<String> stack = new Stack<>();
		// 1. 피연산자 -> 출력
		// 2. 연산자
		// 2-1. 스택이 비었으면 push
		// 2-2. 스택이 비지 않았으면
		// 1) 스택 안 연산자 < 현재 연산자 -> 현재 연산자 push
		// 2) 				 > 			   -> 스택 pop & 현재 연산자 push
		
		for(String str: expList) {
			// 2. 연산자
			if(str.equals("*") || str.equals("+") || str.equals("-")) {
				if(stack.isEmpty()) stack.push(str);
				else {
					if(map.get(stack.peek()) < map.get(str)) {
						stack.push(str);
					} else {
						postList.add(stack.pop());
						stack.push(str);
					}
				}
				
			} else {
				// 1. 피연산자 -> 출력
				postList.add(str);
			}
			
		}
		
		// 3. 스택이 빌 때 까지 pop
		while(!stack.isEmpty()) {
			postList.add(stack.pop());
		}
		
		return postList;
	}
	
	// 3. 후위 표기식 계산
	static long cal(ArrayList<String> postList) {
		Stack<Long> stack = new Stack<>();
		
		for(String str: postList) {
			System.out.println(str);
			if(str.equals("*") || str.equals("+") || str.equals("-")) {
				long su1 = stack.pop();
				long su2 = stack.pop();
				
				if(str.equals("*")) {
					long tmp = su1 * su2;
					stack.push(tmp);
					
				} else if(str.equals("+")) {
					long tmp = su1 + su2;
					stack.push(tmp);
					
				} else if(str.equals("-")) {
					long tmp = su2 - su1;
					stack.push(tmp);
				}
			} else {
				stack.push(Long.parseLong(str));
			}
		}
		
		long stackRes = stack.pop();
		if(stackRes<0) Math.abs(stackRes);
		return stackRes;
	}

}