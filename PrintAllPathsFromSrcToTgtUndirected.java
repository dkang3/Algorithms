import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintAllPathsFromSrcToTgtUndirected 
{
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) 
    {
        Graph g = new Graph();
        int n = graph.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < graph[i].length; j++)
            {
                g.addEdge(i, graph[i][j]);
            }
        }

        List<Integer> curr = new ArrayList<Integer>();
        curr.add(0);
        dfs(g, result, -1, 0, n - 1, curr);
        return result;
    }

    void dfs(Graph g, List<List<Integer>> result, int parent, int src, int dest, List<Integer> curr)
    {
        if(src == dest)
        {
            result.add(new ArrayList<Integer>(curr));
            return;
        }

        Set<Integer> adjList = g.adj.get(src);
        
        if(adjList != null)
        {
            for(int neighbor : adjList)
            {
            	//We don't need a dedicated visited array, instead use curr to check it.
            	//Note: The below check is only required when cycle can exist in the graph.
            	//So, for a directed acyclic graph this check is not required; 
            	//however, it's a good practice to always do this check. Another, benefit is 
            	//that with below condition, this solution will also work for undirected graph. 
            	
            	if(curr.contains(neighbor))
            		continue;
            	
            	if(parent == neighbor)
            		continue;
            	
                curr.add(neighbor);
                dfs(g, result, src, neighbor, dest, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }
    
	public static void main(String[] args) 
	{
		PrintAllPathsFromSrcToTgtUndirected obj = new PrintAllPathsFromSrcToTgtUndirected();
		//Input when cycle doesn't exist
		System.out.println(obj.allPathsSourceTarget(new int[][] {{1,2}, {3}, {3}, {}}));
		//Input when cycle exists
		System.out.println(obj.allPathsSourceTarget(new int[][] {{1,2}, {2,3}, {0,3}, {}}));
		//Input when cycle exists
		System.out.println(obj.allPathsSourceTarget(new int[][] {{1,2,3}, {3}, {0,1}, {}}));
	}
}

class Graph
{
    Map<Integer, Set<Integer>> adj = new HashMap<Integer, Set<Integer>>();
    
    void addEdge(int u, int v)
    {
        adj.putIfAbsent(u, new HashSet<Integer>());
        adj.get(u).add(v);
        adj.putIfAbsent(v, new HashSet<Integer>());
        adj.get(v).add(u);

    }
}
