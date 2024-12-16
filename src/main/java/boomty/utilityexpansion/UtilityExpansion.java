package boomty.utilityexpansion;

import boomty.utilityexpansion.client.CuriosLayerDefinition;
import boomty.utilityexpansion.client.model.armor.curios.FaceMaskModelCurios;
import boomty.utilityexpansion.client.model.armor.curios.LoricaSegmentataModel;
import boomty.utilityexpansion.client.model.armor.curios.TunicUpperModel;
import boomty.utilityexpansion.client.renderer.armor.curios.FaceMaskRendererCurios;
import boomty.utilityexpansion.client.renderer.armor.curios.LoricaSegmentataRenderer;
import boomty.utilityexpansion.client.renderer.armor.curios.TunicUpperRenderer;
import boomty.utilityexpansion.packets.PacketHandler;
import boomty.utilityexpansion.registry.ItemRegistry;
import boomty.utilityexpansion.util.ModItemProperties;
import com.mojang.logging.LogUtils;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.example.GeckoLibMod;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.stream.Collectors;

@Mod("utilityexpansion")
public class UtilityExpansion {
    public static CreativeModeTab utilexpanseitemgroup;
    public static final String MOD_ID = "utilityexpansion";

    public static boolean DISABLE_IN_DEV = false;

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public UtilityExpansion() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemRegistry.register(eventBus);

        eventBus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::registerLayers);

        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();

        utilexpanseitemgroup = new CreativeModeTab(CreativeModeTab.getGroupCountSafe(), "UtilityExpansion") {
            @Override
            public ItemStack makeIcon() {
                return new ItemStack(ItemRegistry.gladius.get());
            }
        };

    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        event.enqueueWork(PacketHandler::init);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(ItemRegistry.lorica_segmentata.get(), LoricaSegmentataRenderer::new);
        CuriosRendererRegistry.register(ItemRegistry.tunic.get(), TunicUpperRenderer::new);
        CuriosRendererRegistry.register(ItemRegistry.face_mask.get(), FaceMaskRendererCurios::new);

        ModItemProperties.addCustomItemProperties();
        PlayerAnimationFactory.ANIMATION_DATA_FACTORY.registerFactory(
                new ResourceLocation(MOD_ID, "animation"),
                1,
                UtilityExpansion::registerPlayerAnimation);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("utilityexpansion", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.BODY.getMessageBuilder().build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.HEAD.getMessageBuilder().build());
    }

    private void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

    private void registerLayers(final EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(CuriosLayerDefinition.LORICA_SEGMENTATA, LoricaSegmentataModel::createBodyLayer);
        evt.registerLayerDefinition(CuriosLayerDefinition.TUNIC, TunicUpperModel::createBodyLayer);
        evt.registerLayerDefinition(CuriosLayerDefinition.FACE_MASK, FaceMaskModelCurios::createLayer);
    }

    private static IAnimation registerPlayerAnimation(AbstractClientPlayer player) {
        //This will be invoked for every new player
        return new ModifierLayer<>();
    }
}
