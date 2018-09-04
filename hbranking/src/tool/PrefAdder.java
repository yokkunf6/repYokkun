package tool;

import util.FileUtil;
import java.util.*;
public class PrefAdder {
	private static String RESULT = "file\\Result.txt";
	private static String RESULT_TMP_OUT = "file\\resultTmpOut.txt";
	private static String RESULT_TMP_OUT2 = "file\\resultTmpOut2.txt";
	public static void main(String[] args) throws Exception {
		List<String> res = FileUtil.readFile(RESULT);
		Map<String, String> map = new HashMap<>();
		for (String line : res) {
			String[] split = line.split("\t");
			if (split.length < 9) continue;
			if (map.containsKey(split[7])
				&& !split[5].equals(map.get(split[7]))) {
				if (!map.get(split[7]).contains(split[5])) {
					if (!map.get(split[7]).startsWith("?")) {
						map.put(split[7], "?{" + map.get(split[7]) + "," + split[5] + "}");
					} else {
						String rep = map.get(split[7]);
						rep = rep.replaceAll("\\}", "," + split[5] + "\\}");
						map.put(split[7], rep);
					}
				}
			} else {
				map.put(split[7], split[5]);
			}
			if (map.containsKey(split[8])
				&& !split[6].equals(map.get(split[8]))) {
				if (!map.get(split[8]).contains(split[6])) {
					if (!map.get(split[8]).startsWith("?")) {
						map.put(split[8], "?{" + map.get(split[8]) + "," + split[6] + "}");
					} else {
						String rep = map.get(split[8]);
						rep = rep.replaceAll("\\}", "," + split[6] + "\\}");
						map.put(split[8], rep);
					}
				}
			} else {
				map.put(split[8], split[6]);
			}
		}

		List<String> tmp1 = FileUtil.readFile(RESULT_TMP_OUT);
		List<String> tmp2 = new ArrayList<>();
		for (String line : tmp1) {
			String[] split = line.split("\t");
			StringBuilder sb = new StringBuilder();
			if (split.length >= 2) {
				String winnerPref = map.get(split[0]);
				String loserPref = map.get(split[1]);
				sb.append(winnerPref + "\t" + loserPref + "\t");
			}
			sb.append(line);
			tmp2.add(sb.toString());
		}
		FileUtil.writeFile(tmp2, RESULT_TMP_OUT2);
	}
}
