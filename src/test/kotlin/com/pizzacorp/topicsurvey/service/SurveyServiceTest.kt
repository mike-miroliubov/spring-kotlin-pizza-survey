package com.pizzacorp.topicsurvey.service

import com.pizzacorp.topicsurvey.dao.RespondentRepository
import com.pizzacorp.topicsurvey.dao.ToppingChoiceRepository
import com.pizzacorp.topicsurvey.entity.Respondent
import com.pizzacorp.topicsurvey.entity.ToppingChoice
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.*

@ExtendWith(MockitoExtension::class)
class SurveyServiceTest {
    @Mock
    private lateinit var respondentRepository: RespondentRepository

    @Mock
    private lateinit var toppingChoiceRepository: ToppingChoiceRepository

    @InjectMocks
    private lateinit var surveyService: SurveyService

    @Test
    fun shouldSubmitToppings() {
        // given
        val email = "test@example.com"
        val toppings = listOf("pepperoni", "mushroom")

        val respondent = Respondent(email)
        val createdRespondent = Respondent(email, id = 1)

        val expectedToppingChoices = listOf(
            ToppingChoice("pepperoni", createdRespondent),
            ToppingChoice("mushroom", createdRespondent)
        )

        whenever(respondentRepository.findByEmail(email)).thenReturn(null)
        whenever(respondentRepository.save(respondent)).thenReturn(createdRespondent)

        // when
        surveyService.submitToppings(email, toppings)

        // then
        verify(respondentRepository).findByEmail(email)
        verifyNoMoreInteractions(respondentRepository)
        verify(toppingChoiceRepository).deleteByRespondentId(createdRespondent.id!!)
        verify(toppingChoiceRepository).saveAll(expectedToppingChoices)
        verifyNoMoreInteractions(toppingChoiceRepository)
    }

}