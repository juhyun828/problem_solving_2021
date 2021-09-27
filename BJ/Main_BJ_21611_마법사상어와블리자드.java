import java.io.*;
import java.util.*;
// 210927

public class Main_BJ_21611_마법사상어와블리자드 {
    static int N, M;
    static int[] dr = new int[] {-1, 1, 0, 0};
    static int[] dc = new int[] {0, 0, -1, 1};
    static int one, two, three;
    static class Group {
        int type;
        int cnt;

        public Group(int type, int cnt) {
            this.type = type;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = stoi(st.nextToken());
        M = stoi(st.nextToken());

        one = 0;
        two = 0;
        three = 0;

        int[][] map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++) {
                map[i][j] = stoi(st.nextToken());
            }
        }

        // 상어
        map[(N+1)/2][(N+1)/2] = -1;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int di = stoi(st.nextToken());
            int si = stoi(st.nextToken());
            
            // 1. di 방향으로 si 거리 만큼 파괴
            map = destroy(map, di, si);

            // 2. 연속하는 구슬이 없을 때 까지 구슬 폭발
            Queue<Integer> q = mapToQue(map);
            while (true) {
                int beforeSize = q.size();
                q = bomb(q);
                if (beforeSize == q.size()) break;
            }

            // 3. 그룹별로 구슬 변환
            map = change(q);
        }

        int answer = 1 * one + 2 * two + 3 * three;
        System.out.println(answer);

        br.close();
    }

    static int[][] destroy(int[][] map, int di, int si) { // 1. 파괴
        int[][] newMap = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                newMap[i][j] = map[i][j];
            }
        }

        int r = (N+1)/2;
        int c = (N+1)/2;

        for (int i=1; i<=si; i++) {
            int nr = r + dr[di-1]*i;
            int nc = c + dc[di-1]*i;
            if(newMap[nr][nc]!=0) newMap[nr][nc] = 0; // 파괴
        }

        return newMap;
    }

    static Queue<Integer> bomb(Queue<Integer> iq) {
        Iterator<Integer> it = iq.iterator();
        Stack<Integer> stack = new Stack<Integer>();

        int before = -1; int cnt = 0;

        while(it.hasNext()) {
            int cur = it.next();

            if (before == cur) {
                stack.add(cur);
                cnt += 1;
            } else {
                if (cnt >= 4) {
                    if (before==1) one += cnt;
                    else if (before==2) two += cnt;
                    else if (before==3) three += cnt;

                    for(int i=0; i<cnt; i++) {
                        stack.pop();
                    }
                }

                stack.add(cur);
                cnt = 1;
                before = cur;
            }
        }

        if (cnt >= 4) {
            if (before==1) one += cnt;
            else if (before == 2) two += cnt;
            else if (before == 3) three += cnt;

            for(int i=0; i<cnt; i++) {
                stack.pop();
            }
        }

        // stack to Queue
        Queue<Integer> q = new ArrayDeque<Integer>();
        it = stack.iterator();
        while (it.hasNext()) {
            q.offer(it.next());
        }

        return q;
    }

    static int[][] change(Queue<Integer> iq) { // 3. 그룹 별 구슬 변화
        List<Group> list = new ArrayList<Group>();

        Iterator<Integer> it = iq.iterator();

        while (it.hasNext()) {
            int cur = it.next();
            if (list.size()==0) list.add(new Group(cur, 1));
            else {
                if(list.get(list.size()-1).type == cur) {
                    list.get(list.size()-1).cnt += 1;
                } else {
                    list.add(new Group(cur, 1));
                }
            }
        }

        // list to que
        Queue<Integer> q = new ArrayDeque<Integer>();
        for(Group group: list) {
            int type = group.type;
            int cnt = group.cnt;
            q.offer(cnt);
            q.offer(type);
        }

        int[][] map = queToMap(q);

        return map;
    }

    static Queue<Integer> mapToQue(int[][] map){
        Queue<Integer> q = new ArrayDeque<Integer>();
        int i=1;
        int r = (N+1)/2;
        int c = (N+1)/2;

        while (i<=N-2) {
            c -= 1;
            if (map[r][c]!=0) q.offer(map[r][c]);

            // 아래로 i칸
            for(int j=0; j<i; j++) {
                r += 1;
                if (map[r][c]!=0) q.offer(map[r][c]);
            }

            // 오른쪽으로 i+1칸
            for(int j=0; j<i+1; j++) {
                c += 1;
                if (map[r][c]!=0) q.offer(map[r][c]);
            }

            // 위로 i+1칸
            for(int j=0; j<i+1; j++) {
                r -= 1;
                if (map[r][c]!=0) q.offer(map[r][c]);
            }

            // 왼쪽으로 i+1칸
            for(int j=0; j<i+1; j++) {
                c -= 1;
                if (map[r][c]!=0) q.offer(map[r][c]);
            }

            i += 2;
        }

        return q;
    }

    static int[][] queToMap(Queue<Integer> q) {
        int[][] map = new int[N+1][N+1];
        int i=1;
        int r = (N+1)/2;
        int c = (N+1)/2;
        map[r][c] = -1;
        Iterator<Integer> it = q.iterator();

        while (i<=N-2) {
            c -= 1;

            if (!it.hasNext()) return map;
            else map[r][c] = it.next();

            // 아래로 i칸
            for(int j=0; j<i; j++) {
                r += 1;
                if (!it.hasNext()) return map;
                else map[r][c] = it.next();
            }

            // 오른쪽으로 i+1칸
            for(int j=0; j<i+1; j++) {
                c += 1;
                if (!it.hasNext()) return map;
                else map[r][c] = it.next();
            }

            // 위로 i+1칸
            for(int j=0; j<i+1; j++) {
                r -= 1;
                if (!it.hasNext()) return map;
                else map[r][c] = it.next();
            }

            // 왼쪽으로 i+1칸
            for(int j=0; j<i+1; j++) {
                c -= 1;
                if (!it.hasNext()) return map;
                else map[r][c] = it.next();
            }

            i += 2;
        }

        return map;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
