package util;

import java.util.Map;

public class Util {

	public static void add(Map<String, Integer> map, String key, int value) {
		if (!map.containsKey(key)) {
			map.put(key, value);
			return;
		}
		int before = map.get(key);
		map.put(key, before + value);
	}

}
