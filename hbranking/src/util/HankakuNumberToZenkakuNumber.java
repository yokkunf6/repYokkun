package util;

public class HankakuNumberToZenkakuNumber {
	  public static String hankakuNumberToZenkakuNumber(String s) {
	    StringBuffer sb = new StringBuffer(s);
	    for (int i = 0; i < s.length(); i++) {
	      char c = s.charAt(i);
	      if (c >= '０' && c <= '９') {
	        sb.setCharAt(i, (char) (c + '0' - '０'));
	      }
	    }
	    return sb.toString();
	  }
}