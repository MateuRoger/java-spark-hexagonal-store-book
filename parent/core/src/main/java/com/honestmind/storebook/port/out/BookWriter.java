package com.honestmind.storebook.port.out;

import com.honestmind.storebook.domain.BookStock;

public interface BookWriter {

  void store(final BookStock bookStock);
}
