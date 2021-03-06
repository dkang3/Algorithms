public class LongestCommonSubstring 
{
	int LCSubStr(char X[], char Y[], int m, int n) 
	{
	    // Create a table to store lengths of longest common suffixes of
	    // substrings. Note that LCSuff[i][j] contains length of longest
	    // common suffix of X[0..i-1] and Y[0..j-1]. The first row and
	    // first column entries have no logical meaning, they are used only
	    // for simplicity of program
		
	    int lcs[][] = new int[m + 1][n + 1];
	    int result = 0;  // To store length of the longest common substring
	     
	    // Following steps build lcs[m+1][n+1] in bottom up fashion
	    for (int i = 1; i <= m; i++) 
	    {
	        for (int j = 1; j <= n; j++) 
	        {
	            if (X[i - 1] == Y[j - 1])
	            {
	            	lcs[i][j] = lcs[i - 1][j - 1] + 1;
	                result = Integer.max(result, lcs[i][j]);
	            } 
	            else
	            	lcs[i][j] = 0;
	        }
	    }
	    return result;
	}
	public static void main(String[] args) 
	{
		LongestCommonSubstring obj = new LongestCommonSubstring();
		String X = "OldSite:GeeksforGeeks.org";
		String Y = "NewSite:GeeksQuiz.com";		
		System.out.println(obj.LCSubStr(X.toCharArray(), Y.toCharArray(), X.length(), Y.length()));
		//Result should be 10 i.e. "Site:Geeks"
	}
}
