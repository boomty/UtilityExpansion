package boomty.utilityexpansion;

import boomty.utilityexpansion.client.renderer.armor.*;
import boomty.utilityexpansion.item.ArmorItems.BodyArmor.LoricaSegmentata;
import boomty.utilityexpansion.item.ArmorItems.HeadArmor.FaceMaskItem;
import boomty.utilityexpansion.item.ArmorItems.HeadArmor.Galea;
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
            GeoArmorRenderer.registerArmorRenderer(FaceMaskItem.class, () -> new FaceMaskRenderer());
            GeoArmorRenderer.registerArmorRenderer(RomanArmorItem.class, () -> new RomanArmorRenderer());
            GeoArmorRenderer.registerArmorRenderer(LoricaSegmentata.class, () -> new LoricaSegmentataRenderer());
            GeoArmorRenderer.registerArmorRenderer(TunicItem.class, () -> new TunicItemRenderer());
            GeoArmorRenderer.registerArmorRenderer(Galea.class, () -> new GaleaTypeHHelmetRenderer());
        }
    }
}
