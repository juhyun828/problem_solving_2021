import java.io.*;
import java.util.*;
// 210410

public class Main_BJ_17471_게리멘더링 {
    static int N, min;
    static ArrayList<Integer>[] adjList;
    static int[] population, teams;

    // 1. 구역 두개로 나누기
    static void dfs(int cnt, int L) {
        if(L==N+1) { // 1부터 시작
            if(cnt==0 || cnt==N) return;

            // 2. 각 구역이 연결되었는지 확인
            if(bfs(1) && bfs(2)) {
                // 3. min 갱신
                min = Math.min(min, getCount());
            }
            return;
        }

        teams[L] = 1;
        dfs(cnt+1, L+1);
        teams[L] = 2;
        dfs(cnt, L+1);
    }

    // 2. 각 구역이 연결되었는지 확인
    static boolean bfs(int type) {
        boolean[] v = new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<Integer>();

        // type번 구역의 제일 첫 지역 번호 넣기
        for(int i=1; i<=N; i++) {
            if(teams[i] == type) {
                q.offer(i);
                v[i] = true;
                break;
            }
        }

        int cur;
        // 탐색 시작
        while(!q.isEmpty()) {
            cur = q.poll();

            for(int next: adjList[cur]) {
                if(v[next]) continue;;
                if(teams[next]!=type) continue;
                q.offer(next);
                v[next] = true;
            }
        }

        // 해당 지역을 모두 탐색했는지 확인
        for(int i=1; i<=N; i++) {
            if(teams[i]!=type) continue;
            if(!v[i]) return false;
        }
        return true;
    }

    // 3. min 갱신
    static int getCount() {
        int point1 = 0;
        int point2 = 0;

        for(int i=1; i<=N; i++) {
            if(teams[i]==1) point1 += population[i];
            else if(teams[i]==2) point2 += population[i];
        }

        return Math.abs(point1-point2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input_BJ_17471_게리멘더링.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        min = Integer.MAX_VALUE;
        population = new int[N+1]; // 1번 지역부터
        teams = new int[N+1];

        // 인구 수
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            population[i] = stoi(st.nextToken());
        }
        
        // 인접리스트
        adjList = new ArrayList[N+1];
        int l, to;
        for(int i=1; i<=N; i++) {
            adjList[i] = new ArrayList<Integer>();
            st = new StringTokenizer(br.readLine());
            l = stoi(st.nextToken());
            for(int j=0; j<l; j++) {
                to = stoi(st.nextToken());
                adjList[i].add(to);
            }
        }

        dfs(0, 1);

        System.out.println(min==Integer.MAX_VALUE ? -1 : min);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
