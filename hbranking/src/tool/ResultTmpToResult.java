package tool;

import util.FileUtil;
import util.HankakuNumberToZenkakuNumber;

import java.util.*;

public class ResultTmpToResult {
	private static String RESULT_TMP = "file\\resultTmp.txt";
	private static String RESULT_TMP_OUT = "file\\resultTmpOut.txt";
	public static void main(String[] args) throws Exception {
		List<String> resultTmps = FileUtil.readFile(RESULT_TMP);
		List<String> ret = new ArrayList<>();
		for (String line : resultTmps) {
			StringBuilder sb = new StringBuilder();
			String[] split = line.split("\t");
			if (split.length > 3) {
				ret.add(line);
				continue;
			}
			sb.append(split[0] + "\t" + split[1] + "\t");
			String score = null;
			try {
				score = split[2];
			} catch (Exception e) {
				System.out.println(line);
				throw e;
			}
			score = HankakuNumberToZenkakuNumber.hankakuNumberToZenkakuNumber(score);
			if (score.length() == 2) {
				score = score.substring(0, 1) + "\t" + score.substring(1, 2) + "\t" + 9;
			} else if (score.length() == 3) {
				if (score.startsWith("1")) {
					score = score.substring(0, 2) + "\t" + score.substring(2, 3) + "\t" + 9;
				} else {
					score = score.substring(0, 1) + "\t" + score.substring(1, 2) + "\t" + score.substring(2, 3);
				}
			} else if (score.length() == 4) {
				int extra = 0;
				int cold = 0;
				int nine = 0;
				if (new Integer(score.substring(0, 1)) >= new Integer(score.substring(1, 2))
					&& new Integer(score.substring(2, 4)) >= 10 && new Integer(score.substring(2, 4)) <= 15) {
					extra = 1;
				}
				if (new Integer(score.substring(0, 2)) <= 19
					&& new Integer(score.substring(3, 4)) >= 5 && new Integer(score.substring(3, 4)) <= 8) {
					cold = 1;
				}
				if (new Integer(score.substring(0, 2)) >= new Integer(score.substring(2, 4))
					&& new Integer(score.substring(2, 3)) != 0
					&& new Integer(score.substring(0, 2)) <= 19
					&& new Integer(score.substring(3, 4)) <= 14) {
					nine = 1;
				}
				if (extra + cold + nine == 0 || extra + cold + nine >= 2) {
					System.out.println(line);
				} else if (extra == 1) {
					score = score.substring(0, 1) + "\t" + score.substring(1, 2) + "\t" + score.substring(2, 4);
				} else if (cold == 1) {
					score = score.substring(0, 2) + "\t" + score.substring(2, 3) + "\t" + score.substring(3, 4);
				} else if (nine == 1) {
					score = score.substring(0, 2) + "\t" + score.substring(2, 4) + "\t" + 9;
				}
			} else {
				System.out.println(line);
			}
			sb.append(score);
			ret.add(sb.toString());
		}
		FileUtil.writeFile(ret, RESULT_TMP_OUT);
	}
}
