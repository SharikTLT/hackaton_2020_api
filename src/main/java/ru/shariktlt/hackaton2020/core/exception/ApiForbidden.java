package ru.shariktlt.hackaton2020.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ApiForbidden extends RuntimeException {
}
