public class SearchResult {
    private int pageid;
    private String title;
    private String snippet;

    public int getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getCleanSnippet() {
        return snippet.replaceAll("<.*?>", "");
    }
}