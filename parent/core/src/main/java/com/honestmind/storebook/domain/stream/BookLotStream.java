package com.honestmind.storebook.domain.stream;

import com.honestmind.storebook.domain.BookLot;
import com.honestmind.storebook.domain.BookStock;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookLotStream {

  private final List<BookStockStream> bookStockStreams;

  public BookLotStream() {
    this.bookStockStreams = new ArrayList<>();
  }

  public BookStockStream andThatContains(final int quantity) {
    return thatContains(quantity);
  }

  public BookStockStream thatContains(final int quantity) {
    final BookStockStream bookStockStream = new BookStockStream(quantity,
        this);
    this.bookStockStreams.add(bookStockStream);
    return bookStockStream;
  }

  public BookLot createsABookLot() {
    return new BookLot(bookStockStreams.stream()
        .map(bookStockStream -> new BookStock(bookStockStream.getQuantity(),
            bookStockStream.getBook()))
        .collect(Collectors.toList()));
  }
}
