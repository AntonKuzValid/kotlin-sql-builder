package ru.antonioplay

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

class Select<T : Any>(vararg columns: KProperty1<T, *>, distinct: Boolean = false) : QueryBuilder() {

    constructor(clazz: KClass<T>, distinct: Boolean = false) : this(
        *clazz.declaredMemberProperties.toTypedArray(),
        distinct = distinct
    )

    init {
        sql += columns.joinToString(prefix = "SELECT ${if (distinct) "DISTINCT" else ""} (", postfix = ")") { it.name }
    }

    infix fun from(builder: QueryBuilder): QueryBuilder {
        sql += " FROM ${builder.sql}"
        return this
    }

    infix fun from(str: String): QueryBuilder {
        sql += " FROM $str"
        return this
    }

    infix fun <T : Any> from(clazz: KClass<T>): QueryBuilder {
        sql += " FROM ${clazz.table()}"
        return this
    }
}