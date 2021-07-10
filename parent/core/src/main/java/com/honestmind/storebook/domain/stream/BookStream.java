package com.honestmind.storebook.domain.stream;

import com.honestmind.storebook.domain.Book;
import com.honestmind.storebook.domain.BookCategory;
import java.util.ArrayList;
import java.util.List;

public class BookStream {

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
