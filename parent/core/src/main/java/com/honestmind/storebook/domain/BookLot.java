package com.honestmind.storebook.domain;

import com.honestmind.storebook.domain.stream.BookLotStream;
import com.honestmind.storebook.port.out.BookWriter;
import java.util.List;

public record BookLot(List<BookStock> bookStocks) {

  public static BookLotStream givenAListOfBookStocks() {
    return new BookLotStream();
  }

  public void storeAllBooksUsing(final BookWriter bookWriter) {
    this.bookStocks.forEach(bookStock -> bookStock.storeUsing(bookWriter));
  }

  public BookLot cloneWithTheBooks(final List<BookStock> bookStocks) {
    return new BookLot(bookStocks);
  }
}



