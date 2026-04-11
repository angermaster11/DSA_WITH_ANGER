class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int currMax = 0;
        int max = nums[0];

        int currMin = 0;
        int min = nums[0];

        for(int x: nums){
            currMax = Math.max(currMax+x,x);
            max = Math.max(currMax,max);

            currMin = Math.min(currMin+x,x);
            min = Math.min(currMin,min);

            total +=x;
        }
        // / If all numbers are negative
        if (max < 0) return max;

        return Math.max(max,total-min);
    }
}