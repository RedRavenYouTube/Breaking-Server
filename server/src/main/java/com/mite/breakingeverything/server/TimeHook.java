package com.mite.breakingeverything.server;

import com.mite.breakingeverything.asm.Hook;

/**
 * TimeHook for MiTE Breaking Everything
 * Modifies the world time advancement to implement MiTE's custom day/night cycle.
 */
public class TimeHook {

    /**
     * Hook for the world tick method to modify time advancement.
     * This hook intercepts the normal time++ and replaces it with MiTE's variable advancement.
     */
    @Hook(targetClass = "net.minecraft.world.World", targetMethod = "tick")
    public static void tick(net.minecraft.world.World world) {
        long currentTime = world.getWorldTime();
        double advancement = TimeManager.getAdvancement(currentTime);
        world.setWorldTime(currentTime + (long) advancement);
    }

    /**
     * Alternative hook for time synchronization.
     * Ensures clients receive the correct MiTE time.
     */
    @Hook(targetClass = "net.minecraft.server.MinecraftServer", targetMethod = "getWorldTime")
    public static long getWorldTime(net.minecraft.server.MinecraftServer server) {
        // Convert server's normal time to MiTE time for client sync
        long normalTime = server.getWorldTime();
        return TimeManager.getMiteTime(normalTime);
    }
}