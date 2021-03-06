M
1520228394
tags: Hash Table, String

大清洗 O(nk)   
map.size一旦>k，要把longest string最开头（marked by pointer:start）的那个char抹掉    
一旦某一个char要被清除，所以在这个char 的1st and last appearance之间的char都要被清洗from map


```
/*
Given a string s, find the length of the longest substring T that contains at most k distinct characters.

Example
For example, Given s = "eceba", k = 3,

T is "eceb" which its length is 4.

Challenge
O(n), n is the size of the string s.

Tags Expand
String Two Pointers LintCode Copyright Hash Table

*/

/*
Thoughts:
Two pointer, use HashMap to record the passed <char, count>.
If map.size() == k, check string length.
If map.size() > k, start moving front index i until map.size() reduces [while]
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
        int n = s.length();
        int start = 0;
        int end = 0;
        int rst = Integer.MIN_VALUE;
        HashMap<Character, Integer> map = new HashMap<>();
        while (start < n) {
            while (end < n) {
                char c = s.charAt(end);
                if (map.containsKey(c)) {
                    map.put(c, map.get(c) + 1);
                } else {
                    if (map.size() == k) {
                        break;
                    }
                    map.put(c, 1);
                }                
                end++;
            }
            // Calculate length when map.size() == n or end == n
            rst = Math.max(rst, end - start);

            // move start forward
            char c = s.charAt(start);
            int count = map.get(c);
            if (count == 1) {
                map.remove(c);
            } else {
                map.put(c, count - 1);
            }
            start++;
        }

        return rst;
    }
}


/*
Thoughts:
HashMap size cannot go over k.
Map<Char, #of apperance>.
Use a start pointer to track the begin of current longest substring.
If map.size() > k, then remove: count-= map.get(s.charAt(i)); map.remove(s.charAt(i));

Note:
assume we need to remove the first char of current longest string, and that char has multiple apperances later in the string
Do while on map.size>k, and remove all chars before the last appearce of that particular string.
Therefore, we need erase that particular char and all other chars before that particular char's last apperance.
*/
public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
    	if (s == null || s.length() == 0 || k <= 0) {
    		return 0;
    	}
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	int count = 0;
    	int start = 0;
    	int max = 0;
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (map.containsKey(c)) {
    			map.put(c, map.get(c) + 1);
    		} else {
    			map.put(c, 1);
    			while (map.size() > k) {
	    			char rm = s.charAt(start);
	    			int tempCount = map.get(rm);
	    			if (tempCount > 1) {
	    				map.put(rm, tempCount - 1);
	    			} else {
	    				map.remove(rm);
	    			}
	    			start++;
	    			count--;
	    		}
    		}
    		count++;
    		max = Math.max(max, count);
    	}
    	return max;
    }
}



/*
    3.1.2016. 
    How about map to store last occurance of each char.
    Use pointer head to show which char to cut off from string

    Not work: still has to clean up all chars that being cut off from the map, which is O(n) again. 
    So the solutoin has to be O(nk)
*/


```