package com.cpl.micronaut.genre

import com.cpl.micronaut.domain.Genre
import jakarta.inject.Singleton
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive
import javax.validation.constraints.PositiveOrZero

@Singleton
open class GenreMapperImpl(private val sqlSessionFactory: SqlSessionFactory) : GenreMapper {

    override fun findById(id: Long): Genre? {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getGenreMapper(sqlSession).findById(id)
        }
    }

    override fun save(genre: Genre) {
        sqlSessionFactory.openSession().use { sqlSession ->
            getGenreMapper(sqlSession).save(genre)
            sqlSession.commit()
        }
    }

    override fun deleteById(id: Long) {
        sqlSessionFactory.openSession().use { sqlSession ->
            getGenreMapper(sqlSession).deleteById(id)
            sqlSession.commit()
        }
    }

    override fun update(id: Long, name: String?) {
        sqlSessionFactory.openSession().use { sqlSession ->
            getGenreMapper(sqlSession).update(id, name)
            sqlSession.commit()
        }
    }

    override fun findAll(): List<Genre> {
        sqlSessionFactory.openSession().use { sqlSession -> return getGenreMapper(sqlSession).findAll() }
    }

    override fun findAllBySortAndOrder(@Pattern(regexp = "id|name") sort: String,
                                       @Pattern(regexp = "asc|ASC|desc|DESC") order: String): List<Genre> {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getGenreMapper(sqlSession).findAllBySortAndOrder(sort, order)
        }
    }

    override fun findAllByOffsetAndMaxAndSortAndOrder(@PositiveOrZero offset: Int,
                                                      @Positive max: Int,
                                                      @Pattern(regexp = "id|name") sort: String,
                                                      @Pattern(regexp = "asc|ASC|desc|DESC") order: String): List<Genre> {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getGenreMapper(sqlSession).findAllByOffsetAndMaxAndSortAndOrder(offset, max, sort, order)
        }
    }

    override fun findAllByOffsetAndMax(@PositiveOrZero offset: Int,
                                       @Positive max: Int): List<Genre> {
        sqlSessionFactory.openSession().use { sqlSession ->
            return getGenreMapper(sqlSession).findAllByOffsetAndMax(offset, max)
        }
    }

    private fun getGenreMapper(sqlSession: SqlSession): GenreMapper {
        return sqlSession.getMapper(GenreMapper::class.java)
    }
}