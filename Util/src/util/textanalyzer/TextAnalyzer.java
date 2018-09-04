package util.textanalyzer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.codelibs.neologd.ipadic.lucene.analysis.ja.JapaneseTokenizer;
import org.codelibs.neologd.ipadic.lucene.analysis.ja.tokenattributes.PartOfSpeechAttribute;
public class TextAnalyzer {

	public static List<String> getPersonNames(String text) {
		List<String> result = new ArrayList<String>();
		try {
			JapaneseTokenizer tokenizer = new JapaneseTokenizer(null, false, JapaneseTokenizer.DEFAULT_MODE);	        tokenizer.setReader(new StringReader(text));
	        CharTermAttribute term = tokenizer.addAttribute(CharTermAttribute.class);
		    PartOfSpeechAttribute partOfSpeech = tokenizer.addAttribute(PartOfSpeechAttribute.class);
		    tokenizer.reset();

		    String name = "";
		    while (tokenizer.incrementToken()) {
		        // System.out.println(term.toString() + "\t" +  partOfSpeech.getPartOfSpeech());
		    	if (partOfSpeech.getPartOfSpeech().contains("人名")) {
		    		boolean existSei = name.length() > 0;
		    		name += term.toString();
		    		if (existSei) {
		    			if (!result.contains(name)) result.add(name);
		    			name = "";
		    		}
		    	} else {
		    		name = "";
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
