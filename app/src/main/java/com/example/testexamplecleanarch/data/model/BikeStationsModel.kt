package com.example.testexamplecleanarch.data.model

data class BikeStationsModel(
    val crs: Crs,
    val features: List<Feature>,
    val type: String
) {
    data class Crs(
        val properties: Properties,
        val type: String
    ) {
        data class Properties(
            val code: String
        )
    }

    data class Feature(
        val geometry: Geometry,
        val id: String,
        val properties: Properties,
        val type: String
    ) {
        data class Geometry(
            val coordinates: List<Double>,
            val type: String
        )

        data class Properties(
            val bike_racks: String,
            val bikes: String,
            val free_racks: String,
            val label: String,
            val updated: String
        )
    }
}