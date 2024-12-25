package boomty.utilityexpansion.client;

import boomty.utilityexpansion.UtilityExpansion;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

public final class InitializeKeys {
    public static KeyMapping toggleVisor;

    private InitializeKeys() {}

    public static void init() {
        toggleVisor = registerKey("visor", KeyConflictContext.IN_GAME,
                InputConstants.getKey(InputConstants.KEY_V, -1), KeyMapping.CATEGORY_GAMEPLAY);
    }

    private static KeyMapping registerKey(String name, KeyConflictContext context, Key keybind, String category) {
        final var key = new KeyMapping("key." + UtilityExpansion.MOD_ID + "." + name, context, keybind, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
