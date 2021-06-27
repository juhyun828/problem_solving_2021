// 210626

class Solution_Lesson1_1_BinaryGap {
	public int solution(int N) {
        // 이진수로 변환
        String binary = Integer.toBinaryString(N);

        int max = 0;
        for(int i=0; i<binary.length(); i++) {
            int cur = stoi(binary.substring(i, i+1));
            if(cur==0) continue;
            else {
                for(int j=i+1; j<binary.length(); j++) {
                    int next = stoi(binary.substring(j, j+1));
                    if(next==1) {
                        max = Math.max(max, j-i-1);
                        break;
                    }
                }
            }
        }
        return max;
    }

    static int stoi(String str) {
        return Integer.parseInt(str);
    }
}
