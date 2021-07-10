package com.honestmind.storebook.domain;

import com.honestmind.storebook.domain.stream.BookStream;
import java.util.List;

public record Book(String title, String author,
                   BookCategory category,
                   List<String> keywords) {

  public static BookStream book() {
    return new BookStream();
  }
}
