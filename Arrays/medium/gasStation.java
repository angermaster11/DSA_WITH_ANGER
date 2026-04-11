class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total_gas=0, total_cost = 0;
        for(int i=0;i<gas.length;i++){
            total_gas += gas[i];
            total_cost += cost[i];
        }
        if(total_cost>total_gas) return -1;
        int curr = 0, fuel = 0;
        for(int i=0;i<gas.length;i++){
            fuel += gas[i] - cost[i];
            if(fuel<0){
                fuel = 0;
                curr = i+1;
            }

        }
        return curr;
    }
}