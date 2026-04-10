class Solution {
    public int getSecondLargest(int[] arr) {
        // code here
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        
        for(int x: arr){
            if(x>first ){
                second = first;
                first = x;
            }
            else if(x > second && x!=first){
                second = x;
            }
        }
        return second == Integer.MIN_VALUE ? -1 : second;
    }
}