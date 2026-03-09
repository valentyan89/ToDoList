package com.example.todolist

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.todolist.data.local.TodoJsonDataSource
import com.example.todolist.data.repository.TaskRepositoryImpl
import com.example.todolist.domain.usecase.GetTodosUseCase
import com.example.todolist.domain.usecase.ToggleTodoUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetUseCaseTest {
    @Test
    fun getFourCheck() = runBlocking{
        val findAssets = InstrumentationRegistry.getInstrumentation().targetContext

        val dataSource = TodoJsonDataSource(findAssets)
        val repository = TaskRepositoryImpl(dataSource)
        val getTodos = GetTodosUseCase(repository)
        val resultGetTodosUseCase = getTodos.invoke()

        assertEquals(resultGetTodosUseCase.size, 4)
    }
    @Test
    fun toggleCompletedTest() = runBlocking {
        val findAssets = InstrumentationRegistry.getInstrumentation().targetContext

        val dataSource = TodoJsonDataSource(findAssets)
        val repository = TaskRepositoryImpl(dataSource)
        val getTodos = GetTodosUseCase(repository)
        val toggleTodos = ToggleTodoUseCase(repository)

        val start = getTodos.invoke()
        val checkIdForTest = 2
        val completedStart = start.first{it.id == checkIdForTest}.isCompleted
        toggleTodos(checkIdForTest)
        val end = getTodos.invoke()
        val completedEnd = end.first{it.id == checkIdForTest}.isCompleted

        assertNotEquals(completedEnd, completedStart)
    }
}