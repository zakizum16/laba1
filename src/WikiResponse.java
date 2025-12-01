import java.util.List;

public class WikiResponse {
    public Query query;

    public static class Query {
        public List<SearchResult> search;
    }
}