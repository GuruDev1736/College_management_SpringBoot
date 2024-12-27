package com.taskease.college.Service;

import com.taskease.college.PayLoad.BooksDTO;

import java.awt.print.Book;
import java.util.List;

public interface BookService {

    BooksDTO createBook(BooksDTO booksDTO , long userId);
    List<BooksDTO> getAllBooks();
    BooksDTO getBook(long bookId);
    BooksDTO updateBook(long bookId, BooksDTO booksDTO);
    void deleteBook(long bookId);
}
