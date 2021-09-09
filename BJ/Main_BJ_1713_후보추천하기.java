import java.io.*;
import java.util.*;
// 210909

public class Main_BJ_1713_후보추천하기 {
    static class Data implements Comparable<Data>{
        int num, cnt, time;
        public Data(int num, int cnt, int time) {
            this.num = num;
            this.cnt = cnt;
            this.time = time;
        }

        @Override
        public int compareTo(Data o) {
            if(this.cnt==o.cnt) {
                return Integer.compare(this.time, o.time);
            }
            return Integer.compare(this.cnt, o.cnt);
        }
    }
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine()); // 사진틀의 개수
        int M = stoi(br.readLine()); // 학생의 총 추천 횟수
        int[] arr = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            arr[i] = stoi(st.nextToken());
        }
        ArrayList<Data> frame = new ArrayList<Data>();

        int t=0;
        int i=0;
        while (i<M) {
            t++;
            boolean flag = false;
            for(int j=0; j<frame.size(); j++) {
                if(frame.get(j).num==arr[i]) {
                    frame.get(j).cnt+=1;
                    flag = true;
                    i++;
                    break;
                }
            }
            
            if(!flag) { // 앨범에 존재하지 않는 경우
                if(frame.size()<N) {
                    frame.add(new Data(arr[i++], 1, t));
                } else {
                    Collections.sort(frame);
                    frame.remove(0);
                    frame.add(new Data(arr[i++], 1, t));
                }
            }
        }

        Collections.sort(frame, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });
        for(int j=0; j<frame.size(); j++) {
            System.out.print(frame.get(j).num + " ");
        }
        br.close();
    }
    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
