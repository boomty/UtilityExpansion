package boomty.utilityexpansion.mixin;

import boomty.utilityexpansion.item.ArmorTypes.HeadArmor.EnclosedHelmet;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * Makes player head invisible when equipped with special helmets
 */
@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    /*
    Method: setHeadInvisible
    Returns: void
    Purpose: Checks if player is wearing mask, if so make head invisible
    */
    @Inject(method = "setModelProperties", at = @At(value = "INVOKE", target =
            "Lnet/minecraft/client/model/PlayerModel;setAllVisible(Z)V", shift = At.Shift.AFTER, ordinal = 1),
            locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void setPlayerVisiblity(AbstractClientPlayer p_117819_, CallbackInfo ci,
                                    PlayerModel<AbstractClientPlayer> playermodel) {
        Item helmet = p_117819_.getItemBySlot(EquipmentSlot.HEAD).getItem();
//        playermodel.head.visible = false;
//        if (helmet instanceof EnclosedHelmet) {
//            playermodel.head.visible = false;
//        }
    }
}
