package edu.sharif.notificationcenter

class Message(val id: Int, val content: String, val callback: () -> Unit) {
    companion object {
        val IDS = listOf(0, 1, 2)
    }
}
