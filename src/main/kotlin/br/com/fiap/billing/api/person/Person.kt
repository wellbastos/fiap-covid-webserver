package br.com.fiap.billing.api.person

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Person (

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank
        var name: String = "",

        @get: NotBlank
        var doc: String = "",

        @get: NotBlank
        var city: String = "",

        @get: NotBlank
        var state: String = "",

        var jobType: JobType?,

        var active: Boolean = true

)