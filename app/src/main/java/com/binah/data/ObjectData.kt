package com.binah.data

import java.io.Serializable

//Query result
data class ObjectsQueryResult(
        var has_more: Boolean,
        var quota_max: Int,
        var quota_remaining: Int,
        val items: List<ObjectSingleQuestion>
) : Serializable

//Single Item
data class ObjectSingleQuestion(
        var owner: ObjectOwner,
        var score: Int,
        var last_activity_date: Long,
        var creation_date: Long,
        var question_id: Long,
        var content_license: String,
        var tags: List<String>,
        var is_answered: Boolean,
        var view_count: Int,
        var answer_count: Int,
        var link: String,
        var title: String
) : Serializable

//Question owner
data class  ObjectOwner(
        var reputation: Int,
        var user_id: Long,
        var user_type: String,
        var profile_image: String,
        var display_name: String,
        var link: String,

) : Serializable
