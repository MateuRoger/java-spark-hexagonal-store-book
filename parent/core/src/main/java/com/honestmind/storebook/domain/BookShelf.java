package com.honestmind.storebook.domain;

import static com.honestmind.storebook.domain.BookCategory.UNKNOWN;

import com.honestmind.storebook.port.out.BookShelfWriter;

public record BookShelf(String id, String localization, BookCategory category) {

  public static BookShelfStream createsNewBookShelfWithTheId(final String id) {
    return new BookShelfStream(id);
  }

  public void storeUsing(final BookShelfWriter bookShelfWriter) {
    bookShelfWriter.store(this);
  }

  public static class BookShelfStream {

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
}
