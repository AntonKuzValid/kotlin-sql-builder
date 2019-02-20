package ru.antonioplay

import org.junit.Assert
import org.junit.Test

class MainTest {

    @Test
    fun testSimpleSelect() {
        val queryBuilder = SelectBuilder(
            TestEntity::id,
            TestEntity::name,
            distinct = true
        ) from TestEntity::class where {
            TestEntity::id.gt(`?`) and TestEntity::name.gt(`?`)
        } or TestEntity::id.lt(`?`) groupBy TestEntity::name having {
            TestEntity::name.gt(`?`)
        } orderBy (TestEntity::name)

        Assert.assertTrue(queryBuilder.sql == "SELECT DISTINCT (id, name) FROM TestEntity WHERE (id > ?) and")
    }

    @Test
    fun testDiffSelect() {
        val groupBuilder = select(TestEntity::id, TestEntity::male) from {
            select(TestEntity::name) from TestEntity::class
        } groupBy TestEntity::male

        print(groupBuilder.sql)
    }
}