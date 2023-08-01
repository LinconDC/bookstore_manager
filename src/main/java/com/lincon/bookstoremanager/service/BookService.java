package com.lincon.bookstoremanager.service;

import com.lincon.bookstoremanager.dto.BookDTO;
import com.lincon.bookstoremanager.dto.MessageResponseDTO;
import com.lincon.bookstoremanager.entity.Book;
import com.lincon.bookstoremanager.exception.BookException;
import com.lincon.bookstoremanager.mapper.BookMapper;
import com.lincon.bookstoremanager.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public MessageResponseDTO create(@Valid BookDTO bookDTO) {

        Book bookToSave = bookMapper.toModel(bookDTO);

        Book saveBook = bookRepository.save(bookToSave);
        return MessageResponseDTO.builder()
                .message("Book created with ID " + saveBook.getId())
                .build();
    }

    public BookDTO findById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        Book book = optionalBook.orElseThrow(() -> new BookException("Livro não encontrado com ID: " + id));
        return bookMapper.toDTO(book);
    }
}
