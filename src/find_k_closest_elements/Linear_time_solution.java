package find_k_closest_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Linear_time_solution {

	public static List<Integer> findClosestElements(int[] arr, int k, int x) {
		/* Time O(N) 
		 * easy to understand
		 */

		int lo = 0;
		int hi = arr.length - 1;
		while (hi - lo >= k) {
			if (Math.abs(arr[lo] - x) > Math.abs(arr[hi] - x)) {
				lo++;
			} else {
				hi--;
			}
		}
		List<Integer> result = new ArrayList<>(k);
		for (int i = lo; i <= hi; i++) {
			result.add(arr[i]);
		}
		return result;
	}

	public static void main(String[] args) {
//		List<Integer> arrList = Arrays.asList(0,1,1,1,2,3,6,7,8,9,11,13,15,16,17,19,20,22,24,26,27,29,30,34,36,37,38);
//		List<Integer> arrList = Arrays.asList(0,0,0,1,3,5,6,7,8,8);
		List<Integer> arrList = Arrays.asList(1, 1, 1, 10, 10, 10);
		int[] arr = new int[arrList.size()];
		for (int i = 0; i < arrList.size(); i++)
			arr[i] = arrList.get(i);

		int k = 1;
		int x = 9;
		List<Integer> ans1 = findClosestElements(arr, k, x);
		for (int i = 0; i < ans1.size(); i++) {
			System.out.print(ans1.get(i) + ",");
		}
	}

}
