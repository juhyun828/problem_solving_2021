import java.io.*;
import java.util.*;

// 210210

public class Main_BJ_16935_배열돌리기3 {
	
	static int N, M, R, O;
	static int[][] arr;
	static int[][] newArr;
	
	static void printArr() {
		for(int r=0; r<arr.length; r++) {
			for (int c=0; c<arr[r].length; c++) {
				System.out.print(arr[r][c] + " ");
			}
			System.out.println();
		}
	} //
	
	static void copyArr(int NN, int NM) {
		// arr에 newArr copy
		arr = new int[NN][NM];
		for(int r=0; r<NN; r++) {
			for (int c=0; c<NM; c++) {
				arr[r][c] = newArr[r][c];
			}
		}
	} // 
	
	static void rotate1() {
		int NN = arr.length;
		int NM = arr[0].length;
		
		for(int r=0; r<NN/2; r++) {
			for (int c=0; c<NM; c++) {
				int tmp = arr[r][c];
				arr[r][c] = arr[NN-1-r][c];
				arr[NN-1-r][c] = tmp;
			}
		}
	} //
	
	static void rotate2() {
		int NN = arr.length;
		int NM = arr[0].length;
		
		for(int r=0; r<NN; r++) {
			for (int c=0; c<NM/2; c++) {
				int tmp = arr[r][c];
				arr[r][c] = arr[r][NM-1-c];
				arr[r][NM-1-c] = tmp;
			}
		}
	} //
	
	static void rotate3() {
		int NN = arr.length;
		int NM = arr[0].length;
		
		newArr = new int[NM][NN];
		
		for(int r=0; r<NN; r++) {
			for (int c=0; c<NM; c++) {
				newArr[c][NN-1-r] = arr[r][c];
			}
		}
		
		copyArr(NM, NN);
	} //
	
	static void rotate4() {
		int NN = arr.length;
		int NM = arr[0].length;
		
		newArr = new int[NM][NN];
		
		for(int r=0; r<NN; r++) {
			for (int c=0; c<NM ; c++) {
				newArr[NM-1-c][r] = arr[r][c];
			}
		}
		
		copyArr(NM, NN);

	} //
	
	static void rotate5() {
		int NN = arr.length;
		int NM = arr[0].length;
		
		newArr = new int[NN][NM];
	
		for(int r = 0; r<NN/2; r++) {
			// 1번구역
			for(int c=0; c<NM/2; c++) {
				newArr[r][c+NM/2] = arr[r][c];
			}
			// 2번 구역
			for(int c=NM/2; c<NM; c++) {
				newArr[r+NN/2][c] = arr[r][c];
			}
		} 
				
		for(int r = NN/2; r<NN; r++) {
			// 4번구역
			for(int c=0; c<NM/2; c++) {
				newArr[r-NN/2][c] = arr[r][c];
			}
			// 3번 구역
			for(int c=NM/2; c<NM; c++) {
				newArr[r][c-NM/2] = arr[r][c];
			}
		} 
		
		copyArr(NN, NM);
		
	} //
	
	static void rotate6() {
		int NN = arr.length;
		int NM = arr[0].length;
		
		newArr = new int[NN][NM];
	
		for(int r = 0; r<NN/2; r++) {
			// 1번구역 -> 4
			for(int c=0; c<NM/2; c++) {
				newArr[r+NN/2][c] = arr[r][c];
			}
			// 2번 구역 -> 1
			for(int c=NM/2; c<NM; c++) {
				newArr[r][c-NM/2] = arr[r][c];
			}
		} 
				
		for(int r = NN/2; r<NN; r++) {
			// 4번구역 -> 3
			for(int c=0; c<NM/2; c++) {
				newArr[r][c+NM/2] = arr[r][c];
			}
			// 3번 구역 -> 2
			for(int c=NM/2; c<NM; c++) {
				newArr[r-NN/2][c] = arr[r][c];
			}
		} 
		
		copyArr(NN, NM);
		
	} //

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken()); // 연산 수
		
		arr = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<M; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int ri=0; ri<R; ri++) {
			O = Integer.parseInt(st.nextToken()); // 연산 종류
			switch (O) {
			case 1: rotate1(); break;
			case 2: rotate2(); break;
			case 3: rotate3(); break;
			case 4: rotate4(); break;
			case 5: rotate5(); break;
			case 6: rotate6(); break;
			}
		}
		
		printArr();

		br.close();
	}

}
