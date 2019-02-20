package ru.antonioplay

import javax.persistence.Column
import javax.persistence.Table

@Table(name = "test", schema = "schema")
class TestEntity(
    val id: Int,
    @Column(name = "full_name")
    val name: String,
    val male: Boolean
)