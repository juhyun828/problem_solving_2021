import java.io.*;
import java.util.*;
// 210823

public class Main_BJ_18809_Gaaaaaaaaaarden_fail {
    static int N, M, G, R;
    static int[][] map; // 0 호수, 1 뿌릴수x, 2 뿌릴 수, 3 green, 4 red, 5 꽃
    static ArrayList<Pos> possible, green_selected, red_selected;
    static boolean[] v;
    static int max;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};

    static class Pos {
        int r, c;
        char color;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Pos(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        G = stoi(st.nextToken());
        R = stoi(st.nextToken());
        map = new int[N][M];
        possible = new ArrayList<Pos>();
        green_selected = new ArrayList<Pos>();
        red_selected = new ArrayList<Pos>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                map[i][j] = stoi(st.nextToken());
                if(map[i][j] == 2) {
                    possible.add(new Pos(i, j));
                }
            }
        }

        v = new boolean[possible.size()];
        max = 0; // 최대한 많은 꽃을 피워야 한다.
        combination_green(0, 0);

        System.out.println(max);

        br.close();
    }

    static void combination_green(int cnt, int start) {
        if(cnt==G) {
            green_selected.clear();
            // 선택된 위치들 초록 리스트에 넣는다.
            for(int i=0; i<possible.size(); i++) {
                Pos cur = possible.get(i);
                if(v[i]) {
                    green_selected.add(cur);
                }
            }
            combination_red(0, 0);
        }

        for(int i=start; i<possible.size(); i++) {
            if(!v[i]) {
                v[i] = true;
                combination_green(cnt+1, i+1);
                v[i] = false;
            }
        }
    }

    static void  combination_red(int cnt, int start) {
        if(cnt==R) {
            red_selected.clear();
            // 선택된 위취가 초록 리스트에 없으면 빨강 리스트에 넣는다.
            for(int i=0; i<possible.size(); i++) {
                Pos cur = possible.get(i);
                if(v[i] && !green_selected.contains(cur)) {
                    red_selected.add(cur);
                }
            }
           bfs();
        }

        for(int i=start; i<possible.size(); i++) {
            if(!v[i]) {
                v[i] = true;
                combination_red(cnt+1, i+1);
                v[i] = false;
            }
        }
    }

    static void bfs() {
        int[][] mapCopy = new int[N][M]; // 지도를 복사하여 사용
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                mapCopy[i][j] = map[i][j];
            }
        }
        Queue<Pos> q = new ArrayDeque<Pos>();
        // 초록, 빨강 배양액 뿌리기
        for(Pos pos: green_selected) {
            int r = pos.r;
            int c = pos.c;
            q.offer(new Pos(r, c, 'g'));
            mapCopy[r][c] = 3;
        }
        for(Pos pos: red_selected) {
            int r = pos.r;
            int c = pos.c;
            q.offer(new Pos(r, c, 'r'));
            mapCopy[r][c] = 4;
        }
        int flower = 0;

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(!valid(nr, nc)) continue;

                if(mapCopy[nr][nc] == 1 || mapCopy[nr][nc] == 2) {
                    int color = cur.color == 'g' ? 3 : 4;
                    mapCopy[nr][nc] = color;
                    q.offer(new Pos(nr, nc, cur.color));
                } else {
                    if((cur.color == 'g' && mapCopy[nr][nc] == 4)
                        && (cur.color == 'r' && mapCopy[nr][nc] == 3)) {
                        mapCopy[nr][nc] = 5;
                        ++flower;
                    }
                }
            }
        }

        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(mapCopy[i]));
        }
        System.out.println("====");

        max = Math.max(max, flower);
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}