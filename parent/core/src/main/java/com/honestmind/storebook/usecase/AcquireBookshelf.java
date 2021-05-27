package com.honestmind.storebook.usecase;

import com.honestmind.storebook.domain.BookShelf;
import com.honestmind.storebook.port.out.BookShelfWriter;

public final class AcquireBookshelf {

  private final BookShelfWriter bookShelfWriter;

  public AcquireBookshelf(final BookShelfWriter bookShelfWriter) {
    this.bookShelfWriter = bookShelfWriter;
  }

  public void acquire(final BookShelf givenBookShelf) {
    givenBookShelf.storeUsing(bookShelfWriter);
  }
}
