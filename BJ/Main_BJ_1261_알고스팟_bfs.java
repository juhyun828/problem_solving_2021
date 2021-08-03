import java.io.*;
import java.util.*;
// 210702

public class Main_BJ_1261_알고스팟_bfs {
    static int M, N; // 가로, 세로
    // 0, 0 -> N-1, M-1 이동
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = stoi(st.nextToken());
        N = stoi(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            char[] chArr = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = chArr[j]-'0';
            }
        }

        int min = bfs();
        System.out.println(min);
        br.close();
    }

    static int bfs() {
        int[][] v = new int[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(v[i], -1);
        }
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 시작점
        q.offer(new Pos(0, 0));
        v[0][0] = 0;

        while(!q.isEmpty()) {
            Pos cur = q.poll();

            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                if(map[nr][nc]==0) {
                    if(v[nr][nc]==-1 || v[nr][nc] > v[cur.r][cur.c]) {
                        v[nr][nc] = v[cur.r][cur.c];
                        q.offer(new Pos(nr, nc));
                    }
                }
                else { // 벽인 경우
                    if(v[nr][nc]==-1 || v[nr][nc] > v[cur.r][cur.c] + 1) {
                            v[nr][nc] = v[cur.r][cur.c] + 1;
                            q.offer(new Pos(nr, nc));
                        }
                    }
                }

            }
        return v[N-1][M-1];
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
