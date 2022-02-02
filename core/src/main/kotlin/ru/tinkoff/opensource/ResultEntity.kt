package ru.tinkoff.opensource

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "person")
data class ResultEntity(

    @Id
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "personId")
    val personId: String,

    @Column(name = "result")
    val result: String

)