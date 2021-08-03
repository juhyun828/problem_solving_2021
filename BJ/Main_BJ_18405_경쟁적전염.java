import java.io.*;
import java.util.*;
// 210405

public class Main_BJ_18405_경쟁적전염 {
    static int N, K, S, X, Y;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static PriorityQueue<Pos> pq;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/res/input_BJ_18405_경쟁적전염.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = stoi(st.nextToken());
        K = stoi(st.nextToken());
        map = new int[N+1][N+1];
        pq = new PriorityQueue<Pos>();

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++) {
                map[i][j] = stoi(st.nextToken());
                if(map[i][j]>0) pq.offer(new Pos(i, j, map[i][j]));
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        S = stoi(st.nextToken());
        X = stoi(st.nextToken());
        Y = stoi(st.nextToken());

        for(int t=1; t<=S; t++) {
            bfs();
        }

        System.out.println(map[X][Y]);

        br.close();
    }

    static void bfs() {
        Pos cur;
        int nr, nc;
        int size = pq.size();

        // Priority Queue 크기만큼 미리 Queue에 뽑아놓음
        // 큐를 따로 쓰지 않고 하나의 PQ에서 돌리면,
        // 새로 감염되는 위치라도 num이 더 작으면 더 앞에 위치해서 안됨
        Queue<Pos> q = new ArrayDeque<Pos>();

        while(size-->0) {
            q.offer(pq.poll());
        }

        while(!q.isEmpty()) {
            cur = q.poll();

            for(int d=0; d<4; d++) {
                nr = cur.r + dr[d];
                nc = cur.c + dc[d];

                if(nr<1 || nr>N || nc<1 || nc>N || map[nr][nc]>0) continue;
                map[nr][nc] = cur.num;
                pq.offer(new Pos(nr, nc, cur.num));
            }
        }
    }

    static class Pos implements Comparable<Pos>{
        int r, c, num;

        public Pos(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.num, o.num);
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
