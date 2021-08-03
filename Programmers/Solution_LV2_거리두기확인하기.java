import java.util.*;
// 210715

class Solution_LV2_거리두기확인하기 {
    static int N=5;
    static int[] answer;
    static int[] dr = new int[] {-2, -1, -1, -1, 0,  0,  0, 0, 1,  1, 1, 2  }; //12
    static int[] dc = new int[] { 0, -1,  0,  1, -2, -1, 1, 2, -1, 0, 1 , 0 };
    public int[] solution(String[][] places) {
        answer = new int[N];
        Arrays.fill(answer, 1);
        
        for(int idx=0; idx<N; idx++) {
            char[][] map = makeMap(places[idx]);
            isDistancing(idx, map);
        }
            
        return answer;
    }
    
    static void isDistancing(int idx, char[][] map) {
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(map[r][c]=='P') {
                    for(int d=0; d<12; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr<0||nr>=N||nc<0||nc>=N) continue;
                        if(map[nr][nc]=='P') {
                            boolean res = checkWall(r, c, nr, nc, map);
                            if(!res) {
                                answer[idx] = 0;
                                return;
                            }
                        }   
                    }
                }
            }
        }
    }
    
    static boolean checkWall(int r1, int c1, int r2, int c2, char[][] map) {
        if(r1==r2 && map[r1][(c1+c2)/2]=='X') return true;
        else if(c1==c2 && map[(r1+r2)/2][c1]=='X')  return true;
        else if(map[r1][c2]=='X'&&map[r2][c1]=='X') return true;  
        return false;
    }
    
    static char[][] makeMap(String[] p) {
        char[][] map = new char[N][N];
        for(int r=0; r<N; r++) {
            map[r] = p[r].toCharArray();
        }
        return map;
    }
}