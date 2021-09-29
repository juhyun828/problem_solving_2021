import java.io.*;
import java.util.*;
// 210930

public class Main_BJ_21608_상어초등학교 {
    static int N;
    static int[][] map;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static ArrayList<Student> students;

    static class Student {
        int num;
        int[] prefer;
        int r, c;

        public Student(int num, int[] prefer) {
            this.num = num;
            this.prefer = prefer;
        }

        public Student(int num, int[] prefer, int r, int c) {
            this(num, prefer);
            this.r = r;
            this.c = c;
        }
    }

    static class Pos implements Comparable<Pos> {
        int r, c, nearBlank;

        public Pos(int r, int c, int nearBlank) {
            this.r = r;
            this.c = c;
            this.nearBlank = nearBlank;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.nearBlank == o.nearBlank) {
                if (this.r == o.r) {
                    return Integer.compare(this.c, o.c);
                }
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.nearBlank, o.nearBlank)*-1;
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = stoi(br.readLine());
        map = new int[N+1][N+1];
        students = new ArrayList<Student>();

        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = stoi(st.nextToken()); // 학생번호
            int[] prefer = new int[4];
            for(int j=0; j<4; j++) {
                prefer[j] = stoi(st.nextToken());
            }
            students.add(new Student(num, prefer));
        }

        for (int i=0; i<N*N; i++) {
            //  1. 빈칸 중 인간에 좋아하는 학생들 먼저 찾기
            ArrayList<Pos> list = countPrefers(students.get(i));
            if (list.size()==1){
                map[list.get(0).r][list.get(0).c] = students.get(i).num;
                students.get(i).r = list.get(0).r;
                students.get(i).c = list.get(0).c;
            } else {
                list = countBlanks(list);
                Collections.sort(list);
                map[list.get(0).r][list.get(0).c] = students.get(i).num;
                students.get(i).r = list.get(0).r;
                students.get(i).c = list.get(0).c;
            }
        }

        int total = 0;
        for (int i=0; i<N*N; i++) {
            int num = students.get(i).num;
//            Pos pos = getPos(num);
//            int score = getScore(pos.r, pos.c, students.get(i));
            int score = getScore(students.get(i).r, students.get(i).c, students.get(i));
            total += score;
        }

        System.out.println(total);

        br.close();
    }

    static Pos getPos(int stuNum) {
        for(int r=1; r<=N; r++) {
            for (int c=1; c<=N; c++) {
                if (map[r][c]==stuNum) return new Pos(r, c, 0);
            }
        }
        return new Pos(0, 0, 0);
    }

    static int getScore(int r, int c, Student student) {
        int cnt = countNearPreferStudent(r, c, student); // 인근에 앉은 선호 학생 수
        if (cnt == 2) return 10;
        else if (cnt == 3) return 100;
        else if (cnt == 4) return 1000;
        else return cnt;
    }

    // 1. 빈칸 중 인근에 좋아하는 학생이 몇 명 앉았는지
    static ArrayList<Pos> countPrefers(Student student) {
        ArrayList<Pos> list = new ArrayList<Pos>();
        int max = 0;
        int[][] preferMap = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(map[i][j]==0) {
                    int cnt = countNearPreferStudent(i, j, student);
                    preferMap[i][j] = cnt;
                    max = Math.max(max, cnt);
                }
            }
        }

        // max명인 공간들 찾기
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if (max>0 && preferMap[i][j] == max) {
                    list.add(new Pos(i, j, 0));
                }
            }
        }
        return list;
    }

    // 2. 인근 칸 중 빈칸의 개수가 많은 순서대로
    static ArrayList<Pos> countBlanks(ArrayList<Pos> list) {
        if (list.size()==0) {
            for (int i=1; i<=N; i++) {
                for (int j=1;j<=N; j++) {
                    if (map[i][j]==0) list.add(new Pos(i, j, 0));
                }
            }
        }

        for(Pos pos: list) {
            int cnt = countNearBlanks(pos.r, pos.c);
            pos.nearBlank = cnt;
        }

        return list;
    }

    static int countNearPreferStudent(int r, int c, Student student) {
        int cnt = 0;
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(!valid(nr, nc)) continue;
            if (map[nr][nc] == student.prefer[0] ||
                    map[nr][nc] == student.prefer[1] ||
                    map[nr][nc] == student.prefer[2] ||
                    map[nr][nc] == student.prefer[3]) ++cnt;
        }
        return cnt;
    }

    static int countNearBlanks(int r, int c) {
        int cnt = 0;
        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!valid(nr, nc)) continue;
            if(map[nr][nc]==0) ++cnt;
        }
        return cnt;
    }

    static boolean valid(int nr, int nc) {
        if(nr<1 || nr>N || nc<1 || nc>N) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
