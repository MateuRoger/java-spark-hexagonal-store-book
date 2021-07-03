package com.honestmind.storebook.domain;

import java.util.ArrayList;
import java.util.List;

public record Book(String title, String author,
                   BookCategory category,
                   List<String> keywords) {

  public static BookStream book() {
    return new BookStream();
  }

  public static class BookStream {

    private String title;
    private String author;
    private BookCategory category;
    private List<String> keywords;

    public BookStream() {
      this.keywords = new ArrayList<>();
    }

    public BookStream withTheTitle(final String title) {
      this.title = title;
      return this;
    }

    public BookStream andWroteBy(final String author) {
      this.author = author;
      return this;
    }

    public BookStream beingTypeOf(final BookCategory category) {
      this.category = category;
      return this;
    }

    public BookStream andWithTheKeyWord(final String keywords) {
      return this.withTheKeyWord(keywords);
    }

    public BookStream withTheKeyWord(final String keyword) {
      this.keywords.add(keyword);

      return this;
    }

    public Book obtain() {
      return new Book(title, author, category, keywords);
    }
  }
}
