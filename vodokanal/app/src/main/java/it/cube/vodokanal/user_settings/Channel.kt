package it.cube.vodokanal.user_settings

data class Channel(
    var name: String,
    var topic: String,
    var unitsMeasures: String,
    var maxValue: Double,
    var lastMessage: String,
    var viewType: Int,
    var color: String,
    )
