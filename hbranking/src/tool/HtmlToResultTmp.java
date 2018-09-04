package tool;

import util.FileUtil;
import java.util.*;

public class HtmlToResultTmp {
	private static String HTML = "file\\html.txt";
	private static String RESULT_TMP_OUT = "file\\resultTmpOut.txt";
	private static Map<String, String> replaceMap = new HashMap<>();
	static {
		replaceMap.put("弘前学院聖愛", "聖愛");
		replaceMap.put("PL学園", "ＰＬ学園");
		replaceMap.put("ビジネスフロンティア", "大阪ビジネスフロンティア");
		replaceMap.put("ICU", "ＩＣＵ");
		replaceMap.put("飯田OIDE長姫", "飯田ＯＩＤＥ長姫");
	}
	public static void main(String[] args) throws Exception {
		List<String> html = FileUtil.readFile(HTML);
		List<String> ret = new ArrayList<>();
		for (String line : html) {
			if (!line.contains("-")) {
				continue;
			}
			String winner;
			String loser;
			int ws;
			int ls;
			int inning;
			String[] split1 = line.split("-");
			String[] split2 = split1[0].split(" ");
			String[] split3 = split1[1].split(" ");
			// 勝者
			winner = split2[0];
			winner = winner.replaceAll("ケ", "ヶ");
			winner = replace(winner);
			ws = new Integer(split2[1]);
			// 敗者
			loser = split3[1];
			loser = loser.replaceAll("ケ", "ヶ");
			loser = replace(loser);
			ls = new Integer(split3[0]);

			try {
				if (line.contains("延長")) {
					String s = line.substring(line.indexOf("延長") + 2, line.indexOf("延長") + 4);
					inning = new Integer(s);
				} else if (line.contains("コールド")) {
					String s = line.substring(line.indexOf("回コールド") - 1, line.indexOf("回コールド"));
					inning = new Integer(s);
				} else {
					inning = 9;
				}
			} catch (Exception e) {
				System.out.println(line);
				throw e;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(winner + "\t");
			sb.append(loser + "\t");
			sb.append(ws + "\t");
			sb.append(ls + "\t");
			sb.append(inning);
			ret.add(sb.toString());
		}
		FileUtil.writeFile(ret, RESULT_TMP_OUT);
	}
	private static String replace(String s) {
		if (replaceMap.containsKey(s)) {
			return replaceMap.get(s);
		} else {
			return s;
		}
	}
}
