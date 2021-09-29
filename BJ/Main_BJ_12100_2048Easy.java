import java.io.*;
import java.util.*;
// 210929

public class Main_BJ_12100_2048Easy {
    static int N, max;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = stoi(br.readLine());
        max = 0;
        int[][] map = new int[N][N];
        boolean[][] v = new boolean[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j=0; j<N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        dfs(0, map);
        System.out.println(max);

        br.close();
    }

    static void dfs(int L, int[][] map) {
        if (L >= 5) {
            // max 갱신
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    max = Math.max(max, map[i][j]);
                }
            }
            return;
        }

        dfs(L+1, up(map));
        dfs(L+1, down(map));
        dfs(L+1, right(map));
        dfs(L+1, left(map));
    }

    static int[][] up(int[][] map) { // 위로
        int[][] newMap = new int[N][N];
        boolean[][] v = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if(map[i][j]!=0) {
                    int ni = i;
                    newMap[ni][j] = map[i][j];
                    int num = map[i][j];

                    while (ni-1>=0) {
                        if (newMap[ni-1][j]==0) {
                            newMap[ni][j] = 0;
                            ni -= 1;
                            newMap[ni][j] = num;
                        } else if (newMap[ni-1][j]==num && !v[ni-1][j]) {
                            newMap[ni][j] = 0;
                            ni -= 1;
                            newMap[ni][j] = num*2;
                            v[ni][j] = true;
                            break;
                        } else break;
                    }
                }
            }
        }
        return newMap;
    }

    static int[][] down(int[][] map) { // 아래로
        int[][] newMap = new int[N][N];
        boolean[][] v = new boolean[N][N];

        for(int i=N-1; i>=0; i--) {
            for (int j=0; j<N; j++) {
                if(map[i][j]!=0) {
                    int ni = i;
                    newMap[ni][j] = map[i][j];
                    int num = map[i][j];

                    while (ni+1<=N-1) {
                        if (newMap[ni+1][j]==0) {
                            newMap[ni][j] = 0;
                            ni += 1;
                            newMap[ni][j] = num;
                        } else if (newMap[ni+1][j]==num && !v[ni+1][j]) {
                            newMap[ni][j] = 0;
                            ni += 1;
                            newMap[ni][j] = num*2;
                            v[ni][j] = true;
                            break;
                        } else break;
                    }
                }
            }
        }
        return newMap;
    }

    static int[][] left(int[][] map) { // 왼쪽으로
        int[][] newMap = new int[N][N];
        boolean[][] v = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if(map[j][i]!=0) {
                    int ni = i;
                    newMap[j][ni] = map[j][i];
                    int num = map[j][i];

                    while (ni-1>=0) {
                        if (newMap[j][ni-1]==0) {
                            newMap[j][ni] = 0;
                            ni -= 1;
                            newMap[j][ni] = num;
                        } else if (newMap[j][ni-1]==num && !v[j][ni-1]) {
                            newMap[j][ni] = 0;
                            ni -= 1;
                            newMap[j][ni] = num*2;
                            v[j][ni] = true;
                            break;
                        } else break;
                    }
                }
            }
        }
        return newMap;
    }

    static int[][] right(int[][] map) { // 오른쪽으로
        int[][] newMap = new int[N][N];
        boolean[][] v = new boolean[N][N];

        for(int i=N-1; i>=0; i--) {
            for (int j=0; j<N; j++) {
                if(map[j][i]!=0) {
                    int ni = i;
                    newMap[j][ni] = map[j][i];
                    int num = map[j][i];

                    while (ni+1<=N-1) {
                        if (newMap[j][ni+1]==0) {
                            newMap[j][ni] = 0;
                            ni += 1;
                            newMap[j][ni] = num;
                        } else if (newMap[j][ni+1]==num && !v[j][ni+1]) {
                            newMap[j][ni] = 0;
                            ni += 1;
                            newMap[j][ni] = num*2;
                            v[j][ni] = true;
                            break;
                        } else break;
                    }
                }
            }
        }
        return newMap;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
