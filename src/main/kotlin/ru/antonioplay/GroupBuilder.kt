package ru.antonioplay

class GroupBuilder(builder: QueryBuilder) : QueryBuilder(builder) {

    infix fun having(builder: () -> Condition): ConditionBuilder {
        sql += " HAVING (${builder().sql})"
        return ConditionBuilder(this)
    }

}