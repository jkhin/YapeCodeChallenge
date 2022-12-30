package jkcb.dev.labs.yape.test.utils

interface Mapper<I, O> {
    fun map(entry: I): O
}