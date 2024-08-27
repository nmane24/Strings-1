
import java.util.HashMap;

/*
 * 
 * LeetCode : https://leetcode.com/problems/custom-sort-string/
 * You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.
Permute the characters of s so that they match the order that order was sorted. More specifically, 
if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

 * 
 * Example 1:
Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:
Input: order = "bcafg", s = "abcd"
Output: "bcad"
Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, 
so its position is flexible.

Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed 
at any position since it's not in order. The output "bcad" correctly follows this rule. 
Other arrangements like "dbca" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.
 * 
 * Code Explanation :
 * 
 * Aqpproach 1 : Using HashMap
 * 1. Create a frequency map for all characters for the s string
 * Iterate on the order string, pick the character from order string, and if it is there in the hashamp,
 * we take the frequency numnber and append to string builder the times frequency is found.
 * As we fetch from map, those characters of order string and append to new string, we also remove it from the hashmap
 * Whatever characters we are left with in the hashmap, we iterate over the map and append to the string builder.
 * 
 * 
 * TC  : O(m + n) , as we are iterting on both the strings. But at max in my order string we can have all the lowercase characters 
 * which can be 26, so O(m) will become O(1).. so the tc : O(n), where n is the length of the string s
 * 
 * SC : O(n) .. O(1) we can have 26 characters in hashmap. 
 */

 public class custom_Sort_String {
    public String customSortString(String order, String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        //Put all the characters from s into hashmap
        for(int  i =0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        } 

        StringBuilder result = new StringBuilder();

        // appending the characters from order first into result
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            if(map.containsKey(c)){
                int cnt = map.get(c);
                for(int k  = 0 ; k<cnt ;cnt++){
                    result.append(c)
                }
            }
            map.remove(c);
        }

        //appending remaining characters from srting s to result.
        for(char c : map.keySet()){
            int cnt = map.get(c);
            for(int k  = 0;k<cnt; k++){
                result.append(c);
            }
        }
        return result.toString();
    }
     
}
