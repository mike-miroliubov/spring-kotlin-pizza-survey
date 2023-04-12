package com.pizzacorp.topicsurvey.service

import com.pizzacorp.topicsurvey.dao.RespondentRepository
import com.pizzacorp.topicsurvey.dao.ToppingChoiceRepository
import com.pizzacorp.topicsurvey.entity.Respondent
import com.pizzacorp.topicsurvey.entity.ToppingChoice
import com.pizzacorp.topicsurvey.exception.InvalidRequestException
import com.pizzacorp.topicsurvey.vo.ToppingCountVO
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SurveyService(
    val respondentRepository: RespondentRepository,
    val toppingChoiceRepository: ToppingChoiceRepository
) {
    companion object {
        val log = LoggerFactory.getLogger(SurveyService::class.java)
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    }

    @Transactional
    fun submitToppings(email: String, toppings: Iterable<String>) {
        log.info("submitToppings: [{}], [{}]", email, toppings)

        if (email.isBlank()) {
            throw InvalidRequestException("Invalid request: email cannot be empty")
        }
        if (!email.matches(emailRegex)) {
            throw InvalidRequestException("Invalid request: submitted email is not valid")
        }

        val respondent = respondentRepository.findByEmail(email) ?: respondentRepository.save(Respondent(email))

        toppingChoiceRepository.deleteByRespondentId(respondent.id!!)
        toppingChoiceRepository.saveAll(toppings.map { ToppingChoice(it, respondent) })
    }

    fun listRespondents(): Iterable<Respondent> {
        return respondentRepository.findAllRespondentsWithChoices()
    }

    fun getCounts(): Iterable<ToppingCountVO> = toppingChoiceRepository.countByTopping()
}