import java.io.*;
import java.util.*;
// 210403
// 시간 초과

public class Main_BJ_1261_알고스팟_dfs {
    static int M, N, min;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/res/input_BJ_1261_알고스팟.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = stoi(st.nextToken()); // 가로
        N = stoi(st.nextToken()); // 세로
        min = Integer.MAX_VALUE;

        map = new int[N+1][M+1];
        v = new boolean[N+1][M+1];

        char[] chArr;
        for(int i=1; i<=N; i++) {
            chArr = br.readLine().toCharArray();
            for(int j=1; j<=M; j++) {
                map[i][j] = chArr[j-1]-'0';
            }
        }

        // 시작점 방문
        v[1][1] = true;
        dfs(new Pos(1, 1, 0));

        System.out.println(min);

        br.close();
    }

    static void dfs(Pos cur) {
        // 도착점에 도착 시 벽을 부순 개수의 최소값 비교 및 초기화
        if(cur.r==N && cur.c==M) {
            min = Math.min(min, cur.cnt);
            return;
        }

        // 현재까지 벽을 깬 개수가 앞서 찾은 최소값보다 크면 진행x - 가지치기
        if(cur.cnt>=min) return;

        // 4방 탐색
        int nr, nc;
        for(int d=0; d<4; d++) {
            nr = cur.r + dr[d];
            nc = cur.c + dc[d];

            // 1. 범위체크 2. 방문 여부 체크
            if(nr<1 || nr>N || nc<1 || nc>M || v[nr][nc]) continue;

            // 3. 벽인지 여부 체크
            if(map[nr][nc] == 0) {
                v[nr][nc] = true;
                dfs(new Pos(nr, nc, cur.cnt));
                v[nr][nc] = false;
            }
            else if(map[nr][nc] == 1) {
                v[nr][nc] = true;
                dfs(new Pos(nr, nc, cur.cnt+1));
                v[nr][nc] = false;
            }
        }
    }

    static class Pos {
        int r, c, cnt;

        public Pos(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

}
