import java.util.*;
// 210408

class Solution_LV3_여행경로 {
    static String[][] sortedTickets;
    static int n; // 티켓 개수
    static Stack<String> st;
    static boolean[] v;
    static ArrayList<String> route;

    static boolean dfs(String end, int cnt) {
        if (cnt==n) {
            st.push(end); // 최종 도착 공항
            route = new ArrayList<String>();
            for (Iterator<String> it = st.iterator(); it.hasNext(); ) {
                route.add(it.next());
            }
            return true;
        }

        for(int i=0; i<n; i++) {
            // 사용한 티켓
            if(v[i]) continue;
            // 이동가능한 티켓이 아님
            if(!end.equals(sortedTickets[i][0])) continue;

            // 이용가능 : 이번의 끝 == 다음의 시작
            st.push(end); v[i] = true;
            if(dfs(sortedTickets[i][1], cnt+1)) return true;
            st.pop(); v[i] = false; // 백트래킹
        }
        return false;
    }

    public String[] solution(String[][] tickets) {
        n = tickets.length;

        // 1. tickets 정렬하기
        sortedTickets = new String[n][2];
        for(int i=0; i<n; i++) {
            for(int j=0; j<2; j++) {
                sortedTickets[i][j] = tickets[i][j];
            }
        }
        Arrays.sort(sortedTickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[0].equals(o2[0]))
                    return o1[1].compareTo(o2[1]);
                return o1[0].compareTo(o2[0]);
            }
        });
        
        // 2. ICN에서 시작하는 티켓부터 탐색 시작
        for(int i=0; i<n; i++) {
            if(sortedTickets[i][0].equals("ICN")) {
                // 탐색 가능
                v = new boolean[n];
                v[i] = true;
                st = new Stack<String>();
                st.push("ICN"); // 최초 출발 공항
                // 3. 한번 탐색에 성공하면 더이상의 탐색을 멈춘다.
                // - 알파벳 순으로 정렬했기 때문에 최초로 탐색에 성공한 경우가 답이다.
                if(dfs(sortedTickets[i][1], 1)) break;
            }
        }

        // route to answer
        String[] answer = new String[n+1];
        int idx=-1;
        for(String s: route){
            answer[++idx] = s;
        }
        return answer;
    }
}