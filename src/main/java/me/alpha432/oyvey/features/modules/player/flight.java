package com.example.flightmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import org.lwjgl.glfw.GLFW;

public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace", "Makes you throw exp faster", Category.PLAYER, true, false, false);
    }
        
@Override
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleFlightKey.wasPressed()) {
                flightEnabled = !flightEnabled;

                if (client.player != null) {
                    client.player.getAbilities().allowFlying = flightEnabled;
                    client.player.getAbilities().flying = flightEnabled;
                    client.player.sendAbilitiesUpdate();
                    client.player.sendMessage(Text.literal(flightEnabled ? "§aFlight enabled" : "§cFlight disabled"), true);
                }
            }

            if (flightEnabled && client.player != null) {
                // Optional smooth hovering control
                if (!client.player.getAbilities().flying) {
                    client.player.getAbilities().flying = true;
                    client.player.sendAbilitiesUpdate();
                }
            }
        });
    }
}
