package com.example.crud

import java.io.Serializable

data class DataModel (
    val tb_data: List<Data>
    ) {
        data class Data (val id_hewan: String?, val nama_hewan: String?) : Serializable
}