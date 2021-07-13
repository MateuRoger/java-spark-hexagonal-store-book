package com.honestmind.storebook.domain;

import com.honestmind.storebook.domain.stream.BookShelfStream;
import com.honestmind.storebook.port.out.BookShelfWriter;

public record BookShelf(String id, String localization, BookCategory category) {

  public static BookShelfStream createsNewBookShelfWithTheId(final String id) {
    return new BookShelfStream(id);
  }

  public void storeUsing(final BookShelfWriter bookShelfWriter) {
    bookShelfWriter.store(this);
  }
}
