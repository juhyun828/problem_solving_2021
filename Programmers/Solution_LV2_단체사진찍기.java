// 210620

class Solution_LV2_단체사진찍기 {
    static String[] data;
    static int total;
    static String[] friends = new String[] { "A", "C", "F", "J", "M", "N", "R", "T" };
    static boolean[] v;
    
    public int solution(int n, String[] inputData) {
        data = inputData;
        total = 0;
        v = new boolean[8];
        
        for (int i = 0; i < 8; i++) {
            v[i] = true;
            dfs(1, friends[i]);
            v[i] = false;
        }
        return total;
    }
    
    static void dfs(int depth, String curString) {
        if (depth >= 8) {
            //System.out.println(curString);
            ++total;
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (!v[i] && isPossible(friends[i], curString)) {
                v[i] = true;
                dfs(depth + 1, curString + friends[i]);
                v[i] = false;
            }
        }
    }

    static boolean isPossible(String strA, String curString) {
        for (String d : data) {
            if (d.substring(0, 1).equals(strA)) { // 조건 당사자
                String strB = d.substring(2, 3);
                int diff = Integer.parseInt(d.substring(4));
                String condition = d.substring(3, 4);

                for (int i = 0; i < curString.length(); i++) {
                    String cur = curString.substring(i, i + 1);
                    if (cur.equals(strB)) {
                        int tmpDiff = (curString.length()-1) - i;
                        //System.out.println(tmpDiff + " " + strA + " " + cur);
                        if (condition.equals("=") && tmpDiff != diff) {
                            return false;
                        } else if(condition.equals(">") && !(tmpDiff > diff)) {
                            return false;
                        } else if(condition.equals("<") && !(tmpDiff < diff)) {
                            return false;
                        }
                    }
                }

            } else if (d.substring(2, 3).equals(strA)) { // 자신이 상대방
                String strB = d.substring(0, 1);
                int diff = Integer.parseInt(d.substring(4));
                String condition = d.substring(3, 4);

                for (int i = 0; i < curString.length(); i++) {
                    String cur = curString.substring(i, i + 1);
                    if (cur.equals(strB)) {
                        int tmpDiff = (curString.length()-1) - i;
                        //System.out.println(tmpDiff + " " + strA + " " + cur);
                        if (condition.equals("=") && tmpDiff != diff) {
                            return false;
                        } else if(condition.equals(">") && !(tmpDiff > diff)) {
                            return false;
                        } else if(condition.equals("<") && !(tmpDiff < diff)) {
                            return false;
                        }
                    }
                }
            } */
        }
        return true;
    }    
    
}