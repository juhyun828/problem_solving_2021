import java.io.*;
import java.util.*;
// 210601

public class Main_p303_커리큘럼 {
    // 위상정렬
    static int V; // 강의(노드) 수
    static int[] indegree; // 진입차수
    static ArrayList<Integer>[] graph; // 각 노드에 연결된 간선 정보
    static int[] times, res;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        V = stoi(br.readLine());
        indegree = new int[V+1];
        graph = new ArrayList[V+1];
        times = new int[V+1];
        res = new int[V+1];
        for(int i=0; i<=V; i++) {
            graph[i] = new ArrayList<>();
        }

        // 방향 그래프의 간선 정보
        for(int i=1; i<=V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = stoi(st.nextToken());
            times[i] = x;
            // 해당 강의를 듣기 위해서 먼저 들어야 하는 강의들 번호
            while(true) {
                x = stoi(st.nextToken());
                if(x == -1) break;
                ++indegree[i]; // 진입 차수 증가
                graph[x].add(i); // 정점 x는 i의 선수강
            }
        }

        topologySort();
        for(int i=1; i<=V; i++) {
            System.out.println(res[i]);
        }
        br.close();
    }

    static void topologySort() {
        for(int i=1; i<=V; i++) {
            res[i] = times[i];
        }
        Queue<Integer> q = new ArrayDeque<>();

        // 진입차수 0인 노드
        for(int i=1; i<=V; i++) {
            if(indegree[i]==0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            // 해당 원소와 연결된 노드들 진입차수에서 1 빼기
            for(int i=0; i<graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                res[next] = Math.max(res[next], res[cur] + times[next]);
                --indegree[next];
                // 새롭게 진입차수가 0이 되는 노드
                if(indegree[next]==0) {
                    q.offer(next);
                }
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
