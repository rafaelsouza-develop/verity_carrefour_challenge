package com.example.githubrepos.domain.mapper

import com.example.githubrepos.data.model.LicenseResponse
import com.example.githubrepos.domain.model.License

fun LicenseResponse.toDomain(): License {
    return License(
        key = this.key,
        name = this.name,
        spdxId = this.spdxId,
        url = this.url,
        nodeId = this.nodeId
    )
}