package com.route.todo_c35_sat.database.model

import java.util.*
data class TodoDTO(
    var id: Int? = null,
    var name: String? = null,
    var details: String? = null,
    var date: Date? = null,
    var isDone: Boolean? = false,

)