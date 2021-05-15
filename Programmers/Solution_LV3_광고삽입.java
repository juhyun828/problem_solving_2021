import java.util.*;
// 210515

class Solution_LV3_광고삽입 {	
	// 문자열 시간 -> 초
	static int strToSec(String time) {
		String[] arr = time.split(":");
		int sec = stoi(arr[0])*60*60 + stoi(arr[1])*60 + stoi(arr[2]);
		return sec;
	}
	
	// 초 -> 문자열 시간
	static String secToStr(int sec) {
		String str="";
		
		int m = sec/60; int s = sec%60;
		int h = m/60; m = m%60;
		
		if(h<10) str += ("0" + h + ":");
		else str+= (h + ":");
		
		if(m<10) str += ("0" + m + ":");
		else str+= (m + ":");
		
		if(s<10) str += ("0" + s);
		else str+= s;
		
		return str;
	}
	
    public String solution(String play_time, String adv_time, String[] logs) {
        int[] watchers = new int[100*60*60];
        
        // 1. 누적 시청자 수 
        for(String log: logs) {
        	String[] t = log.split("-");
        	int startTime = strToSec(t[0]);
        	int endTime = strToSec(t[1]);
        	for(int s=startTime; s<endTime; s++) {
        		++watchers[s];
        	}
        }
        
        // 전체 동영상 재생 시간 (초)
        int totalLen = strToSec(play_time);
        // 광고 동영생 재생 시간 (초)
        int adLen = strToSec(adv_time);
        
        Queue<Integer> q = new ArrayDeque<Integer>();
        long sum = 0; // 현재 구간 합 (누적 시청자 수)
        long maxSum = 0; // 최대 구간 합 (누적 시청자 수)
		
        // 2. 0초~adLen초 까지의 구간합
        for(int i=0; i<adLen; i++) {
        	sum += watchers[i];
        	q.offer(watchers[i]);
        }
        maxSum = sum;
        int idx = 0;
        
        for(int i=adLen; i<totalLen; i++) {
        	// i초 구간에서의 시청자 수
        	sum += watchers[i];
        	q.offer(watchers[i]);
        	
        	// 큐의 맨 앞 시청자 수 - 구간 제외
        	sum -= q.poll();
        	
        	if(sum > maxSum) {
        		maxSum = sum;
        		idx = i-adLen+1;
        	}
        }

        return secToStr(idx);
    }
    
    static int stoi(String str) {
    	return Integer.parseInt(str);
    }
}