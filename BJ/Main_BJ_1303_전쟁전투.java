import java.io.*;
import java.util.*;
// 210708

public class Main_BJ_1303_전쟁전투 {
    static int N, M, totalW, totalB;
    static char[][] map;
    static boolean[][] v;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static class Pos {
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = stoi(st.nextToken()); // 가로
        N = stoi(st.nextToken()); // 세로
        map = new char[N][M];
        v = new boolean[N][M];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        totalW = 0;
        totalB = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!v[i][j]) {
                    if (map[i][j]=='W') {
                        totalW += bfs(i, j);
                    } else if(map[i][j]=='B') {
                        totalB += bfs(i, j);
                    }
                }
            }
        }

        System.out.println(totalW + " " + totalB);
        br.close();
    }

    static int bfs(int r, int c) {
        int total = 0, cnt = 1;
        char color = map[r][c];
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 시작점
        v[r][c] = true;
        q.offer(new Pos(r, c));

        while(!q.isEmpty()) {
            Pos cur = q.poll();
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0 || nr>=N || nc<0 || nc>=M || v[nr][nc] || map[nr][nc]!=color) continue;
                ++cnt;
                v[nr][nc] = true;
                q.offer(new Pos(nr, nc));
            }
        }
        total += Math.pow(cnt, 2);
        return total;
    }

    static int stoi(String str){
        return Integer.parseInt(str);
    }
}
