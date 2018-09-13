package com.khilman.www.batiknusantaraencyclopedia.model

import com.google.gson.annotations.SerializedName

data class DataItem(

	@SerializedName("post_content")
	val postContent: String? = null,

	@SerializedName("created_at")
	val createdAt: String? = null,

	@SerializedName("id")
	val id: String? = null,

	@SerializedName("writer")
	val writer: String? = null,

	@SerializedName("title")
	val title: String? = null,

    @SerializedName("poster")
    val poster: String? = null
)