import java.io.*;
import java.util.*;
// 210914

public class Main_BJ_16918_봄버맨 {
    static StringBuilder sb;
    static int R, C, N;
    static int[][] map;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = stoi(st.nextToken());
        C = stoi(st.nextToken());
        N = stoi(st.nextToken()); // N초 후
        map = new int[R][C];

        for(int i=0; i<R; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                if(tmp[j]=='.') map[i][j] = -1;// 빈칸
                else map[i][j]=0; // 초기 폭탄
            }
        }

        for(int time=1; time<=N; time++) {
            // 빈칸에 폭탄 설치
            if (time>=2) install(time); // 1초부터 빈칸에 폭탄을 설치하기 시작했으면 2초에 설치가 모두 끝난 것
            // time-3에 설치된 폭탄들은 터진다.
            if(time>=3) bomb(time); // time=2에 빈칸(-1)을 -1초에 터진 것으로 인식 못하게
        }

        // 출력
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j]==-1) sb.append('.');
                else sb.append('O');
            }
            if(i<R-1) sb.append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static void install(int time) {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j]==-1) map[i][j] = time;
            }
        }
    }

    static void bomb(int time) {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j]==time-3) { // 3초 전에 터진 폭탄
                    map[i][j] = -1; // 폭탄 터짐
                    // 연쇄 작용으로 4방 탐색하여 폭탄 제거
                    for(int d=0; d<4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (valid(nr, nc) && map[nr][nc]!=-1 && map[nr][nc]!=time-3) {
                            map[nr][nc] = -1; // 폭탄 터짐
                        }
                    }
                }
            }
        }
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=R || nc<0 || nc>=C) return false;
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}