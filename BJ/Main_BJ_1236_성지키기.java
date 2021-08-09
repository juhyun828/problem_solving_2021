import java.io.*;
import java.util.*;
// 210809

public class Main_BJ_1236_성지키기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = stoi(st.nextToken());
        int M = stoi(st.nextToken());
        boolean[] row = new boolean[N];
        boolean[] col = new boolean[M];
        char[][] map = new char[N][M];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(map[i][j]=='X') { // 경비원
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        int rCnt = 0, cCnt = 0;
        for(int i=0; i<N; i++) {
            if(!row[i]) ++rCnt;
        }
        for(int i=0; i<M; i++) {
            if(!col[i]) ++cCnt;
        }
        System.out.println(Math.max(rCnt, cCnt));

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}