package com.taskease.college.Controller;


import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.BooksDTO;
import com.taskease.college.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponse<BooksDTO>> create(@RequestBody BooksDTO booksDTO , @PathVariable long userId) {
        BooksDTO booksDTO1 = this.bookService.createBook(booksDTO, userId);
        return ResponseEntity.ok(new ApiResponse<>("200","Book Added Successfully",booksDTO1));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<BooksDTO>>> getAll() {
        List<BooksDTO> booksDTOS = this.bookService.getAllBooks();
        return ResponseEntity.ok(new ApiResponse<>("200","Books List Fetched Successfully",booksDTOS));
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<ApiResponse<BooksDTO>> update(@RequestBody BooksDTO booksDTO , @PathVariable long bookId) {
        BooksDTO booksDTO1 = this.bookService.updateBook(bookId, booksDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","Book Updated Successfully",booksDTO1));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<ApiResponse<BooksDTO>> get(@PathVariable long bookId) {
        BooksDTO booksDTO = this.bookService.getBook(bookId);
        return ResponseEntity.ok(new ApiResponse<>("200","Book Found",booksDTO));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable long bookId) {
        this.bookService.deleteBook(bookId);
        return ResponseEntity.ok(new ApiResponse<>("200","Book Deleted Successfully",""));
    }
}
