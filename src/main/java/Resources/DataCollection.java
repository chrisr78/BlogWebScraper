package Resources;

public class DataCollection {
    String source;
    int scraped;
    int served;

    public void DataCollection(String source, int scraped, int served){
        this.source = source;
        this.scraped = scraped;
        this.served = served;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getScraped() {
        return scraped;
    }

    public void setScraped(int scraped) {
        this.scraped = scraped;
    }

    public int getServed() {
        return served;
    }

    public void setServed(int served) {
        this.served = served;
    }
}
