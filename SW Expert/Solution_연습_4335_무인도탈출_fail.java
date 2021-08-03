import java.io.*;
import java.util.*;
// 210609

public class Solution_연습_4335_무인도탈출_fail {
    static int boxCnt, max;
    static int[][] box;
    static int[][] flipArr;
    static int[] boxRes;
    static boolean[] boxV;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        flipArr = new int[6][3];
        flip(0);

        int T = stoi(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            max = 0;
            boxCnt = stoi(br.readLine());
            box = new int[boxCnt][3];
            boxRes = new int[boxCnt];
            boxV = new boolean[boxCnt];

            for(int i=0; i<boxCnt; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<3; j++) {
                    box[i][j] = stoi(st.nextToken());
                }
            }

            // 2. 순열로 박스 순서 구하기
            permutateBox(0);
            sb.append("#" + tc + "" + max + "\n");
        }

        System.out.println(sb.toString());
    }

    // 3. 박스 순서들 대로 박스 쌓기
    static void build(int level, int lastGaro, int lastSero, int lastHeight, int totalHeight, int buildCnt) {
        if(level==buildCnt+1) {
            if(buildCnt == 0) return; // 박스를 하나도 쌓지 않음
            max = Math.max(max, totalHeight);
            return;
        }

        // level번째 박스를 쌓는다.
        for(int i=0; i<6; i++) {
            // 회전하는 경우의 수 0 가로, 1 세로, 2 높이
            int garoIdx = flipArr[i][0];
            int seroIdx = flipArr[i][1];
            int heightIdx = flipArr[i][2];
            int garo = box[level][garoIdx];
            int sero = box[level][seroIdx];
            int height = box[level][heightIdx];

            System.out.println(garo + ", " + sero + ", " + height);
            // 이전 박스와 크기 비교하여 쌓을 수 있는지 확인한다.
            if (level==1) {
                build(level+1, garo, sero, height, totalHeight+height, buildCnt+1);
            }
            else {
                if(lastGaro>=garo && lastSero>=sero) {
                    build(level+1, garo, sero, height, totalHeight+height, buildCnt+1);
                }
            }
            }

        // 쌓지 않는다.
        build(level+1, lastGaro, lastSero, lastHeight, totalHeight, buildCnt);
    }


    // 2. 순열로 박스들 순서 구하기
    static void permutateBox(int L) {
        if(L==boxCnt) {
            System.out.println(Arrays.toString(boxRes));
            // 3. 박스 순서들 대로 박스 쌓기
            build(1, 0, 0, 0, 0, 0);
            return;
        }
        for(int i=0; i<boxCnt; i++) {
            if(boxV[i]) continue;
            boxRes[L] = i;
            boxV[i] = true;
            permutateBox(L+1);
            boxV[i] = false;
        }
    }

    // 1. 0, 1, 2의 순열 구하기
    static int[] flipRes = new int[3];
    static boolean[] flipV = new boolean[3];
    static int flipCnt = -1;
    static void flip(int L) {
        if(L==3) {
            ++flipCnt;
            for(int i=0; i<3; i++) {
                flipArr[flipCnt][i] = flipRes[i];
            }
            return;
        }
        for(int i=0; i<3; i++) {
            if(flipV[i]) continue;
            flipRes[L] = i;
            flipV[i] = true;
            flip(L+1);
            flipV[i] = false;
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
