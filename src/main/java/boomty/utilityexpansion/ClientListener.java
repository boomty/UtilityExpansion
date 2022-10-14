package boomty.utilityexpansion;

import boomty.utilityexpansion.client.renderer.armor.RomanArmorRenderer;
import boomty.utilityexpansion.client.renderer.armor.TunicItemRenderer;
import boomty.utilityexpansion.item.RomanArmorItem;
import boomty.utilityexpansion.item.TunicItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = utilityexpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        if (!FMLEnvironment.production && !utilityexpansion.DISABLE_IN_DEV) {
            GeoArmorRenderer.registerArmorRenderer(RomanArmorItem.class, () -> new RomanArmorRenderer());
            GeoArmorRenderer.registerArmorRenderer(TunicItem.class, () -> new TunicItemRenderer());
        }
    }
}
