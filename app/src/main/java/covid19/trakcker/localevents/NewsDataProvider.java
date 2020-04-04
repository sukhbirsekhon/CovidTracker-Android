package covid19.trakcker.localevents;

public class NewsDataProvider
{
    private String source;
    private String title;
    private String description;
    private String url;
    private String publishedAt;
    private String content;
    private String urlToImage;

    private boolean expanded;

    public NewsDataProvider(String title, String description, String content, String url, String publishedAt, String urlToImage)
    {
        this.title = title;
        this.description = description;
        this.content = content;
        this.url = url;
        this.publishedAt = publishedAt;
        this.urlToImage = urlToImage;
    }
    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
