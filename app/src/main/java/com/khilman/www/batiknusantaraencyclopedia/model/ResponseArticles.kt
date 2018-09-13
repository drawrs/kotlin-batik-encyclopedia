package com.khilman.www.batiknusantaraencyclopedia.model

import com.google.gson.annotations.SerializedName

data class ResponseArticles(

	@SerializedName("data")
	val data: List<DataItem?>? = null,

	@SerializedName("status")
	val status: Boolean? = null
)