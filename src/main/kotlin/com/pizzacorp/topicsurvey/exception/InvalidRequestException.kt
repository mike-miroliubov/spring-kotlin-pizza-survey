package com.pizzacorp.topicsurvey.exception

class InvalidRequestException(message: String, cause: Throwable?) : RuntimeException(message, cause) {
    constructor(message: String) : this(message, null)
}