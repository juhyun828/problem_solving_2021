import java.util.*;
// 210709

class Solution_LV3_기둥과보설치 {
    static class Frame implements Comparable<Frame> {
        int x, y, stuff;
        public Frame(int x, int y, int stuff) {
            this.x = x;
            this.y = y;
            this.stuff = stuff;
        }
        
        @Override
        public int compareTo(Frame o) {
            if(this.x==o.x) {
                if(this.y==o.y) {
                    return Integer.compare(this.stuff, o.stuff);
                }
                return Integer.compare(this.y, o.y);
            }
        
            return Integer.compare(this.x, o.x);
        }
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        ArrayList<Frame> list = new ArrayList<Frame>();
        
        for(int[] frame: build_frame) {
            int x = frame[0];
            int y = frame[1];
            int stuff = frame[2];
            int oper = frame[3];
            
            if(oper==0) { // 삭제
                // 삭제할 frame 찾기
                int removeIdx =-1;
                for(int i=0; i<list.size(); i++) {
                    Frame frameB = list.get(i);
                    if(x==frameB.x&&y==frameB.y&&stuff==frameB.stuff) {
                        removeIdx = i;
                        break;
                    }
                }
                Frame before = list.get(removeIdx);
                list.remove(removeIdx);

                if(!check(list)) { // 삭제 불가
                    list.add(before);
                }
                
            } else if(oper==1) { // 설치
                list.add(new Frame(x, y, stuff));
                if(!check(list)) {
                    list.remove(list.size()-1);
                }
            }
        }
        
        Collections.sort(list);
        answer = new int[list.size()][3];
        for(int i=0; i<list.size(); i++) {
            Frame frame = list.get(i);
            answer[i][0] = frame.x;
            answer[i][1] = frame.y;
            answer[i][2] = frame.stuff;
            //System.out.println(Arrays.toString(answer[i]));
        }
        
        return answer;
    }
    
    static boolean check(ArrayList<Frame> list) {
        for(Frame frameA : list) {
            int xA = frameA.x;
            int yA = frameA.y;
            int stuffA = frameA.stuff;
            
            if(stuffA==0) { // 기둥
                boolean flag = false;
                if(yA == 0) flag = true;
                for(Frame frameB : list) {
                    int xB = frameB.x;
                    int yB = frameB.y;
                    int stuffB = frameB.stuff;
                    if(stuffB==1 && yA==yB && (xA==xB || xA==xB+1)) flag=true;
                    else if(stuffB==0 && xA==xB && yA==yB+1) flag=true;
                }
                if(!flag) return false;
            } else if(stuffA==1) { // 보
                boolean flag = false, left = false, right = false;
                for(Frame frameB : list) {
                    int xB = frameB.x;
                    int yB = frameB.y;
                    int stuffB = frameB.stuff;
                    if(stuffB==0) { // 기둥
                        if(xA==xB && yA==yB+1) {
                            flag = true;
                        } else if(xA==xB-1 && yA==yB+1) {
                            flag = true;
                        }
                        
                    } else if(stuffB==1) { // 보
                        if(yA==yB && xA==xB-1) {
                            left = true;
                        } else if(yA==yB && xA==xB+1) {
                            right = true;
                        }
                    }
                }
                if(left&&right) flag = true;
                if(!flag) return false;
            }
        }
        
        return true;
    }

}