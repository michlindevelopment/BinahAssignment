package com.binah.data

//Parameter class for multiple queries, more can be added
class Params {
    companion object {
        fun getParamsSearch(): MutableMap<String, String> {
            val params = mutableMapOf<String, String>()
            params[DefaultData.ORDER] = "desc"
            params[DefaultData.SORT] = "activity"
            params[DefaultData.SITE] = "stackoverflow"
            return params
        }
    }
}