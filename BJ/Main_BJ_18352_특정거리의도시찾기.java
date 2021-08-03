import java.io.*;
import java.util.*;
// 210405

public class Main_BJ_18352_특정거리의도시찾기 {
    static int N, M, K, X;
    static ArrayList<Integer>[] adjList;
    static StringBuilder sb;
    static boolean flag = false;
    static int[] v;
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/res/input_BJ_18352_특정거리의도시찾기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken()); // 정점 개수
        M = stoi(st.nextToken()); // 간선 개수
        K = stoi(st.nextToken()); // 거리 정보
        X = stoi(st.nextToken()); // 출발 도시 번호
		
        adjList = new ArrayList[N+1];
        v = new int[N+1];
        sb = new StringBuilder();

        for(int i=0; i<=N; i++) {
            adjList[i] = new ArrayList<Integer>();
        }

        int from, to;
        // 간선 정보
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            from = stoi(st.nextToken());
            to = stoi(st.nextToken());
            adjList[from].add(to);
        }

        bfs(X);

        boolean flag = false;
        for(int i=1; i<=N; i++) {
            if(v[i]==K) {
                flag=true;
                sb.append(i + "\n");
            }
        }
        if(flag) System.out.println(sb.toString());
        else System.out.println(-1);

        br.close();
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        Arrays.fill(v, -1);

        q.offer(start);
        v[start]=0;

        Integer cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            for(int i=0; i<adjList[cur].size(); i++) {
                int next = adjList[cur].get(i);
                if(v[next]!=-1) continue;
                v[next] = v[cur]+1;
                q.offer(next);
            }
        }

    }
    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
