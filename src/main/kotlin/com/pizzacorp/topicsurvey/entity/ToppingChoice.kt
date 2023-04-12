package com.pizzacorp.topicsurvey.entity

import jakarta.persistence.*

@Entity
@Table(name = "topping_choice", schema = "survey")
data class ToppingChoice(val name: String,
                         @ManyToOne val respondent: Respondent?,
                         @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null)
