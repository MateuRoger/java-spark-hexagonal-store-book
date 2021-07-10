package com.honestmind.storebook.domain.stream;

import com.honestmind.storebook.domain.Book;

public class BookStockStream {

  private final BookLotStream bookLotStreamBeloging;
  private final int quantity;
  private Book book;

  public BookStockStream(final int quantity, final BookLotStream bookLotStream) {
    this.quantity = quantity;
    bookLotStreamBeloging = bookLotStream;
  }

  public int getQuantity() {
    return quantity;
  }

  public Book getBook() {
    return book;
  }

  public BookLotStream unitsOf(final Book book) {
    this.book = book;
    return bookLotStreamBeloging;
  }
}
