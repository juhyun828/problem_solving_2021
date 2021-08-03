import java.io.*;
import java.util.*;
// 210617

public class Main_BJ_9372_상근이의여행 {
    static int V, E; // 정점 - 국가수, 간선 - 비행기
    static int[][] adjMatrix;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = stoi(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            V = stoi(st.nextToken());
            E = stoi(st.nextToken());
            adjMatrix = new int[V+1][V+1];
            for(int i=0; i<E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = stoi(st.nextToken());
                int to = stoi(st.nextToken());
                adjMatrix[from][to] = 1;
                adjMatrix[to][from] = 1;
            }
            prim();
        }

        br.close();
    }

    static void prim() {
        boolean[] v = new boolean[V+1];
        int[] minEdge = new int[V+1];
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        // 시작점 1로 설정
        minEdge[1] = 0;
        int totalWeight = 0;

        for(int c=0; c<V; c++) { // 정점 개수만큼 반복
            int min = Integer.MAX_VALUE;
            int minVertex = 0;
            // 아직 방문 안하고 정점에서 최소 비용으로 연결된 간선 택
            for(int i=1; i<=V; i++) {
                if(!v[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            if(min==Integer.MAX_VALUE) continue; // 모든 정점이 선택됨
            totalWeight += min; // 시작점의 가중치는 0, 나머지는 1 -> 개수
            v[minVertex] = true;

            // 간선 비용 업데이트
            for(int i=1; i<=V; i++) {
                if(!v[i] && adjMatrix[minVertex][i]!=0 && minEdge[i] > min + adjMatrix[minVertex][i]) {
                    minEdge[i] = adjMatrix[minVertex][i];
                }
            }
        }
        System.out.println(totalWeight);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}

