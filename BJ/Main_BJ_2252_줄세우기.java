import java.io.*;
import java.util.*;
// 210530

// 위상정렬 : 방향 그래프의 모든 노드를 방향성에 거스르지 않도록 순서대로 나열
// 1. 진입 차수가 0인 노드를 큐에 넣는다.
// 큐가 빌 때 까지 2. 큐에서 원소를 꺼내 해당 노드에서 출발하는 간선을 그래프에서 제거한다.
//  3. 새롭게 진입 차수가 0이 된 노드를 큐에 넣는다.

public class Main_BJ_2252_줄세우기 {
    static int N, M; // 노드 , 간선 개수
    static int[] indegree;
    static ArrayList<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input_BJ_2252_줄세우기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken()); // 노드 개수
        M = stoi(st.nextToken()); // 간선 개수
        indegree = new int[N+1]; // 모든 노드에 대한 진입차수는 0으로 초기화
        graph = new ArrayList[N+1]; // 각 노드에 연결된 간선 정보를 담기 위한 연결리스트

        for(int i=0; i<=N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        // 방향 그래프의 간선 정보 입력받기
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = stoi(st.nextToken());
            int b = stoi(st.nextToken());
            // 정점 a에서 b로 이동 가능
            graph[a].add(b);
            // 도착지인 b의 진입 차수를 1 증가
            ++indegree[b];
        }

        topologySort();
        br.close();
    }

    // 위상 정렬 함수
    static void topologySort() {
        Queue<Integer> q = new ArrayDeque<>();

        // 처음 시작할 때는 진입차수 0인 노드를 큐에 삽입
        for(int i=1; i<=N; i++) {
            if(indegree[i]==0) {
                q.offer(i);
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            System.out.print(cur+" ");

            // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
            for(int i=0; i<graph[cur].size(); i++){
                int next = graph[cur].get(i);
                --indegree[next];
                if(indegree[next]==0) {
                    // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                    q.offer(next);
                }
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
