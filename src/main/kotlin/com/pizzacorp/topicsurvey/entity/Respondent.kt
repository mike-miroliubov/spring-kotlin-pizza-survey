package com.pizzacorp.topicsurvey.entity

import jakarta.persistence.*

@Entity
@Table(name = "respondent", schema = "survey")
data class Respondent(
    val email: String,

    @OneToMany(mappedBy = "respondent", fetch = FetchType.EAGER)
    val toppingChoices: List<ToppingChoice> = emptyList(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
