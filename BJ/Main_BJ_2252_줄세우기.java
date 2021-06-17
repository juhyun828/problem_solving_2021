import java.io.*;
import java.util.*;
// 210617

public class Main_BJ_2252_줄세우기 {
    static int V, E; // 정점: 학생, 간선 : 비교
    static int[] indegree; // 진입차수
    static ArrayList<Integer>[] graph; // 정점마다 연결된 다른 정점들

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());

        indegree = new int[V+1]; // 학생 1번부터
        graph = new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<E; i++) { // 간선 정보 입력
            st = new StringTokenizer(br.readLine()," ");
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            graph[a].add(b);
            ++indegree[b];
        }

        topologySort();

        br.close();
    }

    static void topologySort() {
        Queue<Integer> q = new ArrayDeque();
        // 진입차수 0인 정점들
        for(int i=1; i<=V; i++) {
            if (indegree[i]==0) q.offer(i);
        }

        while(!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur + " ");

            // cur 정점에서 연결된 간선 제거
            for(int next: graph[cur]) {
                --indegree[next];
                // 새롭게 진입차수 0이 된 정점들
                if(indegree[next]==0) q.offer(next);
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

