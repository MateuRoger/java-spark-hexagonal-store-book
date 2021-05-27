package com.honestmind.storebook.usecase;

import static com.honestmind.storebook.domain.Book.book;
import static com.honestmind.storebook.domain.BookCategory.ROMANTIC;
import static com.honestmind.storebook.domain.BookLot.givenAListOfBookStocks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import com.honestmind.storebook.domain.BookCategory;
import com.honestmind.storebook.domain.BookLot;
import com.honestmind.storebook.domain.BookStock;
import com.honestmind.storebook.port.out.BookWriter;
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
class AcquireBookLotShould {

  private AcquireBookLot acquireBookLot;

  @Mock
  private BookWriter bookWriterMocked;

  @BeforeEach
  void setUp() {
    this.acquireBookLot = new AcquireBookLot(bookWriterMocked);
  }

  @Test
  @DisplayName("store the book of the lot in the proper BookShelf when we acquire a new Book lot")
  void storeBooksInProperBookShelfWhenAllIsRight() {
    var correctBookLot = correctBookLot();

    new BookLotTest()
        .given(correctBookLot)
        .when(this::acquireABookLot)
        .then(this::allTheBooksAreStored);
  }

  private BookLot acquireABookLot(final BookLot bookLot) {
    final var bookLotCaptor = ArgumentCaptor.forClass(BookStock.class);
    doNothing().when(this.bookWriterMocked).store(bookLotCaptor.capture());

    acquireBookLot.acquire(bookLot);
    return bookLot.cloneWithTheBooks(bookLotCaptor.getAllValues());
  }

  private Consumer<BookLot> allTheBooksAreStored(final BookLot correctBookLot) {
    return savedBookLot -> assertThat(savedBookLot).isEqualTo(correctBookLot);
  }

  private BookLot correctBookLot() {
    return givenAListOfBookStocks()
        .thatContains(10)
        .unitsOf(book().withTheTitle("Lies Never Dies")
            .beingTypeOf(ROMANTIC)
            .andWroteBy("Arthur Lies")
            .withTheKeyWord("lies")
            .andWithTheKeyWord("dies")
            .obtain())
        .andThatContains(15)
        .unitsOf(book().withTheTitle("Legends never dies")
            .beingTypeOf(BookCategory.ACTION)
            .andWroteBy("League of legends")
            .withTheKeyWord("legends")
            .andWithTheKeyWord("dies")
            .obtain())
        .createsABookLot();
  }


  private static class BookLotTest {

    private BookLot bookLot;
    private UnaryOperator<BookLot> when;

    public BookLotTest given(final BookLot bookLot) {
      this.bookLot = bookLot;
      return this;
    }

    public BookLotTest when(final UnaryOperator<BookLot> when) {
      this.when = when;
      return this;
    }

    public void then(final Consumer<BookLot> then) {
      then.accept(when.apply(bookLot));
    }
  }

}