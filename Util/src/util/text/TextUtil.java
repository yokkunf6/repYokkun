package util.text;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {
    public static List<Integer> getDistance(String text, String center, String target) {
    	List<Integer> result = new ArrayList<Integer>();
		int distance = Integer.MAX_VALUE;
		int index = 0;
		while (text.substring(index).indexOf(center) != -1) {
			// ターゲットの位置
			int targetPos = text.substring(index).indexOf(target) + index;
			int namePos = text.indexOf(center);
			// ターゲットが先、名前が後
			if (targetPos < namePos) result.add(Math.abs(targetPos - namePos + target.length() + 1));
			else result.add(Math.abs(targetPos - namePos));
			index = index + text.substring(index).indexOf(target) + target.length();
		}
    	return result;
    }
}
