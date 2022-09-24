package com.android.data.remote.ws.scarlet

import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.channels.ReceiveChannel

interface ScarletSocketService {


/*
    // server.js
    const WebSocket = require('ws')
    const wss = new WebSocket.Server(
    { port: 22080 })

    wss.on('connection', ws =>
    {

        ws.send('Hello! Message From Server!!')
        ws.on('message', message => {
            console.log(`Received message => ${message}`)
            ws.send("test")
        })

    })
*/

    @Send
    fun sendData(message: String)

    @Receive
    fun receiveDate(): ReceiveChannel<String>
}