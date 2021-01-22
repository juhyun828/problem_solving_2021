import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CU1084 {
	public static void main (String[] args) throws IOException {
		// 210122
		// 1084 : [기초-종합] 빛 섞어 색 만들기

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		
		String[] strArr = br.readLine().split(" ");
		int r = Integer.parseInt(strArr[0]);
		int g  = Integer.parseInt(strArr[1]);
		int b = Integer.parseInt(strArr[2]);
		
		int cnt = 0;
		for (int i=0; i<r; i++) {
			for (int j=0; j<g; j++) {
				for (int z=0; z<b; z++) {
					cnt++;
					// System.out.print() => 시간 초과
					// buffer 공간에 일시 저장 후 
					bw.write(i + " " + j + " " + z + "\n");
				}
			}
		}
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close(); // buffer 자원 해제 전에 buffer에 있는 내용을 write
		br.close();
		
	} // main
} // class end