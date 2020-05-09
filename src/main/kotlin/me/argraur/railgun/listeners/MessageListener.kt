/*
 * Copyright (C) 2020 Arseniy Graur
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.argraur.railgun.listeners

import me.argraur.railgun.Railgun
import me.argraur.railgun.command.Command
import me.argraur.railgun.command.impls.utils.Ping
import me.argraur.railgun.utils.checkLevel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageListener : ListenerAdapter() {
    val commands: LinkedHashMap<String, Command> = LinkedHashMap()

    private fun registerCommand(command: Command) {
        commands[command.getName()] = command
        println("${javaClass.name}: Registered command ${command.getName()}!")
    }

    private fun commandExists(command: String): Boolean {
        if (commands[command] != null)
            return true
        return false
    }

    init {
        registerCommand(Ping())
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message
        val command = Railgun.prefix.filterPrefix(message).split(" ")[0]
        if (commandExists(command))
            if (checkLevel(message, commands[command]!!))
                commands[command]?.exec(message)
            else return
    }
}