package com.egecius.electriccars.repository

data class Result<T> (val data : T?, val error: Throwable?)