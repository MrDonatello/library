package com.sinitsyn.library.service;

import com.sinitsyn.library.exceptions.ApiError;
import com.sinitsyn.library.exceptions.ErrorCode;
import com.sinitsyn.library.exceptions.ServiceException;
import com.sinitsyn.library.model.Book;
import com.sinitsyn.library.model.Genre;
import com.sinitsyn.library.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final GenreService genreService;


    public BookService(BookRepository bookRepository, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
    }

    public Book addBook(Book book) throws ServiceException {
        if (validate(book)) {
            try {
                return bookRepository.save(book);
            } catch (RuntimeException e) {
                ArrayList<ApiError> errors = new ArrayList<>();
                ApiError apiError = new ApiError(ErrorCode.ERROR_ADD_TO_DATABASE.name(), "AddBookMethod", e.getCause().getMessage());
                errors.add(apiError);
                throw new ServiceException(errors);

            }


        } else return null;
    }

    public Book updateBook(Book bookFromDataBase, Book updatedBook) {//validate
        BeanUtils.copyProperties(updatedBook, bookFromDataBase, "id");
        return bookRepository.save(bookFromDataBase);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }

    public Book findBookById(Book book) throws ServiceException { //validate
        try {
            return bookRepository.findById(book.getId()).orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            ArrayList<ApiError> errors = new ArrayList<>();
            ApiError apiError = new ApiError(ErrorCode.ERROR_FIND_OBJECT_TO_DATABASE.name(), "findBookByIdMethod", e.getCause().getMessage());
            errors.add(apiError);
            throw new ServiceException(errors);
        }

    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }


    private boolean validate(Book book) {

        if (book.getGenre() != null && !genreService.isContainsTitle(book.getGenre())) {
            Genre genre = new Genre();
            genre.setTitle(book.getGenre());
            genreService.addGenre(genre);
        }
        return book.getTitle() != null && book.getYearOfPublishing() != 0 && book.getIsbn() != null;
    }
}
