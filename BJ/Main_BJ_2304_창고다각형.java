import java.io.*;
import java.util.*;
// 210917

public class Main_BJ_2304_창고다각형 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int start = 10001, end = 0;
        int[] arr = new int[1001];
        StringTokenizer st;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int L = stoi(st.nextToken());
            int H = stoi(st.nextToken());
            arr[L] = H;
            start = Math.min(start, L);
            end = Math.max(end, L);
        }

        Stack<Integer> stack = new Stack<Integer>();
        // 왼쪽부터
        int pivot = arr[start];
        for(int i=start; i<=end; i++) {
            if(pivot > arr[i]) stack.add(i);
            else {
                while (!stack.isEmpty()) {
                    int lower = stack.pop();
                    arr[lower] = pivot;
                }
                pivot = arr[i];
            }
        }

        stack.clear();
        // 오른쪽부터
        pivot = arr[end];
        for(int i=end; i>=start; i--) {
            if(pivot > arr[i]) stack.add(i);
            else {
                while (!stack.isEmpty()) {
                    int lower = stack.pop();
                    arr[lower] = pivot;
                }
                pivot = arr[i];
            }
        }

        int width = 0;
        for(int i=start; i<=end; i++) {
            width += arr[i];
        }

        System.out.println(width);

        br.close();
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}