package com.pizzacorp.topicsurvey.controller

import com.pizzacorp.topicsurvey.vo.SurveyVO
import com.pizzacorp.topicsurvey.service.SurveyService
import com.pizzacorp.topicsurvey.vo.ToppingCountVO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/survey")
class SurveyController(val surveyService: SurveyService) {
    @GetMapping
    fun getResponses(): Iterable<SurveyVO> = surveyService.listRespondents().map {
        SurveyVO(it.email, it.toppingChoices.map { t -> t.name })
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun saveResponse(@RequestBody survey: SurveyVO) = surveyService.submitToppings(survey.email, survey.toppings)

    @GetMapping("/results")
    fun getCounts(): Iterable<ToppingCountVO> = surveyService.getCounts()

    @GetMapping("/authors_choice")
    fun authorsFavorite(): SurveyVO = SurveyVO("mike.miroliubov@gmail.com", listOf("ham", "cheese", "pineapple"))
}