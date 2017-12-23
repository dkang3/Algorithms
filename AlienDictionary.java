import java.util.Arrays;

public class AlienDictionary 
{
	public String alienOrder(String[] words) 
	{
	    boolean[][] adj = new boolean[26][26];
	    int[] visited = new int[26];
	    
	    buildGraph(words, adj, visited);
	
	    StringBuilder sb = new StringBuilder();
	    
	    for(int i = 0; i < 26; i++) 
	    {
	        if(visited[i] == 0)						// unvisited 
	        {                 
	            if(!dfs(adj, visited, sb, i))
	            {
	            	return "";
	            }
	        }
	    }
	    return sb.reverse().toString();
	}
	
	public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) 
	{
	    visited[i] = 1;                            	// 1 = visiting
	    for(int j = 0; j < 26; j++) 
	    {
	        if(adj[i][j])						    // connected 
	        {                        
	            if(visited[j] == 1)				    // 1 => 1, cycle 
	            	return false;     
	            if(visited[j] == 0)					// 0 = unvisited 
	            {              
	                if(!dfs(adj, visited, sb, j))
	                {
	                	return false;
	                }
	            }
	        }
	    }
	    visited[i] = 2;                           // 2 = visited
	    sb.append((char) (i + 'a'));
	    return true;
	}
	
	public void buildGraph(String[] words, boolean[][] adj, int[] visited) 
	{
	    Arrays.fill(visited, -1);                 // -1 = not even existed
	    
	    for(int i = 0; i < words.length; i++) 
	    {
	        for(char c : words[i].toCharArray())
	        {
	        	visited[c - 'a'] = 0;
	        }
	        	
	        if(i > 0) 
	        {
	            String w1 = words[i - 1], w2 = words[i];
	            int len = Math.min(w1.length(), w2.length());
	            
	            for(int j = 0; j < len; j++) 
	            {
	                char c1 = w1.charAt(j);
	                char c2 = w2.charAt(j);
	                
	                if(c1 != c2) 
	                {
	                    adj[c1 - 'a'][c2 - 'a'] = true;
	                    break;
	                }
	            }
	        }
	    }
	}
	
	public static void main(String[] args)
	{
		AlienDictionary obj = new AlienDictionary();
		String[] words = new String[] {"wrt","wrf","er","ett","rftt"};
		System.out.println(obj.alienOrder(words));
	}
}