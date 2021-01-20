package analyze_user_website_visit_pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MySolution {

	class UserData {
		String website;
		String user;
		int time;

		public UserData() {
		}

		public UserData(String website, String user, int time) {
			this.website = website;
			this.user = user;
			this.time = time;
		}
	}

	public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
		Map<String, List<UserData>> data = new HashMap<>();
		int n = username.length;
		MySolution obj = new MySolution();
		for (int i = 0; i < n; i++) {
			if (!data.containsKey(username[i]))
				data.put(username[i], new ArrayList());
			data.get(username[i]).add(obj.new UserData(website[i], username[i], timestamp[i]));
		}

		Map<String, Map<String, Set<String>>> sequenceUserMap = new HashMap<>();

		for (String usr : data.keySet()) {
			generateSequence(usr, data.get(usr), new ArrayList<UserData>(), 0, data.get(usr).size(), sequenceUserMap);
		}

		List<String> ans = new ArrayList<>();
		String maxSeq = "";
		int maxSeqCount = 0;
		String forUser = "";
		Map<String, Set<String>> r = new HashMap<>();
		Set<String> timeStampStr = new HashSet<>();
		for (String seq : sequenceUserMap.keySet()) {
			Map<String, Set<String>> usrMap = sequenceUserMap.get(seq);
			int count = 0;
			for (String u : usrMap.keySet()) {
				count += usrMap.get(u).size();
				forUser = u;
			}

			if (count > maxSeqCount) {
				maxSeq = seq;
				maxSeqCount = count;
				timeStampStr = usrMap.get(forUser);
				r.clear();
				r.putAll(usrMap);
			} else if (count == maxSeqCount) {
				if (seq.compareTo(maxSeq) < 0) {
					maxSeq = seq;
					r.clear();
					r.putAll(usrMap);
					timeStampStr = usrMap.get(forUser);
				}
			}
		}
//		String[] web = maxSeq.split(":");
//		Set<String> sset = r.values();
//		String[] time = 

		List<String> websiteList = new ArrayList<>(Arrays.asList(website));
		List<Integer> timeList = new ArrayList<>();
		for (int w = 0; w < timestamp.length; w++)
			timeList.add(timestamp[w]);

		for (String s1 : timeStampStr) {
			String[] tempo = s1.split(":");
			int[] tym = new int[3];
			tym[0] = Integer.valueOf(tempo[0]);
			tym[1] = Integer.valueOf(tempo[1]);
			tym[2] = Integer.valueOf(tempo[2]);

			int[][] t = new int[3][2];
			int[][] inde = new int[3][2];
			inde[0][0] = timeList.indexOf(tym[0]);
			timeList.set(timeList.indexOf(tym[0]), Integer.valueOf(tym[0]) + 39);
			inde[1][0] = timeList.indexOf(tym[1]);
			timeList.set(timeList.indexOf(tym[1]), Integer.valueOf(tym[1]) + 161);
			inde[2][0] = timeList.indexOf(tym[2]);

			inde[0][1] = 0;
			inde[1][1] = 1;
			inde[2][1] = 2;

			Arrays.sort(inde, (a, b) -> a[0] - b[0]);
			ans.add(websiteList.get(inde[0][1]));
			ans.add(websiteList.get(inde[1][1]));
			ans.add(websiteList.get(inde[2][1]));

			break;
		}

//		List<String> websiteList = new ArrayList<>(Arrays.asList(website));
//		int[][] t = new int[3][2];
//		int[][] inde = new int[3][2];
//		inde[0][0] = websiteList.indexOf(web[0]);
//		websiteList.set(websiteList.indexOf(web[0]), web[0] + "1");
//		inde[1][0] = websiteList.indexOf(web[1]);
//		websiteList.set(websiteList.indexOf(web[1]), web[1] + "2");
//		inde[2][0] = websiteList.indexOf(web[2]);
//
//		inde[0][1] = 0;
//		inde[1][1] = 1;
//		inde[2][1] = 2;
//
//		Arrays.sort(inde, (a, b) -> a[0] - b[0]);
//		ans.add(web[inde[0][1]]);
//		ans.add(web[inde[1][1]]);
//		ans.add(web[inde[2][1]]);

		return ans;
	}

	public static void generateSequence(String user, List<UserData> dataList, List<UserData> listSoFar, int index,
			int n, Map<String, Map<String, Set<String>>> sequenceUserMap) {
		if (listSoFar.size() == 3) {
			Collections.sort(listSoFar, new Comparator<UserData>() {
				@Override
				public int compare(UserData u1, UserData u2) {
					if (u1.website.compareTo(u2.website) == 0) {
						return Integer.compare(u1.time, u2.time);
					} else {
						return u1.website.compareTo(u2.website);
					}
				}
			});
			String seq = listSoFar.get(0).website + ":" + listSoFar.get(1).website + ":" + listSoFar.get(2).website;
			String timeString = String.valueOf(listSoFar.get(0).time) + ":" + String.valueOf(listSoFar.get(1).time)
					+ ":" + String.valueOf(listSoFar.get(2).time);
			if (!sequenceUserMap.containsKey(seq))
				sequenceUserMap.put(seq, new HashMap<String, Set<String>>());

			Map<String, Set<String>> m = sequenceUserMap.get(seq);
			Set<String> l = m.getOrDefault(user, new HashSet<String>());
			l.add(timeString);
			m.put(user, l);
			sequenceUserMap.put(seq, m);
			return;
		}

		for (int i = index; i < n; i++) {
			generateSequence(user, dataList, new ArrayList<UserData>(listSoFar), i + 1, n, sequenceUserMap);
			listSoFar.add(dataList.get(i));
			generateSequence(user, dataList, new ArrayList<UserData>(listSoFar), i + 1, n, sequenceUserMap);
			listSoFar.remove(listSoFar.size() - 1);
		}
		return;
	}

	public static void main(String[] args) {
//			cart:home:maps={james=[5:7:6, 5:4:6]}, 
//					home:home:maps={james=[4:7:6]}, 
//					about:career:home={joe=[2:3:1], mary=[9:10:8]}, 
//					cart:home:home={james=[5:4:7]}}
//		String[] username = new String[] { "joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary",
//				"mary" };
//		int[] timestamp = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
//		String[] website = new String[] { "home", "about", "career", "home", "cart", "maps", "home", "home", "about",
//				"career" };

		String[] username = new String[] { "u1", "u1", "u1", "u2", "u2", "u2" };
		int[] timestamp = new int[] { 1, 2, 3, 4, 5, 6 };
		String[] website = new String[] { "a", "b", "a", "a", "b", "c" };
//		

//		String[] username = new String[] { "dowg", "dowg", "dowg" };
//		int[] timestamp = new int[] { 158931262, 562600350, 148438945 };
//		String[] website = new String[] { "y", "loedo", "y" };

		List<String> res = mostVisitedPattern(username, timestamp, website);

		for (int i = 0; i < res.size(); i++)
			System.out.println(res.get(i));

	}

}
