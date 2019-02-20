package ru.antonioplay

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties

class SelectBuilder<T : Any>(vararg columns: KProperty1<T, *>, distinct: Boolean = false) : QueryBuilder() {

    constructor(clazz: KClass<T>, distinct: Boolean = false) : this(
        *clazz.declaredMemberProperties.toTypedArray(),
        distinct = distinct
    )

    init {
        sql += columns.joinToString(prefix = "SELECT ${if (distinct) "DISTINCT" else ""} (", postfix = ")") { it.name }
    }

    infix fun from(builder: () -> QueryBuilder): FromBuilder {
        sql += " FROM (${builder().sql})"
        return FromBuilder(this)
    }

    infix fun from(str: String): FromBuilder {
        sql += " FROM $str"
        return FromBuilder(this)
    }

    infix fun <T : Any> from(clazz: KClass<T>): FromBuilder {
        sql += " FROM ${clazz.table()}"
        return FromBuilder(this)
    }
}