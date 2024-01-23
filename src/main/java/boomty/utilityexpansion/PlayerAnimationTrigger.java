package boomty.utilityexpansion;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.client.player.AbstractClientPlayer;
import boomty.utilityexpansion.registry.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class PlayerAnimationTrigger {
    private static boolean isScutumEquipped = false;
    @SubscribeEvent
    public static void onScutumUsed(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getPlayer();
        ItemStack offhandItem = player.getOffhandItem();
        if (offhandItem.getItem() == ItemRegistry.scutum.get() && !isScutumEquipped) {
            isScutumEquipped = true;

            //Get the animation for that player
            var animation = (ModifierLayer<IAnimation>) PlayerAnimationAccess.getPlayerAssociatedData((AbstractClientPlayer) player).get(new ResourceLocation(UtilityExpansion.MOD_ID, "animation"));
            if (animation != null) {
                //You can set an animation from anywhere ON THE CLIENT
                //Do not attempt to do this on a server, that will only fail

                animation.setAnimation(new KeyframeAnimationPlayer(PlayerAnimationRegistry.getAnimation(new ResourceLocation("utilityexpansion", "scutum_stance"))));
                //You might use  animation.replaceAnimationWithFade(); to create fade effect instead of sudden change
                //See javadoc for details
            }
        }
    }
}
