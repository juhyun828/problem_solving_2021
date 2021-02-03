// 210203
 
import java.io.*;
import java.util.*;
 
public class Solution_D3_2805_농작물수확하기 {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for (int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
             
            int[][] map = new int[N][N];
             
            for (int r=0; r<N; r++) {
                String[] str = sc.next().split("");
                for (int c=0; c<N; c++) {
                    map[r][c] = Integer.parseInt(str[c]);
                }
            }
             
            int mid = N/2;
            int d;
            int sum = 0;
            for (int r=0; r<N; r++) {
                if (r <= mid) d = r;
                else d = mid - 1*(r-mid);
                sum += map[r][mid];
                for (int z=1; z<=d; z++) {
                    sum += map[r][mid+z];
                    sum += map[r][mid-z];
                }
            }
             
            System.out.println("#" + tc + " " + sum);       
        }
 
        sc.close();
    } // main
 
}