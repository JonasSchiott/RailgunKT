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

package me.argraur.railgun

import me.argraur.railgun.utils.Config
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.utils.Compression

class Railgun {
    private val token: String = "token"
    private val activity: String = "activity"
    private val discord: JDA
    private val config: Config = Config()

    private fun configure(): JDABuilder {
        return JDABuilder
            .createDefault(config.getValue(token))
            .setCompression(Compression.NONE)
            .setActivity(Activity.playing(config.getValue(activity)))
    }

    init {
        discord = configure().build()
    }
}

fun main() {
    Railgun()
}