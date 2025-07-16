package com.qburst.bind.skillforge.quiz.data.mapper

interface Mapper<To, From> {
    fun transform(entity: From): To
    fun transformToList(entity: From): List<To>
}