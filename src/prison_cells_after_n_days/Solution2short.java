package prison_cells_after_n_days;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution2short {

	public static int[] prisonAfterNDays(int[] cells, int N) {
		Map<Integer, Integer> map = new HashMap<>();
		int num = getBinaryArrayToInteger(cells);
		while (N > 0) {
			int temp = num;
			if (map.containsKey(num)) {
				int cycleSize = getCycleSize(map, num);
				N = N % cycleSize;
				for (int i = 0; i < N; i++) {
					cells = getNextDayCell(cells);
				}
				return cells;
			}
			cells = getNextDayCell(cells);
			num = getBinaryArrayToInteger(cells);
			map.put(temp, num);
			N--;
		}
		return cells;
	}

	private static int getBinaryArrayToInteger(int[] cells) {
		int num = 0;
		for (int i = 0; i < 8; i++) {
			num += cells[7 - i] * Math.pow(2, i);
		}
		return num;
	}

	public static int getCycleSize(Map<Integer, Integer> map, Integer num) {
		int size = 0;
		Set<Integer> s = new HashSet<>();
		while (!s.contains(num)) {
			s.add(num);
			size++;
			num = map.get(num);
		}
		return size;
	}

	private static int[] getNextDayCell(int[] cells) {
		int[] nextCell = new int[8];
		for (int i = 1; i < 7; i++) {
			nextCell[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
		}
		return nextCell;
	}

	public static void main(String[] args) {
//		int[] cells = new int[] { 0, 1, 0, 1, 1, 0, 0, 1 };
//		int N = 7;
//		int[] cells = new int[] { 1, 0, 0, 1, 0, 0, 1, 0 };
//		int N = 1000000000;

		int[] cells = new int[] { 1, 0, 1, 0, 0, 0, 1, 0 };
		int N = 788566492;

//		int[] cells = new int[] { 1, 0, 0, 0, 1, 0, 0, 1 };
//		int N = 99;

		int[] result = prisonAfterNDays(cells, N);
		for (int i = 0; i < result.length; i++)
			System.out.print(result[i] + ",");
	}

}
