import java.io.*;
import java.util.*;
// 210825

public class Main_BJ_18809_Gaaaaaaaaaarden {
    static int N, M, G, R;
    static int[][] map; // 0 호수, 1 뿌릴 수x, 2 뿌릴 수o, 3 green, 4 red, 5 꽃
    static ArrayList<Pos> possible, greenList, redList;
    static boolean[] v;
    static int max;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static Queue<Pos> q;

    static class Pos {
        int r, c, color;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Pos(int r, int c, int color) {
            this(r, c);
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
        greenList = new ArrayList<Pos>();
        redList = new ArrayList<Pos>();

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
        combinationGreen(0, 0);

        System.out.println(max);

        br.close();
    }

    static void combinationGreen(int r, int start) {
        if(r==G) {
            greenList.clear();
            // 선택된 위치들을 초록 리스트에 넣는다.
            for(int i=0; i<possible.size(); i++) {
                if(v[i]) {
                    Pos cur = possible.get(i);
                    greenList.add(cur);
                }
            }
            combinationRed(0, 0);
        }

        for(int i=start; i<possible.size(); i++) {
            if(!v[i]) {
                v[i] = true;
                combinationGreen(r+1, i+1);
                v[i] = false;
            }
        }
    }

    static void  combinationRed(int r, int start) {
        if(r==R) {
            redList.clear();
            // 선택된 위취가 초록 리스트에 없으면 빨강 리스트에 넣는다.
            for(int i=0; i<possible.size(); i++) {
                Pos cur = possible.get(i);
                if(v[i] && !greenList.contains(cur)) {
                    redList.add(cur);
                }
            }
            bfs();
        }

        for(int i=start; i<possible.size(); i++) {
            if(!v[i]) {
                v[i] = true;
                combinationRed(r+1, i+1);
                v[i] = false;
            }
        }
    }

    static void bfs() {
        q = new ArrayDeque<>();
        int[][] cloneMap = copyMap();
        int[][] timeMap = new int[N][M];
        int time = 0;
        int flower = 0;

        while (!q.isEmpty()) {
            ++time;
            int size = q.size();
            for(int i=0; i<size; i++) { // 1초당 퍼지는 배양액
                Pos cur = q.poll();
                if(cloneMap[cur.r][cur.c]==5) continue; // 이전 초에서 꽃을 피운 경우

                for(int d=0; d<4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if(!valid(nr, nc)) continue;

                    if(cloneMap[nr][nc]==1 || cloneMap[nr][nc]==2) {
                        cloneMap[nr][nc] = cur.color;
                        timeMap[nr][nc] = time;
                        q.offer(new Pos(nr, nc, cur.color));
                    } else if((cloneMap[nr][nc]==3 && cur.color==4 && timeMap[nr][nc]==time)
                            || (cloneMap[nr][nc]==4 && cur.color==3 && timeMap[nr][nc]==time)){
                        // 같은 시간에 두 색의 배양액에 도달하면 꽃을 피운다.
                        cloneMap[nr][nc] = 5;
                        ++flower;
                    }
                }
            }
        }
        max = Math.max(max, flower);
    }

    static int[][] copyMap() {
        int[][] cloneMap = new int[N][M];
        for(int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                cloneMap[i][j] = map[i][j];
            }
        }

        // 3: 초록, 4: 빨강
        for(Pos pos: greenList) {
            int r = pos.r;
            int c = pos.c;
            cloneMap[r][c] = 3;
            q.offer(new Pos(r, c, 3));
        }
        for(Pos pos: redList) {
            int r = pos.r;
            int c = pos.c;
            cloneMap[r][c] = 4;
            q.offer(new Pos(r, c, 4));
        }
        return cloneMap;
    }

    static boolean valid(int nr, int nc) {
        if(nr<0 || nr>=N || nc<0 || nc>=M) return false;
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}