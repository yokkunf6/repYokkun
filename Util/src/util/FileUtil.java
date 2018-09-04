package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> readFile(String filename) throws Exception {
		return readFile(new File(filename));
	}

	public static List<String> readFile(File file) throws Exception {
		List<String> ret = new ArrayList<>();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		while ((line = br.readLine()) != null) {
			if (line.length() == 0) continue;
			ret.add(line);
		}
		return ret;
	}

	public static void writeFile(List<? extends Object> list, String filename) throws Exception {
		FileWriter fw = new FileWriter(new File(filename));
		BufferedWriter bw = new BufferedWriter(fw);
		for (Object obj : list) {
			bw.append(obj + "\r\n");
		}
		bw.flush();
		bw.close();
		fw.close();
	}

}
