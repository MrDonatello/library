package com.sinitsyn.library.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinitsyn.library.dto.request.BookDto;
import com.sinitsyn.library.dto.response.BookDtoResponse;
import com.sinitsyn.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    BookService bookService;

    private BookDtoResponse book1 = new BookDtoResponse(1L, "title1", 1901, "new", "11-11-11-11-11-11");
    private BookDtoResponse book2 = new BookDtoResponse(2L, "title2", 1902, "new", "22-22-22-22-22-22");
    private BookDtoResponse book3 = new BookDtoResponse(3L, "title3", 1903, "new", "33-33-33-33-33-33");


    @Test
    void getAllBooks() throws Exception {
        List<BookDtoResponse> bookDtoResponses = new ArrayList<>(Arrays.asList(book1, book2, book3));
        List<BookDtoResponse> responses = mapper.convertValue(bookDtoResponses, new TypeReference<List<BookDtoResponse>>() {
        });
        given(bookService.findAll()).willReturn(responses);

        mockMvc.perform(get("/library/book")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].title", is(book1.getTitle())));
    }

    @Test
    void getOneBook() throws Exception {
        BookDtoResponse bookDtoResponse = mapper.convertValue(book2, BookDtoResponse.class);
        given(bookService.findBookById(book2.getId())).willReturn(bookDtoResponse);

        mockMvc.perform(get("/library/book/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.isbn", is(book2.getIsbn())));
    }

    @Test
    void addBook() throws Exception {
        BookDto bookDto = BookDto.builder()
                .title("titleTest")
                .yearOfPublishing(2020)
                .genre("Фольклор")
                .isbn("01-23-45-67-89")
                .build();

        given(bookService.addBook(bookDto)).willReturn(mapper.convertValue(bookDto, BookDtoResponse.class));

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/library/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(bookDto));

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.genre", is(bookDto.getGenre())));

    }

    @Test
    void updateBook() throws Exception {
        BookDto updateBook = BookDto.builder()
                .id(1L)
                .title("Искусственный интеллект и будущее человечества")
                .yearOfPublishing(2019)
                .genre("фантастика")
                .isbn("978-5-04-092-453")
                .build();

        BookDtoResponse bookFromDB = mapper.convertValue(book1, BookDtoResponse.class);
        given(bookService.findBookById(bookFromDB.getId())).willReturn(bookFromDB);
        given(bookService.addBook(updateBook)).willReturn(mapper.convertValue(updateBook, BookDtoResponse.class));


        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/library/book")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updateBook));

        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.title", is(updateBook.getTitle())));
    }

    @Test
    void delete() {



    }
}