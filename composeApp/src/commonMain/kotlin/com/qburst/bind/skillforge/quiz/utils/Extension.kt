package com.qburst.bind.skillforge.quiz.utils

fun String.toThrowable(): Throwable {
    return Exception(this)
}