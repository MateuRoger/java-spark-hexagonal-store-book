package com.honestmind.storebook.domain;


import com.honestmind.storebook.port.out.BookWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record BookLot(List<BookStock> bookStocks) {

  public BookLot(final List<BookStock> bookStocks) {
    this.bookStocks = List.copyOf(bookStocks);
  }

  public static BookLotStream givenAListOfBookStocks() {
    return new BookLotStream();
  }

  public void storeAllBooksUsing(final BookWriter bookWriter) {
    this.bookStocks.forEach(bookStock -> bookStock.storeUsing(bookWriter));
  }

  public BookLot cloneWithTheBooks(final List<BookStock> bookStocks) {
    return new BookLot(bookStocks);
  }

  public static class BookLotStream {

    private final List<BookStock.BookStockStream> bookStockStreams;

    BookLotStream() {
      this.bookStockStreams = new ArrayList<>();
    }

    public BookStock.BookStockStream thatContains(final int quantity) {
      final BookStock.BookStockStream bookStockStream = new BookStock.BookStockStream(quantity,
          this);
      this.bookStockStreams.add(bookStockStream);
      return bookStockStream;
    }

    public BookStock.BookStockStream andThatContains(final int quantity) {
      return thatContains(quantity);
    }

    public BookLot createsABookLot() {
      return new BookLot(bookStockStreams.stream()
          .map(bookStockStream -> new BookStock(bookStockStream.getQuantity(),
              bookStockStream.getBook()))
          .collect(Collectors.toList()));
    }
  }
}



