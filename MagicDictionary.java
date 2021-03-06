import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class MagicDictionary 
{    
    Map<String, List<int[]>> map;
    
    /** Initialize your data structure here. */
    public MagicDictionary() 
    {
        map = new HashMap<String, List<int[]>>();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) 
    {
        for (String str : dict)
        {
            for (int i = 0; i < str.length(); i++)
            {
                String key = str.substring(0, i) + str.substring(i + 1);
                map.putIfAbsent(key, new ArrayList<int[]>());
                map.get(key).add(new int[] {i, str.charAt(i)});
            }
        }
    }
    
    public boolean search(String word) 
    {
        for (int i = 0; i < word.length(); i++)
        {
            String key = word.substring(0, i) + word.substring(i + 1);
            
            if (map.containsKey(key))
            {
                for (int j = 0; j < map.get(key).size(); j++)
                {
                    int[] valArr = map.get(key).get(j);
                    if (valArr[0] == i && valArr[1] != word.charAt(i))
                        return true;
                }
            }            
        }
        return false;
    }
    
    public static void main(String[] args)
    {
        MagicDictionary obj = new MagicDictionary();
        obj.buildDict(new String[] {"hello","leetcode"});
        System.out.println(obj.search("hello"));
        System.out.println(obj.search("hhllo"));
        System.out.println(obj.search("hell"));
        System.out.println(obj.search("leetcoded"));
        System.out.println(obj.search("leetcodd"));
    }
}
