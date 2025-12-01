import java.util.List;

public class ResultDisplayer {
    public static void displayResults(List<SearchResult> results) {
        System.out.println("\nРезультаты поиска:");
        for (int i = 0; i < results.size(); i++) {
            SearchResult result = results.get(i);
            System.out.printf("%d. %s — %s%n",
                    i + 1,
                    result.getTitle(),
                    result.getCleanSnippet());
        }
    }
}