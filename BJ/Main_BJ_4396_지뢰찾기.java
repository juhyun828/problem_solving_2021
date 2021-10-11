import java.io.*;
import java.util.*;
// 211011

public class Main_BJ_4396_지뢰찾기 {
	static int N;
	static char[][] map, cur, res;
	static int[] dr = new int[] {-1,1,0,0,-1,-1,1,1};
	static int[] dc = new int[] {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = stoi(br.readLine());
        map = new char[N][N];
        cur = new char[N][N];
        res = new char[N][N];
        
        for(int i=0; i<N; i++) {
        	Arrays.fill(res[i], '.');
        	map[i] = br.readLine().toCharArray();
        }
                
        for(int i=0; i<N; i++) {
        	cur[i] = br.readLine().toCharArray();
        }
        
        boolean caught = false;
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		if(cur[i][j]=='x') {
        			if(map[i][j]=='*') {
        				caught = true;
        			} else {
        				res[i][j] = (char) (countMin(i, j) + '0');
        			}
        		}
        	}
        }
        
        if(caught) {
        	// 모든 지뢰칸 표시
        	for(int i=0; i<N; i++) {
        		for(int j=0; j<N; j++) {
        			if(map[i][j]=='*') {
        				res[i][j] = '*';
        			}
        		}
        	}
        }
        
        for(int i=0; i<N; i++) {
        	for(int j=0; j<N; j++) {
        		System.out.print(res[i][j]);
        	}
        	if(i<N-1) System.out.print('\n');
        }
		
		br.close();
    }
	
	
	static int countMin(int r, int c) {
		int cnt = 0;
		
		for(int d=0; d<8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(valid(nr, nc) && map[nr][nc]=='*') {
				++cnt;
			}
		}
		
		return cnt;
	}
	
	static boolean valid(int r, int c) {
		if(r<0 || r>=N || c<0 || c>=N) return false;
		else return true;
	}
	
    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}