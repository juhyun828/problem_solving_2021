import java.util.*;
// 210831

class Solution_LV3_블록이동하기 {
    static int answer, N;
    static int[] dr = new int[] {0, 0, -1, 1};
    static int[] dc = new int[] {-1, 1, 0, 0};
    static int[] vh = new int[] {-1, 1};
    static int[][] map;
    static ArrayList<Pos> visited;

    static class Pos {
        int r1, c1, r2, c2, dist;

        public Pos(int r1, int c1, int r2, int c2, int dist) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.dist = dist;
        }
    }

    public int solution(int[][] board) {
        answer = 0;
        N = board.length;
        map = new int[N+2][N+2];
        for(int i=0; i<N+2; i++) {
            map[i][0] = 1; map[i][N+1] = 1;
            map[0][i] = 1; map[N+1][i] = 1;
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i+1][j+1] = board[i][j];
            }
        }

        visited = new ArrayList<Pos>();

        bfs();

        return answer;
    }

    static void bfs() {
        // 시작점
        visited.add(new Pos(1, 1, 1, 2, 0));
        Queue<Pos> q = new ArrayDeque<Pos>();
        q.offer(new Pos(1, 1, 1, 2, 0));

        while (!q.isEmpty()) {
            Pos cur = q.poll();
            if((cur.r1==N&&cur.c1==N) || (cur.r2==N&&cur.c2==N)) {
                answer = cur.dist;
                return;
            }

            ArrayList<Pos> possible = findNextPos(cur);
            for(Pos next: possible) {
                // 다음 위치가 방문하지 않은 위치라면
                if(!checkVisited(next)) {
                    q.offer(next);
                    visited.add(next);
                }
            }
        }
    }

    static ArrayList<Pos> findNextPos(Pos cur) {
        ArrayList<Pos> list = new ArrayList<Pos>();
        
        // 상하좌우로 이동할 수 있는 곳
       for(int d=0; d<4; d++) {
            int nr1 = cur.r1 + dr[d];
            int nc1 = cur.c1 + dc[d];
            int nr2 = cur.r2 + dr[d];
            int nc2 = cur.c2 + dc[d];
            // 이동할 수 있는 곳
            if(map[nr1][nc1]==0 && map[nr2][nc2]==0) {
                list.add(new Pos(nr1, nc1, nr2, nc2, cur.dist+1));
            }
        }
       
        // 수직일 경우 회전할 수 있는 곳
        if(cur.r1 == cur.r2) {
            for(int d=0; d<2; d++) {
                // 위 아래의 칸들이 빈칸이어야 한다.
                int nr1 = cur.r1 + vh[d];
                int nr2 = cur.r2 + vh[d];
                if(map[nr1][cur.c1]==0 && map[nr2][cur.c2]==0) {
                    list.add(new Pos(nr1, cur.c1, cur.r1, cur.c1, cur.dist+1));
                    list.add(new Pos(nr2, cur.c2, cur.r2, cur.c2, cur.dist+1));
                }
            }
        }

        // 수평일 경우 회전할 수 있는 곳
        if(cur.c1 == cur.c2) {
            for(int d=0; d<2; d++) {
                // 좌 우의 칸들이 빈칸이어야 한다.
                int nc1 = cur.c1 + vh[d];
                int nc2 = cur.c2 + vh[d];
                if(map[cur.r1][nc1]==0 && map[cur.r2][nc2]==0) {
                    list.add(new Pos(cur.r1, nc1, cur.r1, cur.c1, cur.dist+1));
                    list.add(new Pos(cur.r2, nc2, cur.r2, cur.c2, cur.dist+1));
                }
            }
        }

       return list;
    }
    
    static boolean checkVisited(Pos next) {
        // 이미 방문한 적 있는지 체크
        for(Pos v: visited) {
            if(v.r1 == next.r1 && v.c1 == next.c1 && v.r2 == next.r2 && v.c2 == next.c2 ) {
                return true;
            }
        }
        return false;
    }
}