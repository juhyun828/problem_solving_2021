import java.io.*;
import java.util.*;

// 210214
public class Solution_D3_6806_규영이와인영이의카드게임_2 {
	
	static int win, lose;
	static int[] numbers1, numbers2;
	static int N = 9;
	static boolean[] visited;

	static void dfs(int cnt, int guSum, int inSum) {
		if (cnt==N) {
			if (guSum>inSum) {
				win++;
			} else {
				lose++;
			}
			return;
		}
		
		int guCard, inCard;
		
		for(int i=1; i<=N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			
			guCard = numbers1[cnt];
			inCard = numbers2[i-1];
			
			if(guCard>inCard) {
				dfs(cnt+1,guSum+inCard+guCard, inSum);
			} else {
				dfs(cnt+1,guSum, inSum+inCard+guCard);
			}
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_D3_6806_규영이와인영이의카드게임.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		numbers1 = new int[N]; // 규영이 카드 저장 배열
		numbers2 = new int[N]; // 인영이 카드 저장 배열
		String line;
		StringTokenizer st = null;
		int[] card = new int[19]; // 1~1까지의 숫자
		visited = new boolean[19]; // 방문 체크
		
		int data;
		
		for(int tc=1; tc<=T; tc++) {
			line = br.readLine();
			st = new StringTokenizer(line.trim());
			
			for(int i=0; i<N; i++) {
				data = Integer.parseInt(st.nextToken());
				card[data] = 1; // 규영이 받은 9장의 카드를 카드 배열에 1로 표시
				numbers1[i] = data; // 규영이 받은 숫자 저장
			}
			
			data = 0;
			for(int i=1; i<card.length; i++) {
				if(card[i]==0) {
					numbers2[data++] = i; // 인영이 받은 숫자 저장
				}
			}
			
			dfs(0, 0, 0);
			System.out.println("#" + tc + " " + win + " " + lose);
			
			win = 0;
			lose = 0;
			Arrays.fill(card, 0);
		}
		
		br.close();
	}

}