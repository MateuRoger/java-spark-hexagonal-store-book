package com.honestmind.storebook.port.out;

import com.honestmind.storebook.domain.BookShelf;

public interface BookShelfWriter {

  void store(final BookShelf bookShelf);
}
