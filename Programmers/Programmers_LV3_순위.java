import java.util.*;

// 210410

class Solution {
    static int[][] adjMatrix;
    static int INF = 987654321;
    
    public int solution(int n, int[][] results) {
        
        // 1. 인접행렬 만들기
        adjMatrix = new int[n+1][n+1];
        for(int[] arr: adjMatrix)
            Arrays.fill(arr, INF);
        // 대각선
        for(int i=0; i<=n; i++) {
            adjMatrix[i][i] = 0;
        }
        int from, to;
        for(int i=0; i<results.length; i++) {
            from = results[i][0];
            to = results[i][1];
            adjMatrix[from][to] = 1;
        }
              
        
        // 2. 플로이드 워샬 알고리즘 - 최단경로 구하기
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                if(k==i) continue;
                for(int j=1; j<=n; j++) {
                    if(k==j || i==j) continue;
                    if(adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }
         
        // 3. 답 구하기
        // - 모든 선수들과 게임을 한 적이 있는지 검사한다.
        int res = 0;
        for(int i=1; i<=n ; i++) {
            for(int j=1; j<=n; j++) {
                if(i==j) continue;
                if(adjMatrix[i][j]==INF && adjMatrix[j][i]==INF){
                    ++res;
                    break;
                }
            }
        }
        
		// 순위가 결정된 인원 수를 반환해야 한다. 이때 반환값이 음수가 나오지 않도록 주의한다.
        if((n-res)>0) return n-res;
        else return 0;
		
//         boolean[] flag = new boolean[n+1];
//         Arrays.fill(flag, true);
//         for(int i=1; i<=n; i++) {
//             for(int j=1; j<=n; j++) {
//                 if(i==j) continue;
//                 if(adjMatrix[i][j]==INF && adjMatrix[j][i]==INF) {
//                     flag[i] = false;
//                     break;
//                 }
//             }
//         }
        
//         int answer = 0;
//         for(int i=1; i<=n; i++) {
//             if(flag[i]) ++answer;
//         }
        
//         return answer;
        
    }
}