package com.lincon.bookstoremanager.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import com.lincon.bookstoremanager.dto.BookDTO;
import com.lincon.bookstoremanager.entity.Book;

import java.net.URI;

public class BookUtils {

    private static final Faker faker = new Faker();

    public static BookDTO createFakeBookDTO() {
        return BookDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 200))
                .isbn("0-569-52069-9")
                .publisherName(faker.book().publisher())
                .author(createFakeBookDTO().getAuthor())
                .build();
    }

    public static Book createFakeBook() {
        return Book.builder()
                .id(faker.number().randomNumber())
                .name(faker.book().title())
                .pages(faker.number().numberBetween(0, 200))
                .chapters(faker.number().numberBetween(1, 200))
                .isbn("0-569-52069-9")
                .publisherName(faker.book().publisher())
                .author(createFakeBook().getAuthor())
                .build();
    }

    public static String asJsonString(Object objectDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(objectDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
