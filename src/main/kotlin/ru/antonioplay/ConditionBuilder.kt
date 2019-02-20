package ru.antonioplay

class ConditionBuilder(builder: QueryBuilder) : FromBuilder(builder) {

    infix fun and(condition: Condition): ConditionBuilder {
        sql += " and (${condition.sql})"
        return this
    }

    infix fun or(condition: Condition): ConditionBuilder {
        sql += " or (${condition.sql})"
        return this
    }
}