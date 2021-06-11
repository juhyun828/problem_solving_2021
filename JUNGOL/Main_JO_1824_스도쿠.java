import java.io.*;
import java.util.*;
// 210610

public class Main_JO_1824_스도쿠 {
    static int[][] map;
    static ArrayList<Pos> blankList;

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
        map = new int[10][10];
        blankList = new ArrayList<Pos>();

        StringTokenizer st;

        for(int i=1; i<=9; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=9; j++) {
                map[i][j] = stoi(st.nextToken());
                if(map[i][j]==0) {
                    blankList.add(new Pos(i, j));
                }
            }
        }

        dfs(0);
        br.close();
    }

    static void dfs(int idx) {
        if(!isOk()) return;
        if(idx>=blankList.size()) {
            printMap();
            System.exit(0);
        }

        for(int i=1; i<=9; i++) {
            // idx번째 빈칸에 숫자 i를 넣는다.
            int r = blankList.get(idx).r;
            int c = blankList.get(idx).c;
            map[r][c] = i;
            dfs(idx+1);
            map[r][c] = 0;
        }

    }

    static boolean isOk() {
        boolean[] v = new boolean[10]; // 1~9까지 중복 체크
        // 가로
        for(int r=1; r<=9; r++) {
            v = new boolean[10];
            for(int c=1; c<=9; c++) {
                int cur = map[r][c];
                if(cur==0) continue;
                else {
                    if(v[cur]) return false;
                    v[cur] = true;
                }
            }
        }

        // 세로
        for(int c=1; c<=9; c++) {
            v = new boolean[10];
            for(int r=1; r<=9; r++) {
                int cur = map[r][c];
                if(cur==0) continue;
                else {
                    if(v[cur]) return false;
                    v[cur] = true;
                }
            }
        }

        // 3*3
        for(int r=1; r<=9; r+=3) {
            for(int c=1; c<=9; c+=3) {
                v = new boolean[10];
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {
                        int cur = map[r+i][c+j];
                        if(cur==0) continue;
                        else {
                            if(v[cur]) return false;
                            v[cur] = true;
                        }
                    }
                }
            }
        }

        return true;
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int r=1; r<=9; r++) {
            for(int c=1; c<=9; c++) {
                sb.append(map[r][c]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
