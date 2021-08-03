import java.util.*;
// 210514

class Solution_LV3_합승택시요금_floyd {
    public int solution(int n, int s, int a, int b, int[][] fares) {   
        // 1. 모든 정점들 사이의 인접 행렬 만들기 & 양방향 그래프
        int INF = 987654321;
//    	int INF = 100000 * 200 + 1;
        int[][] adjMatrix = new int[n+1][n+1];
        for(int i=0; i<=n; i++) {
        	Arrays.fill(adjMatrix[i], INF);
        	adjMatrix[i][i] = 0;
        }
        for(int[] fare: fares) {
        	int from = fare[0], to = fare[1], cost = fare[2];
        	adjMatrix[from][to] = cost;
        	adjMatrix[to][from] = cost;
        }
        
        // 2. 플루이드 워샬 알고리즘 : 경, 출, 도
        for(int k=1; k<=n; k++) {
        	for(int i=1; i<=n; i++) {
        		if(k==i) continue;
        		for(int j=1; j<=n; j++) {
        			if(k==j || i==j) continue;
        			if(adjMatrix[i][k]==INF || adjMatrix[k][j]==INF) continue;
        			if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
        				adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
        			}
        		} 
        	}
        }
        
        // 3. 합승하지 않고 바로 가는 경우: S->A + S->B
        int min = adjMatrix[s][a] + adjMatrix[s][b]; 
        // INF로 초기화 해도 상관없음 adhMatrix[S][S]는 0이기 때문에 합승하지 않는 경우가 4번에서도 구해짐
        
        // 4. 1~N번 정점을 각각 경유하여 가는 경우: S->경유지 + 경유지->A + 경유지->B
        for(int i=1; i<=n; i++) {
        	if(adjMatrix[s][i]==INF || adjMatrix[i][a]==INF || adjMatrix[i][b]==INF) continue;
        	min = Math.min(min, adjMatrix[s][i] + adjMatrix[i][a] + adjMatrix[i][b]);
        }
        
        return min;
    }
}