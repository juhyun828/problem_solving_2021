import java.io.*;
import java.util.*;
// 210217

public class Main_BJ_17135_캐슬디펜스_fail {
	static int N, M, D;
	static int[][] map;
	static ArrayList<Integer[]> target; 
	static ArrayList<Integer[]> archer;
	static int[] here;
	static int max = 0;
	static int targetTotal, targetTotal2;
	
	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static void combination(int cnt, int L, int start) {
		if (cnt==3) {
			// ystem.out.println(Arrays.toString(here));
			archer = new ArrayList<Integer[]>();
			for(int i=0; i<3; i++) {
				archer.add(new Integer[] {N, here[i]}); // n, r 위치
			}
			max = Math.max(max, kill()); // 최대값 갱신
			// 원상 복귀
			revive();
			return;
		}
		
		if(L==M) return;
		
		here[cnt] = start;
		combination(cnt+1, L+1, start+1);
		combination(cnt, L+1, start+1);
	}
	
	static void revive() {
		for(int j=0; j<target.size(); j++) {
			target.get(j)[2] = 1; // 다시 살림
			target.get(j)[0] = target.get(j)[3]; // 원래 위치로
			targetTotal = targetTotal2;
		}
	}
	
	static int kill() {
		int cnt = 0;
		
		while (targetTotal>0) {
			int[] kj = new int[3];
			for(int i=0; i<3; i++) {
				int ar = archer.get(i)[0];
				int ac = archer.get(i)[1];
				
				// 3명의 궁수에서 가장 가까운 적의 위치를 찾기
				int min = Integer.MAX_VALUE;
				kj[i] = -1; // 죽일 적의 위치
				int kc = M;
				for(int j=0; j<target.size(); j++) {
					if (target.get(j)[2]==0) continue; //죽었으면 pass 
					int tr = target.get(j)[0];
					int tc = target.get(j)[1];
					int dist = cal(ar, ac, tr, tc);
					if (dist<= D) {
						if (min > dist && tc < kc) {
							min = dist;
							kj[i] = j;
							tc = kc;// 더 왼쪽에 있는 적 공격
						}	
					}
				} // for

			} // for
			
			// 한꺼번에 죽이는 처리 해야 함
			for(int j=0; j<3; j++) {
				if (kj[j]!=-1 && target.get(kj[j])[2] == 1) {
					++cnt;
					target.get(kj[j])[2] = 0; --targetTotal; // 죽임 
//					System.out.println("cnt: " + cnt+ " | " + target.get(kj[j])[0] + " " + target.get(kj[j])[1]);
					
//					System.out.println(target.get(12)[0] + ", " + target.get(12)[1] + "| " + target.get(12)[2] );
				}
			}
			
			// 남아있는 적들은 아래로 한 칸 내려간다.
			down();
		} // while
		// System.out.println(cnt);
		return cnt;
	} // kill
	
	static void printTarget() {
		for(int j=0; j<target.size(); j++) {
			System.out.println(target.get(j)[0] + ", " + target.get(j)[1] 
					+ " | 1살 0죽 " + target.get(j)[2]);
		}
	}
	
	static void down() {
		for (int j=0; j<target.size(); j++) {
			if (target.get(j)[2]==0) continue;
			target.get(j)[0] += 1;
			if (target.get(j)[0]>=N) {
				target.get(j)[2]=0; // 성 까지 내려가면 죽는다.
				--targetTotal;
			}
		}
	}
	
	static int cal(int ar, int ac, int br, int bc) {
		return Math.abs(ar-br) + Math.abs(ac-bc);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_BJ_17135_캐슬디펜스.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = stoi(st.nextToken());
		M = stoi(st.nextToken());
		D = stoi(st.nextToken()); // 거리
		
		target = new ArrayList<Integer[]>();
		archer = new ArrayList<Integer[]>();
		
		map = new int[N][M];
		here = new int[3]; // 궁수 세명 배치
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				map[r][c] = stoi(st.nextToken());
				if (map[r][c]==1) {
					target.add(new Integer[] {r, c, 1, r}); // 1은 살아 있음
					++targetTotal;
				}
 			}
		}
		targetTotal2 = targetTotal;
		
		combination(0, 0, 0);
		System.out.println(max);
		br.close();
	}

}
