package com.pizzacorp.topicsurvey.controller

import com.pizzacorp.topicsurvey.exception.InvalidRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(InvalidRequestException::class)
    protected fun handleInvalidRequest(ex: InvalidRequestException): ResponseEntity<Any?>? {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }
}