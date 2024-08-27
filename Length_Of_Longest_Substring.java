
import java.util.HashMap;
import java.util.HashSet;


/*
 * TC :
 * 
 * LeetCode : https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 * 
 * Given a string s, find the length of the longest substring without repeating characters.
 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * BruteForce : TC O(n^3)
 */

 /*
 * 
 *Class  Example : s = zxlmnablcdmalcd



  * Approach 1 : Take two pointers and a hashset ( sliding window approach)
  We can keep on adding the characters as soon as we are encountering into the set, then we will move our fast pointer. So 
  we keep moving our fast pointer and keep adding the unique characters to set. So say in above example we added all characters 
  in set till next l in encountered. When we come to l character we see that l is already present in the hashset we are maintaining
  Which means we are able to locate the repeating character. So whateveer length we have till now we will record that length which is now
  7 and move the slow pointer till the time we are not escaping the repeating character. And as we move our slow pointer that 
  means those characters need to be removed from our set. As soon as I am reaching the repeating character l at index 2, we will
  only increment our slow pointer and m,n,a,b are the characters right now we have in our current window between two l. 
  Basically we are sliding our window,we are sliding on the basis of some condition that till the time we are not reaching the 
  repeating character we will keep on moving our window.
  Now when the fast pointer is againg moving and reaches repeating character at m, we then try to move oour slow ptr. 
  But the slow ptr is already on m, so we only increment slow ptr by 1 and we have escaped our repeating character at index 3. 
  This way we keep on moving and check the length everytime and 
  see if we have found a better length than previous one we record id

  TC : O(2n).. as we have 2 pointers..slow is moving and fast is moving ..  which is O(n)
  SC :  O(26) valid alphabets or O(256) .. valid ascii characters .. which is equal to O(1)
  

  */
  public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int slow = 0;
        int max = 0;

        for(int i = 0; i< s.length();i++){
          char c = s.charAt(i);
          if(set.contains(c)){
            // this means its a repeating character, move the left ptr till we escape the repeating character.
            while(s.charAt(slow) != c){
              set.remove(s.charAt(slow)); // also remove from the set.
              slow++; // increment slow 
            }
            slow++; // when we are out of while loop slow will be aat repeating character, so this line is to incrementt it by 1.
          }
          else{
            set.add(c);
          }
          //calculate max
          max = Math.max(max,i-slow+1);
        }
        return max;
    }
}


/*
 * 
 * Class  Example : s = zxlmnablcdmalcd
 * 
 * Approach 2 : To reduce O(2n).. that is we iterate slow pointer 1 by 1 , instead we try to move slow pointers by some 
 * distance based on the index values; stored in hashmap. So we use the sliding window approach + hashmap
 * So we are moving fast ptr over the elements and slow pointer by the (index we get from the hashmap) + 1 to it. 
 * Also, before moving the slow ptr we will be recording the length. And then update the index of that repeating character in map
 * So instead of approach 1 where we remove characters 1 by 1 from the set and moving the slow ptr 1  by 1 , we maintain 
 * a hashmap where we store the indexes of the characters. 
 * Also important note if the repeating character is not present in the current window between slow and fast ptr, 
 * in this case when alphabert x is found in below example , this will mean 
 * the slow pointer index is greater than the index found in the hashmap of the repeating character. So we compare the index
 * we are getting for the slow and  index we are getting from the map. The index of repeating character from map is smaller than
 * the slow ptr.; that means the repeating charcter does not belong to the current window. Then we will only update the index of the
 * repeating character in map.
 * 
 * 
 * Example :  s = zxlmnablcdmaxcd
 * 
 * TC : O(n) .. as we one ptr over elements, slow ptr is moving based on the obtained index size + 1
 * SC : O(26) valid alphabets or O(256) .. valid ascii characters .. which is equal to O(1)
 */

 public int lengthOfLongestSubstring(String s) {
      HashMap<Character,Integer> map = new HashMap<>();
      int slow = 0;
      int max = 0;

      for(int i =0; i<s.length();i++){
        char c = s.charAt(i);
        if(map.containsKey(c)){
          // max between this will help to move slow to the required index, and if not it will help to keep at the index incase the 
          // the repeating character is not in the current window
          slow = Math.max(slow, map.get(c)+1);
        }
        map.put(c, i);
        max = Math.max(max, i-slow+1);
      }
      return max;
 }