package com.honestmind.storebook.port.out;

import com.honestmind.storebook.domain.BookStock;

public interface BookWriter {

  void save(final BookStock bookStock);
}
