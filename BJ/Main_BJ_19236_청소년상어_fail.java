import java.io.*;
import java.util.*;
// 210404

public class Main_BJ_19236_청소년상어_fail {
static int[][] map;
    static int max;
    static ArrayList<Fish> fishList;
    static int[] dr ={-1, -1,  0,  1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};

    static class Fish implements Comparable<Fish> {
        // 리스트에 들어있는 물고기
        int idx, r, c, dir;
        boolean dead; // true면 죽은거

        public Fish(int idx, int r, int c, int dir) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        public Fish(int idx, int r, int c, int dir, boolean dead) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.dead = dead;
        }


        @Override
        public int compareTo(Fish o) {
            return Integer.compare(this.idx, o.idx);
        }

        @Override
        public String toString() {
            return "Fish {" +
                    "idx=" + idx +
                    ", r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    '}';
        }
    }

    static class Shark {
        int r, c, dir, total;

        public Shark(int r, int c, int dir, int total) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.total = total;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "r=" + r +
                    ", c=" + c +
                    ", dir=" + dir +
                    ", total=" + total +
                    '}';
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }

    static void printMap(int[][] copyMap) {
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                System.out.print(copyMap[i][j]+1 + " ");
            }
            System.out.println();
        }
        System.out.println("------------------------");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input_BJ_19236_청소년상어.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        map = new int[4][4];
        fishList = new ArrayList<>();
        max = 0;

        int num, dir;
        for(int i=0; i<4; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<4; j++){
                num = stoi(st.nextToken())-1;
                dir = stoi(st.nextToken())-1;
                fishList.add(new Fish(num, i, j, dir));
                map[i][j] = num; // -1은 빈칸
            }
        }

        Collections.sort(fishList); // 0번 물고기부터

        // 상어가 (0, 0) 방문
        int startNum = map[0][0];
        int startDir = fishList.get(startNum).dir;

        map[0][0] = -1; fishList.get(startNum).dead=true;

        System.out.println("시작");
        printMap(map);
        // 시작점에서 dfs 시작
        dfs(new Shark(0, 0, startDir, (startNum+1)), map, fishList);
        System.out.println(max);

        br.close();
    }

    static void dfs(Shark shark, int[][] inputMap, ArrayList<Fish> inputList) {
        if(max<shark.total) {
            max = shark.total;
        }

        // 1. copy
        int[][] copyMap = new int[4][4];
        ArrayList<Fish> copyList = new ArrayList<>();

        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                copyMap[i][j] = inputMap[i][j];
            }
        }

        for(Fish fish: inputList) {
            copyList.add(fish);
        }

        // 2. 물고기 이동
        for(Fish fish: copyList) {
            if(fish.dead) continue;
            // 8방향 시도
            int nr, nc;
            for(int i=0; i<8; i++) {
                nr = fish.r + dr[(fish.dir+i)%8];
                nc = fish.c + dc[(fish.dir+i)%8];

                // 범위 밖이거나 상어랑 위치가 같으면 이동x
                if(nr<0 || nr>=4 || nc<0 || nc>=4 || (nr==shark.r && nc==shark.c))
                    continue;
                else { // 이동
                    if(copyMap[nr][nc]==-1) {
                        // map 이동
                        copyMap[nr][nc] = fish.idx;
                        copyMap[fish.r][fish.c] = -1;

                        // list 이동
                        fish.r = nr;
                        fish.c = nc;
                        break;

                    } else {
                        // map 이동
                        int tmpIdx = copyMap[nr][nc];
                        copyMap[nr][nc] = fish.idx;
                        copyMap[fish.r][fish.c] = tmpIdx;

                        // list 이동
                        copyList.get(tmpIdx).r = fish.r;
                        copyList.get(tmpIdx).c = fish.c;

                        fish.r = nr;
                        fish.c = nc;
                        break;
                    }
                }
            }
        } //

        printMap(copyMap);
        // 3. 상어 이동
        int nr=shark.r, nc = shark.c;
        for(int i=0; i<3; i++) {
            nr += dr[shark.dir];
            nc += dc[shark.dir];

            // 범위 밖이거나 물고기가 없는 칸은 이동x
            if(nr<0 || nr>=4 || nc<0 || nc>=4 || copyMap[nr][nc]==-1) continue;
            else {
                int fishIdx = copyMap[nr][nc];

                // 이동, 물고기를 먹음
                copyMap[nr][nc]=-1; copyList.get(fishIdx).dead = true;
                dfs(new Shark(nr, nc, copyList.get(fishIdx).dir, shark.total+(fishIdx+1)), copyMap, copyList);
                copyMap[nr][nc]=fishIdx; copyList.get(fishIdx).dead = false; // 백트래킹
            }
        } //
    }

}
