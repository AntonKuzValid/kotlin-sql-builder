package ru.antonioplay

import kotlin.reflect.KProperty1

open class FromBuilder(builder: QueryBuilder) : QueryBuilder(builder) {

    infix fun where(builder: () -> Condition): ConditionBuilder {
        sql += " WHERE (${builder().sql})"
        return ConditionBuilder(this)
    }

    infix fun <T : Any, R> groupBy(columns: List<KProperty1<T, R>>): GroupBuilder {
        sql += columns.joinToString(prefix = " GROUP BY ") { it.column() }
        return GroupBuilder(this)
    }

    infix fun <T : Any, R> groupBy(column: KProperty1<T, R>): GroupBuilder {
        sql += " GROUP BY ${column.column()}"
        return GroupBuilder(this)
    }

    infix fun <T : Any, R> orderBy(columns: List<KProperty1<T, R>>): FromBuilder {
        sql += columns.joinToString(prefix = " ORDER BY ") { it.column() }
        return this
    }

    infix fun <T : Any, R> orderBy(column: KProperty1<T, R>): FromBuilder {
        sql += " ORDER BY ${column.column()}"
        return this
    }

    infix fun limit(m: Int): FromBuilder {
        sql += " LIMIT $m"
        return this
    }
}