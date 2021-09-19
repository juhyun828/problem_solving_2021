import java.io.*;
import java.util.*;
// 210919

public class Main_BJ_14502_연구소 {
    static int N, M, max;
    static int[][] map, newMap;
    static Pos[] wall;
    static ArrayList<Pos> blank;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        max = 0;
        blank = new ArrayList<Pos>();

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                map[i][j] = stoi(st.nextToken());
                if(map[i][j] == 0) {
                    blank.add(new Pos(i, j));
                }
            }
        }

        wall = new Pos[3]; // 벽이 세워질 위치
        combination(0, 0);

        System.out.println(max);
        br.close();
    }

    static void combination(int L, int cnt) {
        if(cnt >= 3) {
            setWall();
            bfs();
            return;
        }

        if(L >= blank.size()) return;

        wall[cnt] = blank.get(L);
        combination(L+1, cnt+1);

        combination(L+1, cnt);
    }

    static void setWall() {
        newMap = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        for(Pos pos: wall) {
            newMap[pos.r][pos.c] = 1;
        }
    }

    static void bfs() {
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 바이러스들 큐에 입력
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(newMap[i][j]==2) {
                    q.offer(new Pos(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(valid(nr, nc) && newMap[nr][nc]==0) {
                    newMap[nr][nc] = 2;
                    q.offer(new Pos(nr, nc));
                }
            }
        }

        int blankCnt = countSafe();
        max = Math.max(blankCnt, max);
    }

    static int countSafe() {
        int blankCnt = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(newMap[i][j]==0) ++blankCnt;
            }
        }

        return blankCnt;
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}