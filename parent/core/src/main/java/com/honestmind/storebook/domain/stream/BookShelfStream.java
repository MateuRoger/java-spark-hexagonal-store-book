package com.honestmind.storebook.domain.stream;

import static com.honestmind.storebook.domain.BookCategory.UNKNOWN;

import com.honestmind.storebook.domain.BookCategory;
import com.honestmind.storebook.domain.BookShelf;

public class BookShelfStream {

  private final String id;
  private String localization;
  private BookCategory category;

  public BookShelfStream(final String id) {
    this.id = id;
    this.localization = "";
    this.category = UNKNOWN;
  }

  public BookShelfStream localizedAt(final String localization) {
    this.localization = localization;
    return this;
  }

  public BookShelfStream withBooksOfType(final BookCategory category) {
    this.category = category;
    return this;
  }

  public BookShelf obtain() {
    return new BookShelf(id, localization, category);
  }
}
