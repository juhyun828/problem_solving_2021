import java.io.*;
import java.util.*;
// 210410

public class Main_BJ_17779_게리맨더링2 {
    static int N;
    static int[][] population, map;
    static ArrayList<Data> list;
    static int minRes;

    static class Data {
        int x, y, d1, d2;

        public Data(int x, int y, int d1, int d2) {
            this.x = x;
            this.y = y;
            this.d1 = d1;
            this.d2 = d2;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input_BJ_17779_게리맨더링2.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = stoi(br.readLine());
        population = new int[N+1][N+1];
        list = new ArrayList<Data>();
        minRes = Integer.MAX_VALUE;
        
        // 인구 입력
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++) {
                population[i][j] = stoi(st.nextToken());
            }
        }

        // x -> y -> d1+d2 -> d1 -> d2 결정
        for(int x=1; x<=N; x++) {
            for(int y=1; y<=N; y++) {
                for(int d=2; d<=N-x; d++) {
                    // d1 + d2 = d;
                    for(int d1=1; d1<=d; d1++) {
                        int d2 = d - d1;
                        if(check(x, y, d1, d2)) {
                            list.add(new Data(x, y, d1, d2));
                        }
                    }
                }
            }
        } // for

        for(Data data: list) {
            divide(data);
        }

        System.out.println(minRes);
        br.close();
    }

    static void divide(Data data) {
        map = new int[N+1][N+1];
        int x = data.x;
        int y = data.y;
        int d1 = data.d1;
        int d2 = data.d2;

        // 5번 선거구 - 경계
        for(int r=x, c=y; r<=x+d1; r++,c--) {
            if(r<1||r>N||c<1||c>N) continue;
            map[r][c]=5;
        }
        for(int r=x, c=y; r<=x+d2; r++,c++) {
            if(r<1||r>N||c<1||c>N) continue;
            map[r][c]=5;
        }
        for(int r=x+d1, c=y-d1; r<=x+d1+d2; r++,c++) {
            if(r<1||r>N||c<1||c>N) continue;
            map[r][c]=5;
        }
        for(int r=x+d2, c=y+d2; r<=x+d2+d1; r++,c--) {
            if(r<1||r>N||c<1||c>N) continue;
            map[r][c]=5;
        }

        // 5번 선거구 - 경계 안
        ArrayList<int[]>[] tmpList = new ArrayList[N+1];
        for(int r=1; r<=N; r++) {
          tmpList[r] = new ArrayList<int[]>();
          for(int c=1; c<=N; c++) {
              if(map[r][c]==5) {
                  tmpList[r].add(new int[] {r, c});
              }
          }
        }

        for(ArrayList<int[]> tmp: tmpList) {
            if(tmp==null || tmp.size()<2) continue;
            int[] start = tmp.get(0);
            int[] end = tmp.get(1);

            for(int r=start[0]; r<=end[0]; r++) {
                for(int c=start[1]; c<=end[1]; c++) {
                    map[r][c]=5;
                }
            }
        }

        // 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
        for(int r=1; r<x+d1; r++) {
            for(int c=1; c<=y; c++) {
                if(r<1||r>N||c<1||c>N||map[r][c]==5) continue;
                map[r][c] = 1;
            }
        }

        // 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
        for(int r=1; r<=x+d2; r++) {
            for(int c=y+1; c<=N; c++) {
                if(r<1||r>N||c<1||c>N||map[r][c]==5) continue;
                map[r][c] = 2;
            }
        }

        // 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        for(int r=x+d1; r<=N; r++) {
            for(int c=1; c<y-d1+d2; c++) {
                if(r<1||r>N||c<1||c>N||map[r][c]==5) continue;
                map[r][c] = 3;
            }
        }

        // 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
        for(int r=x+d2+1; r<=N; r++) {
            for(int c=y-d1+d2; c<=N; c++) {
                if(r<1||r>N||c<1||c>N||map[r][c]==5) continue;
                map[r][c] = 4;
            }
        }
        
        minRes = Math.min(minRes, getCount());
    }

    static int getCount() {
        int[] total = new int[6];
        int type;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                type = map[r][c];
                total[type] += population[r][c];
            }
        }

        Arrays.sort(total);
        int max = total[5];
        int min = total[1];
        return max-min;
    }

    static boolean check(int x, int y, int d1, int d2) {
        if(1<= (y-d1) && (y-d1)<y && y<(y+d2) && (y+d2)<=N  ) return true;
        return false;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
