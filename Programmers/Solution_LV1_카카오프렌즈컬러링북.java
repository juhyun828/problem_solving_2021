import java.util.*;
// 210713

class Solution_LV1_카카오프렌즈컬러링북 {
    static int M, N;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static boolean[][] v;
    
    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int[] solution(int m, int n, int[][] picture) {
        int total = 0;
        int max = 0;
        int[] answer = new int[2];
        
        M = m; N = n;
        v = new boolean[m][n];
        
        // bfs 탐색
        for(int r=0; r<m; r++) {
            for(int c=0; c<n; c++) {
                if(picture[r][c]!=0 && !v[r][c]) {
                    int cnt = bfs(r, c, picture[r][c], picture);
                    ++total;
                    max = Math.max(max, cnt);
                }
            }
        }
        
        answer[0] = total;
        answer[1] = max;

        return answer;
    }
    
    static int bfs(int r, int c, int type, int[][] picture) {
        Queue<Pos> q= new ArrayDeque<Pos>();
        // 시작점
        v[r][c] = true;
        q.offer(new Pos(r, c));
        int cnt = 1;
        
        while(!q.isEmpty()) {
            Pos cur = q.poll();
            
            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0||nr>=M||nc<0||nc>=N||v[nr][nc]) continue;
                if(picture[nr][nc]==type) {
                    ++cnt;
                    v[nr][nc] = true;
                    q.offer(new Pos(nr, nc));
                }
            }
        }
        
        return cnt;
    }
}