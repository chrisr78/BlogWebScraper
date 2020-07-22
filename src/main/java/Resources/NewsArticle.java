package Resources;

public class NewsArticle {
    private int articleId;
    private String title;
    private String author;
    private String url;
    private String img;
    private String src;

    public NewsArticle(String title, String author, String url, String imgSrc, String src){
        this.title = title;
        this.author = author;
        this.url = url;
        this.img = imgSrc;
        this.src = src;
    }

    public NewsArticle(){}

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

}
