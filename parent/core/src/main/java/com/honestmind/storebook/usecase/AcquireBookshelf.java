package com.honestmind.storebook.usecase;

import com.honestmind.storebook.domain.BookShelf;
import com.honestmind.storebook.port.out.BookShelfWriter;

public record AcquireBookshelf(BookShelfWriter bookShelfWriter) {

  public void acquire(final BookShelf bookShelf) {
    bookShelfWriter.store(bookShelf);
  }
}
