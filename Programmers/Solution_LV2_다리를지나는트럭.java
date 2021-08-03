import java.util.*;
// 210718

class Solution_LV2_다리를지나는트럭 {

    static class Truck {
        int weight, time;
        public Truck(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int curWeight = 0, time = 0;
        Queue<Truck> onBridgeQue = new ArrayDeque<Truck>(); // 다리를 건너고 있는 트럭
        Stack<Integer> waitingStack = new Stack<>();

        // 스택에 트럭을 거꾸로 넣는다.
        for(int i=truck_weights.length-1; i>=0; i--) {
            waitingStack.add(truck_weights[i]);
        }

        while(!(waitingStack.isEmpty()&&onBridgeQue.isEmpty())) {
            ++time;

            // 다리를 건너는 중인 트럭들 시간 +1, 시간 다 되면 빠져나옴
            if(!onBridgeQue.isEmpty()) {
                Iterator<Truck> it = onBridgeQue.iterator();
                while(it.hasNext()) {
                    Truck cur = it.next();
                    if(cur.time==bridge_length) {
                        onBridgeQue.poll();
                        curWeight -= cur.weight;
                    }
                    else cur.time += 1;
                }
            }
            
            // 대기중인 트럭들
            if (waitingStack.isEmpty()) continue;
            if(curWeight+waitingStack.peek() <= weight) {
                // 다리에 오를 수 있으면
                int truck = waitingStack.pop();
                curWeight += truck;
                onBridgeQue.offer(new Truck(truck, 1));
            }
        }

        return time;
    }
}