import java.io.*;
import java.util.*;
// 210528

public class Solution_D4_1251_하나로_pq {
    static int N;
    static double E; // 세율
    static long[][] adjMatrix;

    static class Vertext implements Comparable<Vertext> {
        int no;
        long cost;

        public Vertext(int no, long cost) {
            this.no = no;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertext o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input_D4_1251_하나로.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        int T = stoi(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            N = stoi(br.readLine());
            int[] x = new int[N];
            int[] y = new int[N];

            // 각 섬의 x 좌표
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                x[i] = stoi(st.nextToken());
            }
            // 각 섬의 y 좌표
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                y[i] = stoi(st.nextToken());
            }
            E = Double.parseDouble(br.readLine());

            // 인접 행렬 구성
            adjMatrix = new long[N][N];
            for(int i=0; i<N; i++) {
                for(int j=i; j<N; j++) {
                    adjMatrix[i][j] = adjMatrix[j][i] = getDistance(x[i], x[j], y[i], y[j]);
                }
            }
            sb.append("#" + tc + " " +Math.round(prim()*E) + "\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    // 프림 알고리즘
    static long prim() {
        long totalWeight = 0;
        boolean[] v = new boolean[N];

        // minEdge
        long[] minEdge = new long[N];
        Arrays.fill(minEdge, Long.MAX_VALUE);

        // 시작 정점 거리값
        minEdge[0] = 0;
        int cnt = 0;

        PriorityQueue<Vertext> pq = new PriorityQueue<>();
        pq.offer(new Vertext(0, minEdge[0])); // no, cost

        while(true) {
            // 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 선택
            Vertext minVertex = pq.poll();
            if(v[minVertex.no]) continue; // 이미 방문한 경우

            // 방문하지 않았으면 신장트리에 포함시킴
            v[minVertex.no] = true;
            totalWeight += minVertex.cost;
            if(++cnt == N) break;

            // 해당 정점과 인접한 정점을 더 적은 비용으로 방문 가능한지 찾기
            for(int j=0; j<N; j++) {
                if(!v[j] && minEdge[j] > adjMatrix[minVertex.no][j]) {
                    minEdge[j] = adjMatrix[minVertex.no][j];
                    pq.offer(new Vertext(j, minEdge[j]));
                }
            }

        }
        return totalWeight;
    }

    static long getDistance(int x1, int x2, int y1, int y2) {
        return (long) (Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    public static int stoi(String str) {
        return Integer.parseInt(str);
    }
}