package util.web;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebAccessor {

	private static String USER_AGENT = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";

	public static Document getDocument(String url) {
		Document document = null;
        try {
    		document = Jsoup.connect(url).userAgent(USER_AGENT).get();
        } catch (HttpStatusException e) {
        	System.out.println(e.getClass().getName() + ",message:" + e.getMessage() + ",surl:"+ url);
        } catch (SocketTimeoutException e) {
        	System.out.println(e.getClass().getName() + ",message:" + e.getMessage() + ",surl:"+ url);
        } catch (UnsupportedMimeTypeException e) {
        	System.out.println(e.getClass().getName() + ",message:" + e.getMessage() + ",surl:"+ url);
        } catch (IOException e) {
        	System.out.println(e.getClass().getName() + ",message:" + e.getMessage() + ",surl:"+ url);
        }
        return document;
	}

	public static String getText(String url) {
		Document document = getDocument(url);
		return getText(document);
	}

	public static String getText(Document document) {
    	StringBuilder builder = new StringBuilder();
        Elements elements = document.body().getAllElements();
        for (Element element : elements) {
            if (element.ownText() == null) {
                continue;
            }
            builder.append(element.ownText()).append("\n");
        }
		return builder.toString();
	}
}
