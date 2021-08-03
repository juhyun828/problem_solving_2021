import java.util.*;

class Solution_LV3_자물쇠와열쇠_fail {
    static int K, L, S;
    static int[][] map;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        K = key.length;
        L = lock.length;
        S = L + 2*(K-1);
        map = new int[S][S];
        for(int i=0; i<S; i++) {
            Arrays.fill(map[i], -1);
        }
        
        // map의 중간에 자물쇠 고정
        for(int i=0; i<L; i++) {
            for(int j=0; j<L; j++) {
                map[K-1+i][K-1+j] = lock[i][j];
            }
        }
        // key 복사
        int[][] rotated = new int[K][K];
        for(int i=0; i<K; i++) {
            for(int j=0; j<K; j++) {
                rotated[i][j] = key[i][j];
            }
        }
        
        // 4번 회전
        for(int r=0; r<4; r++) {
            rotated = rotate(rotated);
            for(int i=0; i<=S-K; i++){
                for(int j=0; j<=S-K; j++) {
                    boolean res = check(i, j, rotated);
                    if(res) return true;
                }
            }
        }
        
        return answer;
    }
    
    static boolean check(int startR, int startC, int[][] rotated) {
        int[][] newMap = new int[S][S];
        for(int i=0; i<S; i++) {
            for(int j=0; j<S; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        
        // key 배치
        for(int i=0; i<K; i++) {
            for(int j=0; j<K; j++) {
                newMap[startR+i][startC+j] = rotated[i][j];
            }
        }
        
        // 확인
        for(int i=0; i<L; i++) {
            for(int j=0; j<L; j++) {
                if(map[K-1+i][K-1+j]==0 && newMap[i][j]==0) return false;
                if(map[K-1+i][K-1+j]==1 && newMap[i][j]==1) return false;
            }
        }
        
        return true;
    }
    
    static int[][] rotate(int[][] key) {
        int[][] rotated = new int[K][K];
        for(int i=0; i<K; i++) {
            for(int j=0; j<K; j++) {
                rotated[j][K-1-i] = key[i][j];
            }
        }
        return rotated;
    }
}