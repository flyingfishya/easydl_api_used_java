package javakeshe_121;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class czy_pachong {
	    //��һ����������������������
	    public static void get_html(String url){
	        try {
	            Document doc = Jsoup.connect(url).get();
	            System.out.println(doc);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public static void main(String[] args) {
	        String url = "https://y.qq.com/";    
	        get_html(url);
	    }
	 
}
