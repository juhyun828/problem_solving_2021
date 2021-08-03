import java.io.*;
import java.util.*;

// 210216

public class Main_BJ_1074_Z {
	
	static int[] dr = {0, 0, 1, 1};
	static int[] dc = {0, 1, 0, 1};
	static int num = 0;
	static int[][] arr;
	static int N, R, C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		divide(N, R, C);
		System.out.println(num);
		br.close();
	}
	
	static void divide(int N, int sr, int sc) {
		
		if (N==0) {
			return;
		}
		
		int len = (int) Math.pow(2, N);
		int size = len * len;
		int halfLen = len/2;
		
		if (sr<halfLen && sc<halfLen)	{
			// System.out.println("1사분면");
			divide(N-1, sr, sc);
		}
		else if (sr<halfLen && sc>=halfLen) {
			// System.out.println("2사분면");
			num += (size)/4;
			divide(N-1, sr, sc - halfLen);
		}
		else if (sr>=halfLen && sc<halfLen) {
			// System.out.println("3사분면");
			num += (size/4)*2;
			divide(N-1, sr - halfLen, sc);
		}
		else if (sr>=halfLen&& sc>=halfLen) {
			// System.out.println("4사분면");
			num += (size/4)*3;
			divide(N-1, sr - halfLen, sc - halfLen);
		}
		
	}
	
}
