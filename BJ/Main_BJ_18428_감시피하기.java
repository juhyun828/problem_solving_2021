import java.io.*;
import java.util.*;
// 210603

public class Main_BJ_18428_감시피하기 {
    static int N;
    static char[][] map, mapClone;
    static ArrayList<Pos> studentList, blankList;
    static boolean[] isSelected;
    static boolean flag;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

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

        N = stoi(br.readLine());
        map = new char[N][N];
        studentList = new ArrayList<>();
        blankList = new ArrayList<>();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j]=='X') { // 빈칸
                    blankList.add(new Pos(i, j));
                } else if (map[i][j]=='S') { // 학생
                    studentList.add(new Pos(i, j));
                }
            }
        }

        flag = false;
        isSelected = new boolean[blankList.size()];

        combination(0, 0);

        if(!flag)
            System.out.println("NO");
        br.close();
    }

    // 전체 빈칸에서 3개 뽑기
    static void combination(int cnt, int L) {
        if(flag) return;;

        if(cnt==3) {
            buildWall();
            if(check()) {
                flag = true;
                System.out.println("YES");
            }
            return;
        }

        if(L >= blankList.size()){
            return;
        }

        isSelected[L] = true;
        combination(cnt+1, L+1);
        isSelected[L] = false;
        combination(cnt, L+1);
    }

    // 뽑힌 세 빈칸에 벽 설치
    static void buildWall() {
        mapClone = new char[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                mapClone[i][j] = map[i][j];
            }
        }

        for(int i=0; i<isSelected.length; i++) {
            if(isSelected[i]) {
                int r = blankList.get(i).r;
                int c = blankList.get(i).c;
                mapClone[r][c] = 'O'; // 벽 세우기
            }
        }
    }

    static boolean check() {
        //  모든 학생의 경우 확인
        for(Pos stu: studentList) {
            for(int d=0; d<4; d++) {
                int nr = stu.r;
                int nc = stu.c;
                // 그 방향으로 계속 가야함
                while(true) {
                    nr += dr[d];
                    nc += dc[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=N) break;
                    if(mapClone[nr][nc]=='T') return false;
                    if(mapClone[nr][nc]=='O') break;
                }

            }
        }
        return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
