package com.cpl.micronaut.genre


import com.cpl.micronaut.ListingArguments
import com.cpl.micronaut.domain.Genre
import java.util.Optional
import javax.validation.constraints.NotBlank

interface GenreRepository {

    fun findById(id: Long): Optional<Genre>

    fun save(@NotBlank name: String): Genre

    fun deleteById(id: Long)

    fun findAll(args: ListingArguments): List<Genre>

    fun update(id: Long, @NotBlank name: String): Int
}