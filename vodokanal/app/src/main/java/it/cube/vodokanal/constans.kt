package it.cube.vodokanal

import org.eclipse.paho.client.mqttv3.MqttClient

/**
 * Сохранённые константы, чтобы не путаться с обозначениями в проекте.
 * Только коментите, для чего они и где используются, и объединяйте в группы строк для удобства
 */



//Для выбора способа отображения датчиков в ячейках
val TYPE_LINEAR: Int = 111
val TYPE_COLLUMN: Int = 222
val TYPE_SPEDOMETR : Int = 333


// Для работы с MQTT
val MQTT_BROCKER_CLIENT_ID = MqttClient.generateClientId()
val CLIENT_NAME = "u_NFCWM3"
val CLIENT_PASSWORD = "i63pWpia".toCharArray()
val MQTT_BROCKER_URL = "tcp://broker.hivemq.com:1883"
//"tcp://broker.hivemq.com:1883"
//"https://broker.wqtt.com:14383"