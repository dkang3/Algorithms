
public class WiggleSubsequence 
{
    public int wiggleMaxLength(int[] nums) 
    {
        int n = nums.length;
        
        if (n == 0)
            return 0;
        
        int[] up = new int[n];
        int[] down = new int[n];
        
        up[0] = 1;
        down[0] = 1;
        
        for (int i = 1; i < n; i++)
        {
            if (nums[i - 1] < nums[i])
            {
                up[i] = down[i - 1] + 1;    //+1
                down[i] = down[i - 1];
            }
            else if (nums[i - 1] > nums[i])
            {
                down[i] = up[i - 1] + 1;    //+1
                up[i] = up[i - 1];
            }
            else
            {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }
}