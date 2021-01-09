package pairs_of_songs_with_total_durations_divisible_by_60;

import java.util.Arrays;
import java.util.List;

public class MySolution {

	public static int numPairsDivisibleBy60(int[] time) {
		int[] remCount = new int[60];
		for (int i = 0; i < time.length; i++) {
			int rem = time[i] % 60;
			remCount[rem]++;
		}

		int count = 0;
		count += (remCount[0] * (remCount[0] - 1)) / 2;  // remainder 0
        count += (remCount[30] * (remCount[30] - 1)) / 2;  // remainder 30
		for (int i = 1; i < 30; i++) {
			if (remCount[i] == 0 || remCount[60 - i] == 0) {
				continue;
			} else {
				count += remCount[i] * remCount[60 - i];
			}
		}
		
		return count;
	}

	public static void main(String[] args) {
		List<Integer> timeList = Arrays.asList(30,20,150,100,40,20,220,240,248,120,60,420,180,300,450,220,440,480,120,390,410,460,410,440);
		int[] time = new int[timeList.size()];
		for (int i = 0; i < timeList.size(); i++)
			time[i] = timeList.get(i);
		int ans = numPairsDivisibleBy60(time);
		System.out.println(ans);
	}

}
