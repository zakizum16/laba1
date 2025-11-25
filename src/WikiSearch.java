import com.google.gson.Gson;
import java.awt.Desktop;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.google.gson.Gson;
public class WikiSearch {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите поисковый запрос: ");
            String query = scanner.nextLine();
            System.out.println("Запрос получен");
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            System.out.println("Запрос закодирован");
            String urlStr = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=" + encodedQuery;
            URL url = new URL(urlStr);
            System.out.println("Создан url");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Laba1_WikiSearchBot/1.0 (https://example.com)");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Gson gson = new Gson();
            WikiResponse wikiResponse = gson.fromJson(response.toString(), WikiResponse.class);
            List<SearchResult> results = wikiResponse.query.search;

            if (results.isEmpty()) {
                System.out.println("Ничего не найдено.");
                return;
            }

            System.out.println("\nРезультаты поиска:");
            for (int i = 0; i < results.size(); i++) {
                System.out.printf("%d. %s — %s%n", i + 1, results.get(i).title, results.get(i).snippet.replaceAll("<.*?>", ""));
            }

            System.out.print("\nВведите номер статьи для открытия: ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > results.size()) {
                System.out.println("Некорректный выбор.");
                return;
            }

            int pageId = results.get(choice - 1).pageid;
            String articleUrl = "https://ru.wikipedia.org/w/index.php?curid=" + pageId;
            System.out.println("Открываю: " + articleUrl);

            Desktop.getDesktop().browse(new URI(articleUrl));

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
    static class SearchResult {
        int pageid;
        String title;
        String snippet;
    }
}
