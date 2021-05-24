package com.honestmind.storebook.usecase;

import com.honestmind.storebook.domain.BookLot;
import com.honestmind.storebook.port.out.BookWriter;

public final class AcquireBookLotUseCase {

  private final BookWriter bookWriter;

  public AcquireBookLotUseCase(final BookWriter bookWriter) {
    this.bookWriter = bookWriter;
  }

  public void performsWith(final BookLot givenBookLot) {
    givenBookLot.saveAllBooksUsing(bookWriter);
  }
}
