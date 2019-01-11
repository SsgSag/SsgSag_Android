package com.sopt.appjam_sggsag

data class Spot(
        val name: String,
        val category: String,
        val start_date: String,
        val end_date: String,
        val url: String
) {
    companion object {
        private var counter = 0L
    }
}
