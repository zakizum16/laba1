import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WikiSearcher {
    private static final String WIKI_API_URL = "https://ru.wikipedia.org/w/api.php";
    private static final String USER_AGENT = "Laba1_WikiSearchBot/1.0 (https://example.com)";

    public List<SearchResult> search(String query) throws Exception {
        System.out.println("Запрос получен");

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        System.out.println("Запрос закодирован");

        String urlStr = WIKI_API_URL + "?action=query&list=search&utf8=&format=json&srsearch=" + encodedQuery;
        URL url = new URL(urlStr);
        System.out.println("Создан URL");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        Gson gson = new Gson();
        WikiResponse wikiResponse = gson.fromJson(response.toString(), WikiResponse.class);

        // Прямой доступ к public полям
        return wikiResponse.query.search;
    }
}