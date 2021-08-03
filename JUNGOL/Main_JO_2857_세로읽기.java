import java.io.*;
import java.util.*;
// 210609
// 문자의 기본값은 내부적으로 MIN_VALUE = '\u0000' 로 표시되는 Character.MIN_VALUE

public class Main_JO_2857_세로읽기 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ans = "";
        int rowNum = 5, colNum = 15;

        char[][] map = new char[rowNum][colNum];

        // 입력
        for(int r=0; r<rowNum; r++) {
            char[] chArr = br.readLine().toCharArray();
            for(int c=0; c<chArr.length; c++) {
                map[r][c] = chArr[c];
            }
        }

        // 열 우선 탐색
        for(int c=0; c<colNum; c++) {
            for(int r=0; r<rowNum; r++) {
                // if(map[r][c]==' ') continue;
                if (map[r][c]==Character.MIN_VALUE) continue;
                else ans += map[r][c];
            }
        }

        System.out.println(ans);
        br.close();
    }
}
