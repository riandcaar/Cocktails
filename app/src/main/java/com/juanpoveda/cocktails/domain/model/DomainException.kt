package com.juanpoveda.cocktails.domain.model

open class DomainException(override val message: String = "") : Throwable(message)
