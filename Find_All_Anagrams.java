

/*
 * 
 * LeetCode : https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
 * 
 * 
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, 
typically using all the original letters exactly once.

 

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 

Class example
p = "abca"
s = "cbaaebabacd"

output: [0,6]

/*
 * So ways to check of they are anagram or not
 * 1. Sort
 * 2. Hashing  ( frequency map )
 * 3. Prime Product


BruteForce : Form all the possible substrings of length p (pattern) string, so form all the substring of length 4.
Then check each of  the formed  subtrings if it is an anagram of the pattern string or not.
For checking if they are anagrams or not, we will sort both the strings( pattern and substring) and check if they are equal.

TC : O(m*n) , m*n for creating a substring of length 4  and then if we do sorting it m*n * log term ,
if we doo hashing then it would be m*n *length of pattern string 
if we doo prime product then m*n* length of pattern string 

AApproach 1 : Create hashmap and check with substrings
Create a hashmap of pattern strings with the frequency into it. Create substrings from s and check those substrings with the hashmap
which is same as hashing pattern for checking if it is anagram or not.


Class example
p = "abca"
s = "cbaaebabacd"

Intuition for approach 2 is instead of checking complete substring of that length 4, 
we will just check for the two character, one incoming and one outgoing.


Approach 2  : Sliding window pattern
One character is incoming and one character is outgoing in the sliding window pattern, just have to check for those two characters
WE are trying to avoid checking for all the charcters in that substring.
Intially we create a map of pattern string; a frequency map. map :{a:2, b:1, c:1}.
Now we check the incoming chharacter from s, so do we have c in our map, yes we have then reduce the frequency of that character
in map by 1. We reduce it as it is matching one of the characters in our pattern string. Now if the frequency count of that character
in map becomes 0, we will have match variable where will increase it by 1.
We will have incoming characters till the length of the pattern string, as soon as our i ptr if it reaches more than the length
of the pattern string, from there onwards we will start having outgoing string. that is when i will reach our 5th character,
from there onwaards we will start having outgoing character as well. If our match  becomes equal to the size of the map, it meaans
we are able to find one anagram and we record the index by i - len(pattern string) + 1 

Now when i becomes greater than the length of the pattern string, check for the incoming character  first 'e'. If the incoming character
is not there in map then do nothing. Then check for the outgoing character which is 'c' at index 0. Was 'c' in map, yes it was
increase its frequencyb by 1 as it is not present in current window.
We decreased the frequency when the character was there in the current window. Now that the character 'c' is not there in the  current
window we increase the frequency. if the frequency becomes equal to 1, we will be decreasing our match as well.
1st window was cbaa , 2nd window baae, now again we move our i by 1 , so it is at index 5 at char  'b'. So 'b' is our incoming char
Note the window size is currently 5 as outgoing character is at  index 1 'b'.  
So now our logic , do we have incomng character b in our map, yes we have freq : 0, then reduce it, freq becomes -1. -1 freq indicates
we have extra characters  in our window than what we require in our pattern string

Algo
match variable to track anagram
// incoming
if(map.contains(incoming)){
    count --; (frequency of char in map)
    if(count == 0){
        match ++;
    }
}
    //outgoing
if(map.contains(outgoing)){
    count ++; (frequency of char in map)
    if(count == 1){
        match --;
    }
}
//record the index
if(match == map.size){
    // get the index
    i - length(pattern string ) + 1;
}


TC  : O(m + n) as O(m).. iteraing on pattern string ; O(n).. iterating on given string ith pointer
O(m+n).. max(m,n).. n>m ( given string will greater than pattern string) .. so O(n)

SC : O(1) .. length of the pattern string .. as we only have our map which can have all lower case char 26 in our map

 */
//

import java.util.ArrayList;
import java.util.List;

public class Find_All_Anagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int m = s.length();
        int n = p.length();

        if(n > m) return  result; // if pattern is greater than given string return result

        // create a frequency map for the pattern string
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i = 0; i< n;i++){
            char c = p.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        int match = 0;  // tracking if anagram found or not

        // iterate over the given string s using the ith pointer and calculate icoming and outgoing
        for(int i = 0; i<m;i++){
            //incoming
            char in = s.charAt(i);
            if(map.containsKey(in)){
                int cnt = map.get(in);
                cnt--;
                if(cnt == 0){
                    match++;
                }
            }

            //outgoing
            // outgoing comes into picture once index i is greater thanor equal tto the pattern string
            if(i >=n){
                char out = s.charAt(i-n);
                if(map.containsKey(out)){
                    int cnt = map.get(out);
                    cnt ++;
                    if(cnt ==1){
                        match--;
                    }
                }

            }

            //check if anagram found or not
            if(match == map.size()){
                result.add(i-n+1);
            }

        }
        return  result;

    }
}
