import java.awt.Desktop;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите поисковый запрос: ");
            String query = scanner.nextLine();

            WikiSearcher searcher = new WikiSearcher();
            List<SearchResult> results = searcher.search(query);

            if (results.isEmpty()) {
                System.out.println("Ничего не найдено.");
                return;
            }

            ResultDisplayer.displayResults(results);

            System.out.print("\nВведите номер статьи для открытия: (1 - " + results.size() + "): ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > results.size()) {
                System.out.println("Некорректный выбор.");
                return;
            }

            ArticleOpener.openArticle(results.get(choice - 1));

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}