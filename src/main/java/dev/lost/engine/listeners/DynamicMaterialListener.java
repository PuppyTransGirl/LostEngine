package dev.lost.engine.listeners;

import dev.lost.engine.utils.FloodgateUtils;
import it.unimi.dsi.fastutil.ints.Int2ByteOpenHashMap;
import net.minecraft.network.protocol.game.ClientboundSetPlayerInventoryPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.Optional;

public class DynamicMaterialListener {

    static Map<Integer, Byte> helpItemSlotMap = new Int2ByteOpenHashMap();

    public static void setup(JavaPlugin plugin) {
        Bukkit.getGlobalRegionScheduler().runAtFixedRate(plugin, scheduledTask -> {
            Map<Integer, Byte> newMap = new Int2ByteOpenHashMap();
            MinecraftServer.getServer().getPlayerList().players.forEach(player -> {
                if (FloodgateUtils.isBedrockPlayer(player.getUUID())) return;
                if (helpItemSlotMap.containsKey(player.getId())) {
                    int previousSlot = helpItemSlotMap.get(player.getId());
                    int currentSlot = player.getInventory().getSelectedSlot();
                    if (previousSlot != currentSlot) {
                        ItemStack previousItem = player.getInventory().getItem(previousSlot);
                        Optional<ItemStack> newItem = PacketListener.editItem(previousItem, false);
                        if (newItem.isPresent()) {
                            ClientboundSetPlayerInventoryPacket packet = new ClientboundSetPlayerInventoryPacket(previousSlot, newItem.get());
                            player.connection.send(packet);
                        }

                        ItemStack currentItem = player.getInventory().getItem(currentSlot);
                        newItem = PacketListener.editItem(currentItem, true);
                        if (newItem.isPresent()) {
                            ClientboundSetPlayerInventoryPacket packet = new ClientboundSetPlayerInventoryPacket(currentSlot, newItem.get());
                            player.connection.send(packet);
                        }
                    }
                }
                newMap.put(player.getId(), (byte) player.getInventory().getSelectedSlot());
            });
            helpItemSlotMap = newMap;
        }, 1, 1);
    }

}
