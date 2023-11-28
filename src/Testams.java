import lt.techin.library.Book;
import lt.techin.library.BookCatalog;
import lt.techin.library.test.AbstractLibraryTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Testams extends AbstractLibraryTest {



    @Override
    protected BookCatalog createBookCatalog() {
        return new BookCatalogImpl();
    }
}
