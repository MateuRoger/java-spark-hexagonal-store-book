package com.honestmind.storebook.domain;

import com.honestmind.storebook.domain.stream.BookLotStream;
import com.honestmind.storebook.port.out.BookWriter;

public record BookStock(int quantity, Book book) {

  public void storeUsing(final BookWriter bookWriter) {
    bookWriter.store(this);
  }
}
