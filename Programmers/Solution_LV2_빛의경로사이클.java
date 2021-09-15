import java.util.*;
// 210915

class Solution_LV2_빛의경로사이클 {
    static char[][] map;
    static boolean[][][] v;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static int R, C; // 가로, 세로 길이
    
    public int[] solution(String[] grid) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<Integer>(); 
        R = grid.length;
        C = grid[0].length();
        map = new char[R][C];
        v = new boolean[R][C][4];

        for(int i=0; i<R; i++) {
            char[] tmp = grid[i].toCharArray();
            for(int j=0; j<C; j++) {
                map[i][j] = tmp[j];
            }
        }
        
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                for(int di=0; di<4; di++) {
                    if(!v[i][j][di]) {
                        int r=i, c=j, d=di;
                        int cycleLen = 0;
                        while(!v[r][c][d]) { // 사이클을 따라 돌며 방문 안한 곳들을 거쳐 시작 좌표를 방문할 때 까지
                            // 시작 좌표로 돌아오지 못해도 방문 한 곳을 방문하면 사이클이라고 보는건가 아니면 그럴 일이 없나..
                            
                            // 1. 현재 위치와 방향 방문
                            ++cycleLen;
                            v[r][c][d] = true;
                            
                            // 2. 다음 빙문 위치는 현재 방문 격자 종류가 결정
                            char type = map[r][c];
                            if(type=='L') {
                                d = rotateLeft(d);
                                
                            } else if(type=='R') {
                                d = rotateRight(d);
                            }
                            
                            // 3. 다음 방문 위치 결정
                            r += dr[d]; r = checkRangeRow(r);
                            c += dc[d]; c = checkRangeCol(c);
                        }
                        
						list.add(cycleLen); // 순환 길이가 0에서 끝나버리는 경우는 없는건지, 길이가 0 이상일 때는 정답 처리를 안해줘도 맞는다 뭐지??
                        //if(cycleLen>0)
                        //    list.add(cycleLen);
                    }
                }
            }
        }
        
        answer = new int[list.size()];
        Collections.sort(list);
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    static int rotateLeft(int d) {
        if(d==0) return 2;
        else if(d==1) return 3;
        else if(d==2) return 1;
        else return 0;
    }
    
    static int rotateRight(int d) {
        if(d==0) return 3;
        else if(d==1) return 2;
        else if(d==2) return 0;
        else return 1;
    }
    
    static int checkRangeRow(int x) {
        if(x<0) return R-1;
        else if(x>=R) return 0;
        else return x;
    }
    
    static int checkRangeCol(int y) {
        if(y<0) return C-1;
        else if(y>=C) return 0;
        else return y;
    }
    
}