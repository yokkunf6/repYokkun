package util.list;

import java.util.List;
import java.util.Map;

public class ListUtil {
	public static Integer max(List<Integer> list) {
		int result = Integer.MIN_VALUE;
		for (int i : list) {
			result = Math.max(result, i);
		}
		return result;
	}

	public static Object getMaxKey(Map<? extends Object, ? extends Number> map) {
		Object maxKey = null;
		Number max = null;
		for (Object key : map.keySet()) {
			if (max == null) {
				maxKey = key;
				max = map.get(key);
			} else {
				if (map.get(key).doubleValue() > max.doubleValue()) {
					maxKey = key;
					max = map.get(key);
				}
			}
		}
		return maxKey;
	}
}
