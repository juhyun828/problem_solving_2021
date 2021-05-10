import java.util.*;

//210510
class Solution_LV3_다단계칫솔판매_fail {
	static int N;
	static Map<String, Integer> map;
	static int[][] adjMatrix;
	static Queue<Data> q;
	static int[] cost, res;
	
	 public static void main(String[] args) {
		String[] enroll = new String[] {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] referral = new String[] {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] seller = new String[] {"young", "john", "tod", "emily", "mary"};
		int[] amount = new int[] {12, 4, 2, 5, 10};
		System.out.println(Arrays.toString(solution(enroll, referral, seller, amount)));
		
	}
	 
	static class Data {
		 int from, idx, cost;

		public Data(int from, int idx, int cost) {
			this.from = from;
			this.idx = idx;
			this.cost = cost;
		}
	 }
	
    static public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        N = enroll.length;
        map = new HashMap<>();
        map.put("minho", 0);
        for(int i=1; i<=N; i++) {
        	map.put(enroll[i-1], i);
        }
        
        // 1. 인접행렬 만들기
        adjMatrix  = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
        	String from = referral[i-1];
        	int to = i;
        	
        	if(from.equals("-")) { // 민호가 등록시킴
        		adjMatrix[0][to] = 1;
        		
        	} else {
        		adjMatrix[map.get(from)][to] = 1;
        	}
        	
        }
        
        for(int i=0; i<=N; i++)
        	System.out.println(Arrays.toString(adjMatrix[i]));
                
        // 2. 총 판매 금액 계산
        cost = new int[N+1]; res = new int[N+1];
        for(int i=0; i<seller.length; i++) {
        	String name = seller[i];
        	cost[map.get(name)] += 100 * amount[i];
        }
        
        // 3.
        bfs();
        
        int[] ans = new int[N];
        for(int i=1; i<=N; i++) {
        	ans[i-1] = res[i];
        }
        return ans;
    }
    
    // 3. bfs로 금액 배분 탐색
    static void bfs() {
        q = new ArrayDeque<Data>();
    	boolean[][] v = new boolean[N+1][N+1];
    	
    	for(int i=0; i<=N; i++) {
    		if(cost[i]>0) {
    			q.offer(new Data(i, i, cost[i]));
    			v[i][i] = true;
    		}
    	}
    	
    	while(!q.isEmpty()) {
    		Data cur = q.poll();
    		boolean flag = false;
    		// 이 사람이 얻은 수익을 나누어가질 수 있는 상급자를 찾는다.
    		for(int i=0; i<=N; i++) {
    			if(adjMatrix[i][cur.idx]==1 && !v[i][cur.from]) {
    				flag = true;
    				v[i][cur.from] = true;
    				
    				if((int) (cur.cost * 0.1)<1) {
    	  				res[cur.idx] += cur.cost;
    				} else {
    					int divided = (int) (cur.cost * 0.1);
    					// 내 돈
    					res[cur.idx] += cur.cost - divided;
    					// 나누어줄 돈
    					q.offer(new Data(cur.from, i, divided));
    				}
    				break;
    			}
    		}
    		
    		if(!flag) {
    			 res[cur.idx] += cur.cost;
    		}
		}

	}

}