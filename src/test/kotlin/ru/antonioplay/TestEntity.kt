package ru.antonioplay

import javax.persistence.Table

@Table(name = "test", schema = "schema")
class TestEntity(
    val id: Int,
    val name: String,
    val male: Boolean
)