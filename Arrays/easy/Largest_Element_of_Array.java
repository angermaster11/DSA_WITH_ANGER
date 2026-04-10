
class Solution {
    public static int largest(int[] arr) {
        // code here
        int max = arr[0];
        for(int x : arr){
            if(x>max){
                max = x;
            }
        }
        return max;
    }
}
