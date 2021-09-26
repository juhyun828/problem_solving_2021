import java.io.*;
import java.util.*;
// 210926

public class Main_BJ_12100_2048Easy_fail {
    static int N, max;

    static class CurState {
        int[][] map;
        boolean[][] v;

        public CurState(int[][] map, boolean[][] v) {
            this.map = map;
            this.v = v;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
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

        dfs(0, new CurState(map, v));
        System.out.println(max);

        br.close();
    }

    static void dfs(int L, CurState curState) {
        if (L >= 5) {
            // max 갱신
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    max = Math.max(max, curState.map[i][j]);
                }
            }
            return;
        }

        dfs(L+1, moveUp(curState));
        dfs(L+1, moveDown(curState));
        dfs(L+1, moveRight(curState));
        dfs(L+1, moveLeft(curState));
    }

    static CurState moveUp(CurState curState) {
        for(int r=0; r<N; r++) {
            for (int c=0; c<N; c++) {
                if(curState.map[r][c]!=0) {
                    int nr = r;
                    boolean combine = false;
                    while (true){
                        if (nr-1>=0) nr-=1;
                        else break;

                        // 새 칸이 빈칸인지, 합쳐질 칸인지 살핀다.
                        if(curState.map[nr][c]==0) continue;
                        else if(curState.map[nr][c]==curState.map[r][c]) {
                            if (!curState.v[nr][c]) {
                                curState.v[nr][c] = true;
                                curState.map[nr][c] = curState.map[r][c]*2;
                                curState.map[r][c] = 0;
                                combine = true;
                                break;

                            } else {
                                nr += 1;
                                break;
                            }

                        } else if(curState.map[nr][c]!=curState.map[r][c]) {
                            nr += 1;
                            break;
                        }
                    }
                    if(!combine) {
                        int before = curState.map[r][c];
                        curState.map[r][c] = 0;
                        curState.map[nr][c] = before;
                    }
                }
            }
        }

        return curState;
    }

    static CurState moveDown(CurState curState) {

        for(int r=N-1; r>=0; r--) {
            for (int c=0; c<N; c++) {
                if(curState.map[r][c]!=0) {
                    int nr = r;
                    boolean combine = false;
                    while (true){
                        if (nr+1<N) nr+=1;
                        else break;

                        // 새 칸이 빈칸인지, 합쳐질 칸인지 살핀다.
                        if(curState.map[nr][c]==0) continue;
                        else if(curState.map[nr][c]==curState.map[r][c]) {
                            if (!curState.v[nr][c]) {
                                curState.v[nr][c] = true;
                                curState.map[nr][c] = curState.map[r][c]*2;
                                curState.map[r][c] = 0;
                                combine = true;
                                break;

                            } else {
                                nr -= 1;
                                break;
                            }

                        } else if(curState.map[nr][c]!=curState.map[r][c]) {
                            nr -= 1;
                            break;
                        }
                    }
                    if(!combine) {
                        int before = curState.map[r][c];
                        curState.map[r][c] = 0;
                        curState.map[nr][c] = before;
                    }
                }
            }
        }

        return curState;
    }

    static CurState moveLeft(CurState curState) {
        for(int c=0; c<N; c++) {
            for (int r=0; r<N; r++) {
                if(curState.map[r][c]!=0) {
                    int nc = c;
                    boolean combine = false;
                    while (true){
                        if (nc-1>=0) nc-=1;
                        else break;

                        // 새 칸이 빈칸인지, 합쳐질 칸인지 살핀다.
                        if(curState.map[r][nc]==0) continue;
                        else if(curState.map[r][nc]==curState.map[r][c]) {
                            if (!curState.v[r][nc]) {
                                curState.v[r][nc] = true;
                                curState.map[r][nc] = curState.map[r][c]*2;
                                curState.map[r][c] = 0;
                                combine = true;
                                break;

                            } else {
                                nc += 1;
                                break;
                            }

                        } else if(curState.map[r][nc]!=curState.map[r][c]) {
                            nc += 1;
                            break;
                        }
                    }
                    if(!combine) {
                        int before = curState.map[r][c];
                        curState.map[r][c] = 0;
                        curState.map[r][nc] = before;
                    }
                }
            }
        }

        return curState;
    }

    static CurState moveRight(CurState curState) {

        for(int c=N-1; c>=0; c--) {
            for (int r=0; r<N; r++) {
                if(curState.map[r][c]!=0) {
                    int nc = c;
                    boolean combine = false;
                    while (true){
                        if (nc+1<=N-1) nc+=1;
                        else break;

                        // 새 칸이 빈칸인지, 합쳐질 칸인지 살핀다.
                        if(curState.map[r][nc]==0) continue;
                        else if(curState.map[r][nc]==curState.map[r][c]) {
                            if (!curState.v[r][nc]) {
                                curState.v[r][nc] = true;
                                curState.map[r][nc] = curState.map[r][c]*2;
                                curState.map[r][c] = 0;
                                combine = true;
                                break;

                            } else {
                                nc -= 1;
                                break;
                            }

                        } else if(curState.map[r][nc]!=curState.map[r][c]) {
                            nc -= 1;
                            break;
                        }
                    }
                    if(!combine) {
                        int before = curState.map[r][c];
                        curState.map[r][c] = 0;
                        curState.map[r][nc] = before;
                    }
                }
            }
        }

        return curState;
    }

    static void print(int[][] map) {
        System.out.println("===============");
        for(int i=0; i<N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
