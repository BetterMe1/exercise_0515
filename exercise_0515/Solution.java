package exercise.exercise_0515;

import java.util.*;

/*
47. 全排列 II
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:
输入: [1,1,2]
输出:
 [
   [1,1,2],
   [1,2,1],
   [2,1,1]
 ]

 */
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        List<Integer> list = new ArrayList<>();
        permuteUniqueHelper(result,list,used,nums);
        return result;
    }
    private void permuteUniqueHelper(List<List<Integer>>result,List<Integer> list,
                                     int[] used,int[] nums){
        if(list.size() == nums.length){
            List<Integer> temp = new ArrayList<>(list);
            result.add(temp);
            return;
        }
        int lastUsed = Integer.MAX_VALUE;//初始化
        for(int i=0; i<nums.length; i++){
            if(used[i] == 0 && nums[i] != lastUsed){//跳过重复
                used[i] = 1;//标记
                list.add(nums[i]);//添加
                permuteUniqueHelper(result,list,used,nums);
                //恢复
                lastUsed = nums[i];
                used[i] = 0;
                list.remove(list.size()-1);
            }
        }
    }

}

/*
49. 字母异位词分组
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
示例:
输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
 [
   ["ate","eat","tea"],
   ["nat","tan"],
   ["bat"]
 ]

说明：
•所有输入均为小写字母。
•不考虑答案输出的顺序。

 */
//方法一
/*
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        int len = strs.length;
        if(strs == null || len == 0){
            return result;
        }
        String[] temp = new String[len];
        for(int i=0; i<len; i++){
            char[] t = strs[i].toCharArray();
            Arrays.sort(t);
            temp[i] = String.valueOf(t);
        }
        boolean[] flag = new boolean[len];
        for(int i=0; i<len; i++){
            if(!flag[i]){
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                flag[i] = true;
                for(int j=i+1; j<len; j++){
                    if(!flag[j] && temp[j].equals(temp[i])){
                        list.add(strs[j]);
                        flag[j] = true;
                    }
                }
                result.add(list);
            }
        }
        return result;
    }
}
*/

//方法二
/*
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List> map = new HashMap<>();
        for(String str : strs){
            //将原字符串排序后变成新的字符串temp
            char[] t = str.toCharArray();
            Arrays.sort(t);
            String temp = String.valueOf(t);
            //没有映射关系时创建映射关系,映射关系为：排序后的字符串 -> 未排序的原字符串的List集合
            if(!map.containsKey(temp)){
                map.put(temp,new ArrayList<>());
            }
            //根据映射关系添加单词
            map.get(temp).add(str);
        }
        return new ArrayList(map.values());//返回包含所有值（也就是未排序的原字符串按键分组）的列表
    }
}
*/
