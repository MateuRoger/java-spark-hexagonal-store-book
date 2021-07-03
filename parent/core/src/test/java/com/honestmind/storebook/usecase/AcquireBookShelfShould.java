package com.honestmind.storebook.usecase;

import static com.honestmind.storebook.domain.BookShelf.createsNewBookShelfWithTheId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import com.honestmind.storebook.domain.BookCategory;
import com.honestmind.storebook.domain.BookShelf;
import com.honestmind.storebook.port.out.BookShelfWriter;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AcquireBookShelfShould {

  private AcquireBookshelf acquireBookshelf;

  @Mock
  private BookShelfWriter bookShelfWriter;

  @BeforeEach
  void setUp() {
    this.acquireBookshelf = new AcquireBookshelf(bookShelfWriter);
  }

  @Test
  @DisplayName("add the book shelf and assign a category of books")
  void addsBookShelfAndCAtegorizeItWhenAllIsRight() {
    var correctBookShelf = correctBookShelf();

    new BookShelfTest()
        .given(correctBookShelf)
        .when(this::BuyAndInstallABookShelf)
        .then(theBookShelfWillStored(correctBookShelf));
  }

  private BookShelf BuyAndInstallABookShelf(final BookShelf bookShelf) {
    final var bookShelfCaptor = ArgumentCaptor.forClass(BookShelf.class);
    doNothing().when(this.bookShelfWriter).store(bookShelfCaptor.capture());

    acquireBookshelf.acquire(bookShelf);
    return bookShelfCaptor.getValue();
  }

  private Consumer<BookShelf> theBookShelfWillStored(final BookShelf correctBookShelf) {
    return savedBookShelf -> assertThat(savedBookShelf).isEqualTo(correctBookShelf);
  }

  private BookShelf correctBookShelf() {
    return createsNewBookShelfWithTheId("RomanticBookShelf")
        .localizedAt("Bookshelf that contains Romantic books")
        .withBooksOfType(BookCategory.ROMANTIC)
        .obtain();

  }


  private static class BookShelfTest {

    private BookShelf bookLot;
    private UnaryOperator<BookShelf> when;

    public BookShelfTest given(final BookShelf bookLot) {
      this.bookLot = bookLot;
      return this;
    }

    public BookShelfTest when(final UnaryOperator<BookShelf> when) {
      this.when = when;
      return this;
    }

    public void then(final Consumer<BookShelf> then) {
      then.accept(when.apply(bookLot));
    }
  }

}