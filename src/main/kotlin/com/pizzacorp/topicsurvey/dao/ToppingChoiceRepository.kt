package com.pizzacorp.topicsurvey.dao

import com.pizzacorp.topicsurvey.entity.ToppingChoice
import com.pizzacorp.topicsurvey.vo.ToppingCountVO
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ToppingChoiceRepository : CrudRepository<ToppingChoice, Long> {
    @Modifying
    @Query("DELETE FROM ToppingChoice t WHERE t.respondent.id = :respondentId")
    fun deleteByRespondentId(respondentId: Long)

    @Query("SELECT new com.pizzacorp.topicsurvey.vo.ToppingCountVO(t.name, COUNT(1)) " +
            "FROM ToppingChoice t " +
            "GROUP BY t.name ORDER BY COUNT(1) DESC")
    fun countByTopping(): Iterable<ToppingCountVO>
}