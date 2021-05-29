import java.io.*;
import java.util.*;
// 210529
public class Main_BJ_1303_전쟁전투 {
    static char[][] map;
    static int N, M; // 가로, 세로
    static int white, blue;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean[][] v;
    static Queue<Pos> q;

    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input_BJ_1303_전쟁전투.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken()); // 가로
        M = stoi(st.nextToken()); // 세로
        map = new char[M][N];
        white = 0; blue = 0;
        v = new boolean[M][N];
        q = new ArrayDeque<>();

        for(int i=0; i<M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                if(!v[i][j]) {
                    q.offer(new Pos(i, j));
                    v[i][j] = true;
                    bfs(map[i][j]);
                }
            }
        }

        System.out.println(white + " " + blue);
        br.close();
    }

    static void bfs(char color) {
        int res = 1;
        while(!q.isEmpty()){
            Pos cur = q.poll();
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr>=M || nr<0 || nc>=N || nc<0 || map[nr][nc]!=color || v[nr][nc]) continue;
                q.offer(new Pos(nr, nc));
                v[nr][nc] = true;
                ++res;
            }
        }
        if(color=='W') white += Math.pow(res, 2);
        else blue += Math.pow(res, 2);
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
