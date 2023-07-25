package com.lincon.bookstoremanager.controller;

import com.lincon.bookstoremanager.dto.BookDTO;
import com.lincon.bookstoremanager.dto.MessageResponseDTO;
import com.lincon.bookstoremanager.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.lincon.bookstoremanager.utils.BookUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    private static final String BOOKS_API_URL_PATH = "/api/v1/books";

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    void testWhenPostIsCalledThenABookShouldCreated() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(1L);

        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Book created with ID " + bookDTO.getId())
                .build();

        when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(BOOKS_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(expectedMessageResponse.getMessage()));
    }

}
