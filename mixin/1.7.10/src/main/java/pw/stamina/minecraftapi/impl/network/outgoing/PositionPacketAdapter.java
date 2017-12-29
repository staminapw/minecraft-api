/*
 * MIT License
 *
 * Copyright (c) 2017 Stamina Development
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

package pw.stamina.minecraftapi.impl.network.outgoing;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C03PacketPlayer;
import pw.stamina.minecraftapi.network.AbstractPacketAdapter;
import pw.stamina.minecraftapi.network.outgoing.PositionPacket;

final class PositionPacketAdapter extends AbstractPacketAdapter<PositionPacket> implements PositionPacket.Adapter {
    private static final double DEFAULT_STANCE = 1.62;

    PositionPacketAdapter() {
        super(PositionPacket.class);
    }

    @Override
    public PositionPacket create(double x, double y, double z, boolean onGround) {
        return (PositionPacket) new C03PacketPlayer.C04PacketPlayerPosition(x, y, y + getStance(), z, onGround);
    }

    static double getStance() {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if (player != null) {
            return player.posY - player.boundingBox.minY;
        } else {
            return DEFAULT_STANCE;
        }
    }
}