package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Books;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.BooksDTO;
import com.taskease.college.Repository.BookRepo;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;

    public BookServiceImpl(BookRepo bookRepo, ModelMapper modelMapper, UserRepo userRepo) {
        this.bookRepo = bookRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    @Override
    public BooksDTO createBook(BooksDTO booksDTO , long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        Books books = this.modelMapper.map(booksDTO, Books.class);
        books.setUser(user);
        Books book = this.bookRepo.save(books);
        return this.modelMapper.map(book, BooksDTO.class);
    }

    @Override
    public List<BooksDTO> getAllBooks() {
        List<BooksDTO> booksDTOS = this.bookRepo.findAll().stream().map(book -> this.modelMapper.map(book, BooksDTO.class)).toList();
        return booksDTOS;
    }

    @Override
    public BooksDTO getBook(long bookId) {
        Books book = this.bookRepo.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book","Id",bookId));
        return this.modelMapper.map(book, BooksDTO.class);
    }

    @Override
    public BooksDTO updateBook(long bookId, BooksDTO booksDTO) {
        Books book = this.bookRepo.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book","Id",bookId));
        book.setTitle(booksDTO.getTitle());
        book.setDescription(booksDTO.getDescription());
        book.setAuthor(booksDTO.getAuthor());
        book.setPublisher(booksDTO.getPublisher());
        book.setBookLink(booksDTO.getBookLink());
        Books updatedBook = this.bookRepo.save(book);
        return this.modelMapper.map(updatedBook, BooksDTO.class);
    }

    @Override
    public void deleteBook(long bookId) {
        Books book = this.bookRepo.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book","Id",bookId));
        this.bookRepo.delete(book);
    }
}
