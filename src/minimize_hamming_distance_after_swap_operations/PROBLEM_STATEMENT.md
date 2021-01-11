# [1722. Minimize Hamming Distance After Swap Operations](https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/)


You are given two integer arrays, `source` and `target`, both of length `n`. You are also given an array `allowedSwaps` where each `allowedSwaps[i] = [a<sub>i</sub>, b<sub>i</sub>]` indicates that you are allowed to swap the elements at index `a<sub>i</sub>` and index `b<sub>i</sub>` **(0-indexed)** of array `source`. Note that you can swap elements at a specific pair of indices **multiple** times and in **any** order.

The **Hamming distance** of two arrays of the same length, `source` and `target`, is the number of positions where the elements are different. Formally, it is the number of indices `i` for `0 <= i <= n-1` where `source[i] != target[i]` **(0-indexed)**.

Return _the **minimum Hamming distance** of_ `source` _and_ `target` _after performing **any** amount of swap operations on array_ `source`_._

**Example 1:**

<pre>
**Input:** source = [1,2,3,4], target = [2,1,4,5], allowedSwaps = [[0,1],[2,3]]
**Output:** 1
**Explanation:** source can be transformed the following way:
- Swap indices 0 and 1: source = [<u>2</u>,<u>1</u>,3,4]
- Swap indices 2 and 3: source = [2,1,<u>4</u>,<u>3</u>]
The Hamming distance of source and target is 1 as they differ in 1 position: index 3.
</pre>

**Example 2:**

<pre>
**Input:** source = [1,2,3,4], target = [1,3,2,4], allowedSwaps = []
**Output:** 2
**Explanation:** There are no allowed swaps.
The Hamming distance of source and target is 2 as they differ in 2 positions: index 1 and index 2.
</pre>

**Example 3:**

<pre>
**Input:** source = [5,1,2,4,3], target = [1,5,4,2,3], allowedSwaps = [[0,4],[4,2],[1,3],[1,4]]
**Output:** 0
</pre>

**Constraints:**

* `n == source.length == target.length`
* `1 <= n <= 10<sup>5</sup>`
* `1 <= source[i], target[i] <= 10<sup>5</sup>`
* `0 <= allowedSwaps.length <= 10<sup>5</sup>`
* `allowedSwaps[i].length == 2`
* `0 <= a<sub>i</sub>, b<sub>i</sub> <= n - 1`
* `a<sub>i</sub> != b<sub>i</sub>`