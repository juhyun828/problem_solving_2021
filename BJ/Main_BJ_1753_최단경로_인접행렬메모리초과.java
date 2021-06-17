import java.io.*;
import java.util.*;
// 210617

public class Main_BJ_1753_최단경로_인접행렬메모리초과 {
    static int V, E, start; // 정점, 간선 수, 시작 정점
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = stoi(st.nextToken());
        E = stoi(st.nextToken());
        adjMatrix = new int[V+1][V+1];
        start = stoi(br.readLine());

        for(int i=0; i<E; i++) { // 간선 정보 입력
            st = new StringTokenizer(br.readLine(), " ");
            int from = stoi(st.nextToken());
            int to = stoi(st.nextToken());
            int weight = stoi(st.nextToken());
            adjMatrix[from][to] = weight;
        }

        dijkstra();
        br.close();
    }

    static void dijkstra() {
        boolean[] v = new boolean[V+1];
        int[] dist = new int[V+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 시작점
        dist[start] = 0;

        for(int c=0; c<V; c++) { // 정점 개수만큼 반복
            int min = Integer.MAX_VALUE;
            int minVertex = 0;

            for(int i=1; i<=V; i++) {
                if(!v[i] && min > dist[i]) { // 방문 x 최소 거리 정점
                    min = dist[i];
                    minVertex = i;
                }
            }
            v[minVertex] = true; // 방문

            // 거리 정보 업데이트 : minVertext를 경유하여 더 적은 비용으로 방문할 수 있는지
            for(int i=1; i<=V; i++) {
                if(!v[i] && adjMatrix[minVertex][i]!=0 && dist[i] > min + adjMatrix[minVertex][i]) {
                    dist[i] = min + adjMatrix[minVertex][i];
                }
            }
        }

        // 출력
        for(int i=1; i<=V; i++) {
            System.out.println(dist[i]==Integer.MAX_VALUE ? "INF" : dist[i]);
        }

    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

