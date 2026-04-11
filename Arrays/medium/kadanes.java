class Solution {
    public int maxSubArray(int[] nums) {
        int curr = 0;
        int max = Integer.MIN_VALUE;
        for(int x: nums){
            curr = Math.max(curr+x,x);
            max = Math.max(curr,max);
        }
        return max;
    }
}