/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelabs.state.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    // remove the LiveData and  replace it with a mutableStateListOf
    // private var _todoItems = MutableLiveData(listOf<TodoItem>())
    // val todoItems: LiveData<List<TodoItem>> = _todoItems
    // it is better to continue use LiveData
    // if this ViewModel was also used by the View System

    // private state
    private var currentEditPosition by mutableStateOf(-1)

    // state : todoItems
    // is short and captures the same behavior as the LiveData
    var todoItems = mutableStateListOf<TodoItem>()
        private set // restrict writes to this state object

    // state : observe changes to both todoItems and currentEditPosition
    val currentEditItem : TodoItem?
        get() = todoItems.getOrNull(currentEditPosition)

    fun addItem(item: TodoItem) {
//        _todoItems.value = _todoItems.value!! + listOf(item)
        todoItems.add(item)
    }

    fun removeItem(item: TodoItem) {
//        _todoItems.value = _todoItems.value!!.toMutableList().also {
//            it.remove(item)
//        }
        todoItems.remove(item)
        // don't keep the editor open when removing items
        onEditDone()
    }

    // todo item 선택
    fun onEditItemSelected (item : TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    // todo item edit 종료
    fun onEditDone () {
        currentEditPosition = -1
    }

    // todo item 수정
    fun onEditItemChange (item : TodoItem) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }
        todoItems[currentEditPosition] = item
    }
}
