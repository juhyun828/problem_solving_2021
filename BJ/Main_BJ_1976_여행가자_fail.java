import java.io.*;
import java.util.*;
// 210814

public class Main_BJ_1976_여행가자_fail {
    static int N, M;
    static int[] parents, plans;
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String ans = "YES";

        N = stoi(br.readLine());
        M = stoi(br.readLine());
        adjMatrix = new int[N+1][N+1];

        plans = new int[M];
        parents = new int[N+1];
        for(int i=1; i<=N; i++) {
            parents[i] = i;
        }

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++) {
                adjMatrix[i][i] = stoi(st.nextToken());
                if(adjMatrix[i][j]==1) {
                    // 연결된 부분은 합집합 연산
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<M; i++) {
            plans[i] = stoi(st.nextToken());
        }

        int start = plans[0]; // 여행의 시작지
        start = find(start);

        for(int i=1; i<M; i++) {
            int next = plans[i]; // 다음 도착지
            // 맨 처음 출발 도시와 연결되있지 않은 도시가 있으면 여행 계획이 불가능
            if(start != find(next)) {
                ans = "NO";
                break;
            }
        }

        System.out.println(ans);
        br.close();
    }

    static int find(int x) {
        if(parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y) {
            if(x<y) {
                parents[y] = x;
            } else {
                parents[x] = y;
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
