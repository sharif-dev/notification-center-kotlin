package edu.sharif.notificationcenter

object NotificationCenter {
    interface NotificationCenterDelegate {
        fun receiveData(msg: Message)
    }

    private val subscribers = Message.IDS.map { mutableListOf<NotificationCenterDelegate>() }

    fun subscribe(subscriber: NotificationCenterDelegate, id: Int) = subscribers[id].add(subscriber)

    fun unSubscribe(subscriber: NotificationCenterDelegate, id: Int) = subscribers[id].remove(subscriber)

    fun notifySubscribers(msg: Message) = subscribers[msg.id].forEach { it.receiveData(msg) }
}
