package com.pizzacorp.topicsurvey.dao

import com.pizzacorp.topicsurvey.entity.Respondent
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RespondentRepository : CrudRepository<Respondent, Long> {
    @Query("SELECT r FROM Respondent r LEFT JOIN FETCH r.toppingChoices")
    fun findAllRespondentsWithChoices(): Iterable<Respondent>

    fun findByEmail(email: String): Respondent?
}