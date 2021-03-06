package ru.antonioplay

import javax.persistence.Column
import javax.persistence.Table
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation

const val `?` = "?"

open class QueryBuilder(var sql: String = "") {

    constructor(builder: QueryBuilder) : this(builder.sql)
}


fun <T> KProperty<T>.eq(param: String) = Condition("${this.name} = $param")
fun <T> KProperty<T>.neq(param: String) = Condition("${this.name} != $param")
fun <T> KProperty<T>.gt(param: String) = Condition("${this.name} > $param")
fun <T> KProperty<T>.ge(param: String) = Condition("${this.name} >= $param")
fun <T> KProperty<T>.lt(param: String) = Condition("${this.name} < $param")
fun <T> KProperty<T>.le(param: String) = Condition("${this.name} <= $param")

fun <T> KProperty<T>.column() = this.findAnnotation<Column>()?.name ?: this.name
fun <T : Any> KClass<T>.table() = this.findAnnotation<Table>()?.let { "${it.schema}.${it.name}" } ?: this.simpleName

fun <T : Any> select(vararg columns: KProperty1<T, *>) = SelectBuilder(*columns, distinct = false)
fun <T : Any> selectDistinct(vararg columns: KProperty1<T, *>) = SelectBuilder(*columns, distinct = true)