import java.util.*;

// 210719

class Solution_LV2_프랜즈4블록 {
    static int M, N, answer;
    static boolean change;
    static int cnt = 0;
    public int solution(int m, int n, String[] board) {
        answer = 0;
        M = m; // 세로 
        N = n; // 가로
        
        Queue<Character>[] q = new ArrayDeque[N];
        for(int c=0; c<N; c++) {
            q[c] = new ArrayDeque<Character>();
        }
		
        for(int r=M-1; r>=0; r--) {
            for(int c=0; c<N; c++) {
                q[c].offer(board[r].charAt(c));
            }
        }
        
        change = false;
        while(true) {
            q = play(q);
            if(!change) break;
        }
        
        // 삭제한 개수를 찾는다.
        countRemoved(q);
        
        return answer;
    }
    
    static void countRemoved(Queue<Character>[] q) {
        char[][] newMap = new char[M][N];
        for(int r=0; r<M; r++) {
            Arrays.fill(newMap[r], '0');
        }
        
        // 게임 후의 map
        for(int c=0; c<N; c++) {
            int r = M-1;
            while(!q[c].isEmpty()) {
                char cur = q[c].poll();
                newMap[r--][c] = cur;
            }
        }
        
        for(int r=0; r<M; r++) {
            for(int c=0; c<N; c++) {
                if(newMap[r][c]=='0') ++answer;
            }
        }
        
    }
    
    static Queue<Character>[] play(Queue<Character>[] q){
        change = false;
        // 입력된 큐로 map 생성
        char[][] map = new char[M][N];
        char[][] newMap = new char[M][N];
        for(int r=0; r<M; r++) {
            Arrays.fill(map[r], '0');
            Arrays.fill(newMap[r], '0');
        }
        
        for(int c=0; c<N; c++) {
            int r = M-1;
            while(!q[c].isEmpty()) {
                char cur = q[c].poll();
                map[r][c] = cur;
                newMap[r--][c] = cur;
            }
        }

        // (0, 0) 부터 (M-2, N-2)까지 삭제 가능한 곳을 찾는다.
        for(int r=0; r<=M-2; r++) {
            for(int c=0; c<=N-2; c++) {
                if(map[r][c]!='0' && checkSame(r, c, map)) {
                    newMap = remove(r, c, newMap);
                    change = true;
                }
            }
        }
             
        Queue<Character>[] nq = new ArrayDeque[N];
        for(int c=0; c<N; c++) {
            nq[c] = new ArrayDeque<Character>();
        }
        
        // newMap으로 다시 큐를 만든다.
        for(int r=M-1; r>=0; r--) {
            for(int c=0; c<N; c++) {
                if(newMap[r][c]=='0') continue;
                nq[c].offer(newMap[r][c]);
            }
        }
        
        return nq;
    }
    
    static boolean checkSame(int r, int c, char[][] map){
        if((map[r][c] == map[r][c+1]) 
           && (map[r][c+1]== map[r+1][c])
           && (map[r+1][c]== map[r+1][c+1])) return true;
        
        return false;
    }
    
    static char[][] remove(int r, int c, char[][] newMap) {
        newMap[r][c] = '0';
        newMap[r][c+1] = '0';
        newMap[r+1][c] = '0';
        newMap[r+1][c+1] = '0';
        
        return newMap;
    }
}