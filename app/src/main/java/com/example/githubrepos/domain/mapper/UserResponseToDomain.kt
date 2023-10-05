package com.example.githubrepos.domain.mapper

import com.example.githubrepos.data.model.UserResponse
import com.example.githubrepos.domain.model.User


fun UserResponse.toDomain() = User(

    login = this.login,
    id = this.id,
    nodeId = this.nodeId,
    avatarUrl = this.avatarUrl,
    gravatarId = this.gravatarId,
    url = this.url,
    htmlUrl = this.htmlUrl,
    followersUrl = this.followersUrl,
    followingUrl = this.followingUrl,
    gistsUrl = this.gistsUrl,
    starredUrl = this.starredUrl,
    subscriptionsUrl = this.subscriptionsUrl,
    organizationsUrl = this.organizationsUrl,
    reposUrl = this.reposUrl,
    eventsUrl = this.eventsUrl,
    receivedEventsUrl = this.receivedEventsUrl,
    type = this.type,
    siteAdmin = this.siteAdmin

)