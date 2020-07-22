package Utilities;

import Resources.NewsArticle;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VergeSraper {
    private static final String vergeUrl = "https://www.theverge.com/tech";
    private static WebClient client = new WebClient();
    private static List<NewsArticle> newsArticles = new ArrayList<NewsArticle>();

    public static List<NewsArticle> getVergeArticles() throws IOException {
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        HtmlPage page = client.getPage(vergeUrl);
        List<HtmlElement> htmlList = page.getByXPath("//div[@class='c-compact-river__entry ']" +
                "/div[@class='c-entry-box--compact c-entry-box--compact--article']");

        for(int i=3; i>0; i--){
                try {
                    String title = ((HtmlElement) htmlList.get(i)
                            .getFirstByXPath("./div/h2[@class='c-entry-box--compact__title']"))
                            .asText();
                    String url = ((HtmlAnchor) htmlList.get(i)
                            .getFirstByXPath("./div/h2[@class='c-entry-box--compact__title']/a"))
                            .getHrefAttribute();

                    String author = ((HtmlElement) htmlList.get(i)
                            .getFirstByXPath("./div/div/span/span/a/span[@class='c-byline__author-name']"))
                            .asText();

                    String imgSrc = ((HtmlImage) htmlList.get(i)
                            .getFirstByXPath("./a[@class='c-entry-box--compact__image-wrapper'][1]" +
                                    "/div[@class='c-entry-box--compact__image'][1]/noscript[1]/img[1]")).getSrcAttribute();
                    String theVerge = "TheVerge";
                    NewsArticle newsArticle = new NewsArticle(title, author, url, imgSrc, theVerge);
                    newsArticles.add(newsArticle);
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }

       }
        return newsArticles;
    }
}
