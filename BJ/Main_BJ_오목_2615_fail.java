import java.io.*;
import java.util.*;
// 211003

public class Main_BJ_오목_2615_fail {
    static int[] dr = new int[] {-1,  1, -1, 1};
    static int[] dc = new int[] {-1, -1, 1, 1};
    static int[] pos;
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[19][19];
        pos = new int[2]; // r, c
        int ans = 0;
        StringTokenizer st;

        for(int i=0; i<19; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<19; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        int res = checkRow(map);
        if(res!=0) {
            ans = res;
            System.out.println(ans);
            System.out.println(pos[0] + " " + pos[1]);
            System.exit(0);
        }

        res = checkCol(map);
        if(res!=0) {
            ans = res;
            System.out.println(ans);
            System.out.println(pos[0] + " " + pos[1]);
            System.exit(0);
        }

        res = checkCross(map);
        if(res!=0) {
            ans = res;
            System.out.println(ans);
            System.out.println(pos[0] + " " + pos[1]);
            System.exit(0);
        }

        System.out.println(0);
        br.close();
    }

    static int checkRow(int[][] map) {
        for(int i=0; i<19; i++) {
            for(int j=0; j<=14; j++) {
                if(map[i][j]==0) continue;
                if(j-1>=0 && map[i][j-1]==map[i][j]) continue;
                if(j+5<19 && map[i][j+5]==map[i][j]) continue;
                if(map[i][j]==map[i][j+1]&&
                map[i][j]==map[i][j+2]&&
                map[i][j]==map[i][j+3]&&
                map[i][j]==map[i][j+4]) {
                    pos[0] = i+1; pos[1] = j+1;
                    return map[i][j];
                }
            }
        }
        return 0;
    }

    static int checkCol(int[][] map) {
        for(int i=0; i<=14; i++) {
            for(int j=0; j<19; j++) {
                if(map[i][j]==0) continue;
                if(i-1>=0 && map[i-1][j]==map[i][j]) continue;
                if(i+5<19 && map[i+5][j]==map[i][j]) continue;
                if(map[i][j]==map[i+1][j]&&
                        map[i][j]==map[i+2][j]&&
                        map[i][j]==map[i+3][j]&&
                        map[i][j]==map[i+4][j]) {
                    pos[0] = i+1; pos[1] = j+1;
                    return map[i][j];
                }
            }
        }
        return 0;
    }

    static int checkCross(int[][] map) {
        for(int i=0; i<19; i++) {
            for(int j=0; j<19; j++) {
                if(map[i][j]==0) continue;
                for(int d=0; d<4; d++) {
                    // 이전 위치 확인
                    int ni = i - dr[d];
                    int nj = j - dc[d];
                    if(valid(ni, nj) && map[ni][nj]==map[i][j]) continue;
                    // 다섯 칸 이후 위치 확인
                    ni = i + dr[d]*5;
                    nj = j + dc[d]*5;
                    if(valid(ni, nj) && map[ni][nj]==map[i][j]) continue;
                    boolean flag = true;
                    ArrayList<int[]> posList = new ArrayList<int[]>();
                    for(int z=1; z<=4; z++) {
                        ni = i + dr[d]*z;
                        nj = j + dc[d]*z;
                        if(!valid(ni, nj)) {
                            flag = false;
                            break;
                        }
                        if(valid(ni, nj) && map[i][j] != map[ni][nj]) {
                            flag = false;
                            break;
                        }
                        posList.add(new int[]{ni, nj});
                    }
                    if(flag) {
                        Collections.sort(posList, new Comparator<int[]>() {
                            @Override
                            public int compare(int[] o1, int[] o2) {
                                return Integer.compare(o1[1], o2[1]); // 가장 왼쪽에 있는 노드
                            }
                        });
                        pos[0] = posList.get(0)[0]+1; pos[1] = posList.get(0)[1]+1;
                        return map[i][j];
                    }
                }
            }
        }
        return 0;
    }

    static boolean valid(int ni, int nj) {
        if(ni<0 || ni>=19 || nj<0 || nj>=19) return false;
        else return true;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
