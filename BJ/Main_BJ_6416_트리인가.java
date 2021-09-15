import java.io.*;
import java.util.*;
// 210916

public class Main_BJ_6416_트리인가 {
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> vertex; // 정점번호 : 들어오는 간선 개수 -> 들어오는 간선이 0이면 루트
        // 조건1> 루트는 1개만 있어야 함
        // 조건2> 루트를 제외한 모든 노드들은 진입 간선이 하나
        // 조건3> 간선 수 == 정점의 개수 -1 -> 루트부터 모든 노드에 가는 경로는 하나 뿐이기 때문

        for(int tc=1; ; tc++) {
            vertex = new HashMap<Integer, Integer>();
            int edge = 0;

            loop:
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine(), "  ");
                while (st.hasMoreTokens()) {
                    int start = stoi(st.nextToken());
                    int end = stoi(st.nextToken());

                    // 테스트 케이스 끝
                    if(start==0 && end==0) break loop;
                    // 종료
                    if(start==-1 && end==-1) System.exit(0);

                    // start 정점도 표기 해둬야 함 -> root 정점인지 판별하기 위해
                    vertex.put(start, vertex.getOrDefault(start, 0));
                    // end 정점에 들어가는 간선 수 추가
                    vertex.put(end, vertex.getOrDefault(end, 0)+1);

                    //if(!vertex.containsKey(start)) vertex.put(start, 0);
                    //if(vertex.containsKey(end)) vertex.put(end, vertex.get(end)+1);
                    //else vertex.put(end, 1);

                    ++edge; // 간선 수 증가
                }
            }

            // root 개수 세기
            int root = 0;
            boolean incomingEdge = true;
            for(int v: vertex.keySet()) {
                if(vertex.get(v)==0) ++root; // 진입 간선이 없기 때문에 root
                else if(vertex.get(v) > 1) { // 진입 간선은 1개만 있어야 함
                    incomingEdge = false;
                    break;
                }
            }

            if(vertex.size()==0 || (root==1 && incomingEdge && edge == vertex.size()-1)) {
                // 정점이 없어도 트리다.
                // 1. root가 1개이고 2. 모든 정점의 진입 노드가 1개 씩이고 3. 간선 개수 == 정점 개수 -1
                System.out.println("Case " + tc + " is a tree.");
            } else {
                System.out.println("Case " + tc + " is not a tree.");
            }
        }
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}