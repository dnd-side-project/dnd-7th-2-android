package com.woowa.data.remote.model.response

import com.woowa.domain.model.Token

internal data class TokenDto(val accessToken: String, val refreshToken: String) {
    fun toToken(): Token = Token(accessToken, refreshToken)
}

