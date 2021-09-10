package com.sinitsyn.library.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinitsyn.library.dto.response.BookDtoResponse;
import com.sinitsyn.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
    void getOneBook() {


    }

    @Test
    void addBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void delete() {
    }
}