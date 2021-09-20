import java.io.*;
import java.util.*;
// 210920

public class Main_BJ_2578_빙고 {
    static int[][] map;
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[5][5];
        StringTokenizer st;
        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<5; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<5; j++) {
                int num = stoi(st.nextToken());
                remove(num);
                if(checkBingo()) {
                    System.out.println(i*5 + (j+1));
                    System.exit(0);
                }
            }
        }

        br.close();
    }

    static void remove(int num) {
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if (map[i][j]==num) {
                    map[i][j] = 0;
                    return;
                }
            }
        }
    }

    static boolean checkBingo() {
        int bingo = 0;
        bingo += checkRow();
        bingo += checkCol();
        bingo += checkCross();
        if (bingo>=3) return true;
        else return false;
    }

    static int checkRow() {
        int zeroCnt = 0;
        for(int i=0; i<5; i++) {
            boolean zeroRow = true;
            for(int j=0; j<5; j++) {
                if (map[i][j]==0) continue;
                else {
                    zeroRow = false;
                    break;
                }
            }
            if (zeroRow) ++zeroCnt;
        }
        return zeroCnt;
    }

    static int checkCol() {
        int zeroCnt = 0;
        for(int i=0; i<5; i++) {
            boolean zeroCol = true;
            for(int j=0; j<5; j++) {
                if (map[j][i]==0) continue;
                else {
                    zeroCol = false;
                    break;
                }
            }
            if (zeroCol) ++zeroCnt;
        }
        return zeroCnt;
    }

    static int checkCross() {
        int zeroCnt = 0;
        boolean zeroCross = true;

        for(int i=0; i<5; i++) {
            if(map[i][i]!=0) {
                zeroCross = false;
                break;
            }
        }
        if (zeroCross) ++zeroCnt;

        zeroCross = true;
        for(int i=0; i<5; i++) {
            if(map[i][4-i]!=0) {
                zeroCross = false;
                break;
            }
        }
        if (zeroCross) ++zeroCnt;

        return zeroCnt;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}