// 210407 

class Solution_LV3_단어변환 {
    static boolean[] v;
    static int min, n;

    static boolean check(String a, String b) {
        int cnt=0;
        for(int k=0; k<a.length(); k++) {
            if(a.charAt(k) != b.charAt(k)) ++cnt;
            if(cnt>1) return false;
        }

        if(cnt==1) return true;
        else return false;
    }

    static void dfs(int next, String target, int cnt, String[] words) {
        if(words[next].equals(target)) {
            min = Math.min(min, cnt);
            return;
        }
        if(next==n) return;

        for(int i=0; i<n; i++) {
            if(!check(words[next], words[i])) continue;
            if(v[i]) continue;
            v[i] = true;
            dfs(i, target, cnt+1, words);
            v[i] = false;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        n = words.length;
        v = new boolean[n];
        min = Integer.MAX_VALUE;

        for(int i=0; i<n; i++) {
            if(begin==words[i]) continue;
            if(check(begin, words[i])) {
                v = new boolean[n];
                v[i] = true;
                dfs(i, target, 1, words);
            }
        }

        if(min==Integer.MAX_VALUE) return 0;
        else return min;
    }
}