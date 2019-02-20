package ru.antonioplay

import org.junit.Assert
import org.junit.Test

class MainTest {

    @Test
    fun testSimpleSelect() {
        val queryBuilder = Select(
            TestEntity::id,
            TestEntity::name,
            distinct = true
        ) from TestEntity::class where (group(TestEntity::id.gt(`?`) and TestEntity::name.gt(`?`))
                or TestEntity::id.lt(`?`))

        Assert.assertTrue(queryBuilder.sql=="SELECT DISTINCT (id, name) FROM TestEntity WHERE (id > ?) and")
    }

    @Test
    fun testSimpleSelect1() {
        val queryBuilder = Select(
            TestEntity::class
        ) from TestEntity::class where (group(TestEntity::id.gt(`?`) and TestEntity::name.gt(`?`))
                or TestEntity::id.lt(`?`))

        Assert.assertTrue(queryBuilder.sql=="SELECT DISTINCT (id, name) FROM TestEntity WHERE (id > ?) and")
    }
}