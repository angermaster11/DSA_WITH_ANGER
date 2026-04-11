class Solution {
    public int trap(int[] height) {
        // Approach is first get  the av tak ka maximum of left and right then
        //  find the  min of each ith index then sum these value.
        int[] left =  new int[height.length];
        int [] right = new int[height.length];

        left[0] = height[0];
        for(int i=1;i<height.length;i++){
            left[i] = Math.max(left[i-1],height[i]);
        }

        int n = height.length;
        right[n-1] = height[n-1];
        for(int i=n-2;i>=0;i--){
            right[i] =  Math.max(right[i+1],height[i]);
        }


        int ans = 0;
        for(int i=0;  i<n;i++){
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;

    }


}