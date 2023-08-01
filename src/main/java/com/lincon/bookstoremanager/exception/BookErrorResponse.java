package com.lincon.bookstoremanager.exception;

import lombok.*;

@Data
@AllArgsConstructor
public class BookErrorResponse {

        private String error;
        private String message;
}
