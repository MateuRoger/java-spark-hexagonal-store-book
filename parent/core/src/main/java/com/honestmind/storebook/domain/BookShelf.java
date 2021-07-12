package com.honestmind.storebook.domain;

import com.honestmind.storebook.domain.stream.BookShelfStream;

public record BookShelf(String id, String localization, BookCategory category) {

  public static BookShelfStream createsNewBookShelfWithTheId(final String id) {
    return new BookShelfStream(id);
  }
}
