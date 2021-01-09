package find_k_closest_elements;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Log_N_time_solution {

	public static List<Integer> findClosestElements(int[] A, int k, int x) {
		/*
		 * Time O(Log N)
		 */
		int left = 0, right = A.length - k;
		while (left < right) {
			int mid = (left + right) / 2;
			if (x - A[mid] > A[mid + k] - x)
				left = mid + 1;
			else
				right = mid;
		}
		return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());

	}

	public static void main(String[] args) {
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
