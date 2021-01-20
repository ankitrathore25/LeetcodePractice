package prison_cells_after_n_days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution1MyLongWayButEasy {

	public static int[] prisonAfterNDays(int[] cells, int n) {

		int num = getBinaryArrayToInteger(cells);

		Map<Integer, Integer> map = new HashMap<>();
		int repeatAt = 0;
		boolean hasCycle = false;
		for (int j = 0; j < n; j++) {
			if (map.containsKey(num)) {
				hasCycle = true;
				break;
			}
			int temp = num;
			num = nextDay(num);
			map.put(temp, num);
			repeatAt++;
		}

		if (hasCycle) {
			int size = getCycleSize(map, num);
			n = (n - (repeatAt - size)) % size;
			for (int i = 0; i < n; i++) {
				num = nextDay(num);
			}
		}

		int[] ans = getBinaryArray(Integer.toBinaryString(num));

		return ans;
	}

	private static int getBinaryArrayToInteger(int[] cells) {
		int num = 0;
		for (int i = 0; i < 8; i++) {
			num += cells[7 - i] * Math.pow(2, i);
		}
		return num;
	}

	public static int getCycleSize(Map<Integer, Integer> map, int num) {
		int size = 0;
		Set<Integer> s = new HashSet<>();
		while (!s.contains(num)) {
			s.add(num);
			size++;
			num = map.get(num);
		}
		return size;
	}

	public static int nextDay(int num) {
		int mask = 126; // 01111110 mask to remove left most and right most bit
		int n1 = (num << 1) & 0xff;
		int n2 = (num >> 1) & 0xff;
		num = (n1 ^ n2 ^ 255) & mask;
		return num;
	}

	private static int[] getBinaryArray(String a) {
		int[] ans = new int[8];
		int k = 7;
		for (int i = a.length() - 1; i >= 0; i--) {
			ans[k--] = Integer.valueOf(a.charAt(i) - '0');
		}
		return ans;
	}

	public static void main(String[] args) {
//		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
//		int N = 7;
		int[] cells = new int[] {1,0,0,1,0,0,1,0};
		int N = 1000000000;

//		int[] cells = new int[] { 1, 0, 1, 0, 0, 0, 1, 0 };
//		int N = 788566492;

		int[] result = prisonAfterNDays(cells, N);
		for (int i = 0; i < result.length; i++)
			System.out.print(result[i] + ",");
	}

}
