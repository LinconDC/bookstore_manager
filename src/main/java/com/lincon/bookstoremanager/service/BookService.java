package com.lincon.bookstoremanager.service;

import com.lincon.bookstoremanager.DTO.MessageResponseDTO;
import com.lincon.bookstoremanager.entity.Book;
import com.lincon.bookstoremanager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(Book book) {
        Book saveBook = bookRepository.save(book);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + saveBook.getId())
                .build();
    }
}
