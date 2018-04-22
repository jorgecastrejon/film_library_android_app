package com.jcastrejon.filmlibrary.data.models

data class DataResponse(val page: Int,
                        val total_results: Int,
                        val total_pages: Int,
                        val results: List<Result>)

data class Result(val vote_count: Int,
                  val id: Int,
                  val video: Boolean,
                  val vote_average: Float,
                  val title: String,
                  val popularity: Float,
                  val poster_path: String,
                  val original_title: String,
                  val original_language: String,
                  val genre_ids: List<Int>,
                  val backdrop_path: String,
                  val adult: Boolean,
                  val overview: String,
                  val release_date: String)