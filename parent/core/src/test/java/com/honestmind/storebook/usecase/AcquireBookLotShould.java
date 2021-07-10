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
import com.honestmind.storebook.utils.TestFluent;
import java.util.function.BiConsumer;
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

    new TestFluent<BookLot>()
        .given(correctBookLot)
        .when(this::acquireABookLot)
        .then(this::allTheBooksAreStored);
  }

  @Test
  @DisplayName("auto assign a bookshelf for each book according the type of the book when we acquire a new Book lot")
  void autoAssignABookshelfWhenAcquireNewBookLot() {
    var correctBookLot = correctBookLot();

    new TestFluent<BookLot>()
        .given(correctBookLot)
        .when(this::acquireABookLot)
        .then(this::allTheBooksHasTheCorrespondingBookShelf);
  }

  private BookLot acquireABookLot(final BookLot bookLot) {
    final var bookLotCaptor = ArgumentCaptor.forClass(BookStock.class);
    doNothing().when(this.bookWriterMocked).store(bookLotCaptor.capture());

    acquireBookLot.acquire(bookLot);
    return bookLot.cloneWithTheBooks(bookLotCaptor.getAllValues());
  }

  private void allTheBooksAreStored(final BookLot savedBookLot, final BookLot expectedBookLot) {
    assertThat(savedBookLot).isEqualTo(expectedBookLot);
  }

  private void allTheBooksHasTheCorrespondingBookShelf(final BookLot savedBookLot,
      final BookLot expectedBookLot) {
    assertThat(savedBookLot).isEqualTo(expectedBookLot);
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
}