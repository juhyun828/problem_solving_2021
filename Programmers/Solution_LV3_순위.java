import java.util.*;
// 210629

class Solution_LV3_순위 {
    static int[][] adjMatrix;
    static int INF = 987654321;
    public int solution(int n, int[][] results) {
        // 1. 인접행렬 만들기
        adjMatrix = new int[n+1][n+1];
        for(int i=0; i<=n; i++) {
            Arrays.fill(adjMatrix[i], INF);
        }
        // 대각선
        for(int i=0; i<=n; i++) {
            adjMatrix[i][i] = 0;
        }
        for(int i=0; i<results.length; i++) {
            int from = results[i][0];
            int to = results[i][1];
            adjMatrix[from][to] = 1;
        }
        
        // 2. 플로이드 워샬 알고리즘 - 최단거리 구하기
        // 경출도
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

        // 3. 선수들 중, 다른 선수와 경기한 적이 없는 선수가 있는지 구한다.
        int res=0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i==j) continue;
                if(adjMatrix[i][j]==INF && adjMatrix[j][i]==INF) {
                    ++res;
                    break;
                }
            }
        }
        return n-res;
    }
}