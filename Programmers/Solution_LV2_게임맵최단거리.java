import java.util.*;
// 210629

class Solution_LV2_게임맵최단거리 {
    static int n, m, min;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public int solution(int[][] maps) {
        n = maps.length;
        m = maps[0].length; 
        // 상대방 위치 (n-1, m-1);
        // 내 위치 (0, 0)
        min = Integer.MAX_VALUE;
        bfs(maps);

        if(min == Integer.MAX_VALUE) min = -1;
        return min;
    }

    static void bfs(int[][] maps) {
        // 시작점
        Queue<Pos> q = new ArrayDeque<Pos>();
        q.offer(new Pos(0, 0));
        // 거리
        int[][] dist = new int[n][m];
        dist[0][0] = 1;

        while(!q.isEmpty()) {
            Pos cur = q.poll();
            if(cur.r == (n-1) && cur.c == (m-1)) {
                // min = Math.min(min, dist[cur.r][cur.c]);
                min = dist[cur.r][cur.c];
                // bfs -> 가중치x, 최단거리 탐색 가능
                return;
            }

            for(int d=0; d<4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr<0 || nr>=n || nc<0 || nc>=m || maps[nr][nc]==0) continue;
                // 아직 방문하지 않은 곳이면 방문
                if(dist[nr][nc]==0) {
                        dist[nr][nc] = dist[cur.r][cur.c] + 1;
                        q.offer(new Pos(nr, nc));
                } 
            }
        }
    }
}