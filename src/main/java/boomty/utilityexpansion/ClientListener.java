package boomty.utilityexpansion;

import boomty.utilityexpansion.client.renderer.armor.*;
import boomty.utilityexpansion.item.armorItems.bodyArmor.LoricaSegmentata;
import boomty.utilityexpansion.item.armorItems.curios.FaceMaskItem;
import boomty.utilityexpansion.item.armorItems.headArmor.ArmetItalian;
import boomty.utilityexpansion.item.armorItems.headArmor.Galea;
import boomty.utilityexpansion.item.RomanArmorItem;
import boomty.utilityexpansion.item.TunicItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = UtilityExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {

    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        if (!FMLEnvironment.production && !UtilityExpansion.DISABLE_IN_DEV) {
            GeoArmorRenderer.registerArmorRenderer(ArmetItalian.class, ArmetItalianRenderer::new);
            GeoArmorRenderer.registerArmorRenderer(FaceMaskItem.class, FaceMaskRenderer::new);
            GeoArmorRenderer.registerArmorRenderer(RomanArmorItem.class, RomanArmorRenderer::new);
            GeoArmorRenderer.registerArmorRenderer(LoricaSegmentata.class, LoricaSegmentataRenderer::new);
            GeoArmorRenderer.registerArmorRenderer(TunicItem.class, TunicItemRenderer::new);
            GeoArmorRenderer.registerArmorRenderer(Galea.class, GaleaTypeHHelmetRenderer::new);
        }
    }
}
