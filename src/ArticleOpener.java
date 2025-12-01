import java.awt.Desktop;
import java.net.URI;

public class ArticleOpener {
    private static final String WIKI_ARTICLE_URL = "https://ru.wikipedia.org/w/index.php?curid=";

    public static void openArticle(SearchResult result) throws Exception {
        String articleUrl = WIKI_ARTICLE_URL + result.getPageid();
        System.out.println("Открываю: " + articleUrl);
        Desktop.getDesktop().browse(new URI(articleUrl));
    }
}