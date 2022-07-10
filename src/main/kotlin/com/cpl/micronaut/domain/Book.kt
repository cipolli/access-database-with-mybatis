package com.cpl.micronaut.domain

import io.micronaut.core.annotation.Introspected

@Introspected
data class Book(
    var isbn: String,
    var name: String,
    var genre: Genre?) {

    var id: Long? = null

    override fun toString() = "Book{id=$id, name='$name', isbn='$isbn', genre=$genre}"
}