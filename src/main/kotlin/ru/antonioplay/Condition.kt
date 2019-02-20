package ru.antonioplay


class Condition(sql: String) : QueryBuilder(sql) {

    infix fun and(condition: Condition): Condition {
        sql += " and ${condition.sql}"
        return this
    }

    infix fun or(condition: Condition): Condition {
        sql += " or ${condition.sql}"
        return this
    }
}