/*
 * MIT License
 *
 * Copyright (c) 2018 Stamina Development
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pw.stamina.minecraftapi

import pw.stamina.minecraftapi.module.MinecraftApiModule
import pw.stamina.minecraftapi.module.MinecraftApiModuleLoader
import java.util.concurrent.atomic.AtomicBoolean

object MinecraftApi {
    private val BOOTSTRAPPED = AtomicBoolean()

    private lateinit var adapter: MinecraftApiAdapter
    private lateinit var module: MinecraftApiModule

    fun bootstrap(adapter: MinecraftApiAdapter) {
        if (!BOOTSTRAPPED.compareAndSet(false, true)) {
            throw Error("MinecraftApi has already been bootstrapped")
        }

        this.adapter = adapter

        module = MinecraftApiModuleLoader.loadModule()
        module.bootstrap(adapter)
    }

    fun getAdapter() = adapter

    fun bootstrapModule() {
        module.bootstrap(adapter)
    }

    fun <T> emitEvent(event: T) {
        module.consumeEvent(event)
    }
}
