import java.io.*;
import java.util.*;
// 210503

public class Main_JO_2078_13일의금요일 {
								//            1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12
	static int[] monthDaysCnt = new int[] {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = stoi(br.readLine());
		int[] cnt = new int[7];
		int firstDay = 0; // 각 달의 1일 요일
		
		for(int year=1900; year<1900+N; year++) {
			if(isLeapYear(year)) {
				monthDaysCnt[2] = 29;
			} else {
				monthDaysCnt[2] = 28;
			}
			
			for(int month=1; month<=12; month++) {
				// 13일의 요일 구하기
				int day = (13-1)%7 + firstDay;
				if(day>=7) day-=7;
				++cnt[day];
				
				// 다음 달의 1일 요일 구하기
				// 이번달 1일 기준으로 28 or 29 or 30 or 31 일 후의 날임
				firstDay = monthDaysCnt[month]%7 + firstDay;
				if(firstDay>=7) firstDay-=7;
			}
		}
		
		for(int i=0; i<7; i++) {
			sb.append(cnt[i]+" ");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	static boolean isLeapYear(int year) {
		if(year%4==0 && year%100!=0) return true;
		if(year%400==0) return true;
		return false;
	}
}
