import java.sql.SQLException;
import java.util.Scanner;

public class BookApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Co chcesz zrobić? Wybierz spośród: dodaj / wyszukaj / zaktualizuj / usuń");
        String choice = scanner.nextLine();
        if ("dodaj".equals(choice)){
            System.out.println("Podaj tytuł");
            String title = scanner.nextLine();
            System.out.println("Podaj autora");
            String author = scanner.nextLine();
            System.out.println("Podaj rok wydania");
            int year = scanner.nextInt();
            System.out.println("Podaj isbn");
            int isbn = scanner.nextInt();

            Book book = new Book(title, author, year, isbn);
            try {
                    BookDao bookDao = new BookDao();
                    bookDao.save(book);
                System.out.println("Dodano pozycję");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if ("wyszukaj".equals(choice)){
            System.out.println("Podaj id");
            int id = scanner.nextInt();
            try {
                BookDao bookDao = new BookDao();
                Book bookFromDb = bookDao.read(id);
                System.out.println("Dane książki: " + bookFromDb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if ("usuń".equals(choice)){
            System.out.println("Podaj id książki do usunięcia");
            int id = scanner.nextInt();
            try{
                BookDao bookDao = new BookDao();
                bookDao.delete(id);
                System.out.println("usunięto pozycję");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Podaj właściwą wartość");
        }
    }
}
