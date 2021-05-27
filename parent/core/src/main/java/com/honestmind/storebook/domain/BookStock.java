package com.honestmind.storebook.domain;

import com.honestmind.storebook.domain.BookLot.BookLotStream;
import com.honestmind.storebook.port.out.BookWriter;

public record BookStock(int quantity, Book book) {

  public void storeUsing(final BookWriter bookWriter) {
    bookWriter.store(this);
  }

  public static class BookStockStream {

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
}
