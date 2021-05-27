package com.honestmind.storebook.usecase;

import com.honestmind.storebook.domain.BookLot;
import com.honestmind.storebook.port.out.BookWriter;

public record AcquireBookLot(BookWriter bookWriter) {

  public void acquire(final BookLot givenBookLot) {
    givenBookLot.storeAllBooksUsing(bookWriter);
  }
}
