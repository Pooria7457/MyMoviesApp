package com.ebrahimipooria.mymoviesapp

data class Model(var id:Int,var title:String,
var poster:String,var year:String,
var country:String,var imdb_rating:String,
var genres:ArrayList<String>,
var images:ArrayList<String>,
var current_page:String,var per_page:Int,
var page_count:Int,var total_count:Int)

