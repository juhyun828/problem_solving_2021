import java.io.*;
import java.util.*;

// 210214

public class Main_BJ_17413_단어뒤집기2 {
	static char[] arr;
	
	static void swap(int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		arr = br.readLine().toCharArray();
		
		for(int i=0; i<arr.length; i++) {
			
			if (arr[i] == ' ') continue; 
			
			if (arr[i] =='<') {
				int j = i+1;
				for(; j<arr.length;j++) {
					if (arr[j]=='>') break;
				}
				i = j;
			} else {
				int j = i+1;
				for(; j<arr.length;j++) {
					if (arr[j]==' ' || arr[j]=='<') break;
				}
				j-=1;
				for(int k=0; k<=(j-i)/2; k++) {
					swap(i+k, j-k);
				}
				i = j;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length;i++) sb.append(arr[i]);
		System.out.println(sb.toString());
		br.close();
	}
}
