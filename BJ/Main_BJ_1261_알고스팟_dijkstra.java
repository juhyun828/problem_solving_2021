import java.io.*;
import java.util.*;
// 210404

public class Main_BJ_1261_알고스팟_dijkstra {
    static int M, N;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] v; // 방문체크겸 벽 부순 회수 저장해서 작은경우만 누적

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input_BJ_1261_알고스팟.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = stoi(st.nextToken()); // 가로
        N = stoi(st.nextToken()); // 세로

        map = new int[N+1][M+1];
        v = new int[N+1][M+1];
        // 한번도 방문 안한 값
        for(int i=0; i<v.length; i++){
            Arrays.fill(v[i], -1);
        }

        char[] chArr;
        for(int i=1; i<=N; i++) {
            chArr = br.readLine().toCharArray();
            for(int j=1; j<=M; j++) {
                map[i][j] = chArr[j-1]-'0';
            }
        }

        bfs(new Pos(1, 1));
        System.out.println(v[N][M]);

        br.close();
    }

    static void bfs(Pos start) {
        // bfs로 방문하면서 벽을 깬 횟수를 v배열에 누적하며 검색
        Queue<Pos> q = new ArrayDeque<>();

        // 시작지점 방문
        q.offer(start);
        v[1][1] = 0;

        Pos cur;
        int nr, nc;
        while (!q.isEmpty()) {
            cur = q.poll();
            for(int d=0; d<4; d++) {
                nr = cur.r + dr[d];
                nc = cur.c + dc[d];

                if(nr<1 || nr>N || nc<1 || nc>M) continue;

                if(map[nr][nc]==0) {
                    // 가본적 없거나 벽을 덜 부시고도 방문 가능하면
                    if(v[nr][nc]==-1 || v[nr][nc]>v[cur.r][cur.c]) {
                        v[nr][nc] = v[cur.r][cur.c];
                        q.offer(new Pos(nr, nc));
                    }

                } else if(map[nr][nc]==1) {
                    if(v[nr][nc]==-1 || v[nr][nc]>v[cur.r][cur.c]+1) {
                        v[nr][nc] = v[cur.r][cur.c]+1;
                        q.offer(new Pos(nr, nc));
                    }
                }
            }
        }
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}