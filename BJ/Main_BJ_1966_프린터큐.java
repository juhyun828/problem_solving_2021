import java.io.*;
import java.util.*;
// 210725

public class Main_BJ_1966_프린터큐 {
    static class Document {
        int idx, prio;

        public Document(int idx, int prio) {
            this.idx = idx;
            this.prio = prio;
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = stoi(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = stoi(st.nextToken());
            int target = stoi(st.nextToken());
            int cnt = 0;
            Queue<Document> q = new ArrayDeque<Document>();

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                int prio = stoi(st.nextToken());
                q.offer(new Document(i, prio));
            }

            while(!q.isEmpty()) {
                Document cur = q.poll();

                boolean flag = false;
                Iterator<Document> it = q.iterator();
                while(it.hasNext()) {
                    Document next = it.next();
                    if(next.prio > cur.prio) {
                        flag = true;
                        q.offer(cur);
                        break;
                    }
                }

                if(!flag) {
                    ++cnt;
                    if(cur.idx == target) {
                        System.out.println(cnt);
                        break;
                    }
                }
            }
        }

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}