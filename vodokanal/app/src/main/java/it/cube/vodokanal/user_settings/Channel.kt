package it.cube.vodokanal.user_settings

data class Channel(
    var name: String,
    var topic: String,
    var unitsMeasures: String,
    var maxValue: Double,
    var currentValue: Double,
    var viewType: Int,
    var color: String,
    )
