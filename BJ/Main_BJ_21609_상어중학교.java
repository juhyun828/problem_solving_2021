import java.io.*;
import java.util.*;
// 210931

public class Main_BJ_21609_상어중학교 {
    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};

    static class BlockGroup implements Comparable<BlockGroup> {
        int mainBlockR, mainBlockC, totalCnt, rainbowCnt;
        boolean[][] group;

        public BlockGroup(int mainBlockR, int mainBlockC, int totalCnt, int rainbowCnt, boolean[][] group) {
            this.mainBlockR = mainBlockR;
            this.mainBlockC = mainBlockC;
            this.totalCnt = totalCnt;
            this.rainbowCnt = rainbowCnt;
            this.group = group;
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.totalCnt == o.totalCnt) {
                if (this.rainbowCnt == o.rainbowCnt) {
                    if (this.mainBlockR == o.mainBlockR) {
                        return Integer.compare(this.mainBlockC, o.mainBlockC)*-1;
                    }
                    return Integer.compare(this.mainBlockR, o.mainBlockR)*-1;
                }
                return Integer.compare(this.rainbowCnt, o.rainbowCnt)*-1;
            }
            return Integer.compare(this.totalCnt, o.totalCnt)*-1; // 크기가 가장 큰 블록 그룹
        }
    }

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
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());
        int score = 0;

        map = new int[N+1][N+1];
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=1; j<=N; j++) {
                map[i][j] = stoi(st.nextToken());
                if (map[i][j]==0) map[i][j]=-2; // 무지개 블록
            }
        }

        while (true) {
            // 1. 블록 그룹 찾기 -> 하나만
            v = new boolean[N+1][N+1];
            ArrayList<BlockGroup> blockGroups = new ArrayList<BlockGroup>();
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (map[i][j]>=1 && !v[i][j]) {
                        BlockGroup res = bfs(map[i][j], i, j);
                        if(res!=null) blockGroups.add(res);
                    }
                }
            }

            if (blockGroups.size()==0) break;

            // 2. 해당 블록 지우고 점수 획득
            Collections.sort(blockGroups);
            score += Math.pow(blockGroups.get(0).totalCnt, 2);
            remove(blockGroups.get(0)); // 해당 자리 0으로


            // 3. 중력 작용
            map = gravity();

            // 4. 반시계방향 90도 회전
            map = rotateCounterClockWise();

            // 5. 다시 중력 작용
            map = gravity();
        }

        System.out.println(score);
        br.close();
    }

    // 1. 블록 그룹들 찾기
    static BlockGroup bfs(int blockNum, int r, int c) {
        int totalCnt = 1, rainbowCnt = 0;
        Queue<Pos> q = new ArrayDeque<Pos>();
        boolean[][] group = new boolean[N+1][N+1];
        q.offer(new Pos(r, c));
        group[r][c] = true;
        v[r][c] = true; // 그룹이 찾아진 블록

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            // 인접 위치에 같은 블록이나 무지개 블록이 있는지 찾는다.
            for (int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (!valid(nr, nc) || group[nr][nc] || map[nr][nc]==-1) continue;
                if (map[nr][nc]==-2) { // 무지개 블록은 얼마든지 추가 가능
                    group[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                    ++rainbowCnt;
                    ++totalCnt;

                } else if (map[nr][nc]==blockNum) { // 일반 블록은 같은 번호만
                    v[nr][nc] = true;
                    group[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                    ++totalCnt;
                }
            }
        }

        if (totalCnt<2) return null;

        // 메인 블록 찾기 : 일반 블록 중 행 번호 작고, 열 번호 작고
        ArrayList<Pos> generalList = new ArrayList<Pos>();
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (group[i][j] && map[i][j]==blockNum) {
                    generalList.add(new Pos(i, j));
                }
            }
        }

        Collections.sort(generalList, new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                if (o1.r == o2.r) {
                    return Integer.compare(o1.c, o2.c);
                }
                return Integer.compare(o1.r, o2.r);
            }
        });

        BlockGroup blcokGroup = new BlockGroup(generalList.get(0).r, generalList.get(0).c, totalCnt, rainbowCnt, group);
        return blcokGroup;
    }

    // 2. 해당 블록 지우고 점수 획득
    static void remove(BlockGroup blockGroup) {
        boolean[][] group = blockGroup.group;
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (group[i][j]) {
                    map[i][j] = 0; // 지운 칸
                 }
            }
        }
    }

    // 3, 5. 중력 작용
    static int[][] gravity() {
        int[][] newMap = new int[N+1][N+1];
        for (int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                if (map[i][j]==-1) {
                    newMap[i][j] = -1; // 검은 블록은 이동 불가
                }
            }
        }

        // 무지개 블록, 일반 블록 옮기기
        for (int i=N; i>=1; i--) {
            for (int j=1; j<=N; j++) {
                if (map[i][j]>=1 || map[i][j]==-2) {
                    int num = map[i][j];
                    int ni = i;
                    newMap[ni][j] = num;
                    while (true) { // 행 번호 큰 칸으로 이동
                        if (ni+1>N || newMap[ni+1][j]!=0) break;
                        else {
                            newMap[ni][j] = 0;
                            ni += 1;
                            newMap[ni][j] = num;
                        }
                    }
                }
            }
        }
        return newMap;
    }

    static int[][] rotateCounterClockWise() { // 반시계 방향으로
        int[][] newMap = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                int num = map[i][j];
                newMap[N+1-j][i] = num;
            }
        }
        return newMap;
    }

    static boolean valid(int nr, int nc) {
        if (nr<1 || nr>N || nc<1 || nc>N) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
