//package boomty.utilityexpansion;
//
//import net.minecraft.world.entity.Mob;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.loading.FMLEnvironment;
//import software.bernie.example.registry.EntityRegistry;
//
//@Mod.EventBusSubscriber(modid = utilityexpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//public class CommonListener {
//    @SubscribeEvent
//    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
//        if (!FMLEnvironment.production && !utilityexpansion.DISABLE_IN_DEV) {
//            event.put(EntityRegistry.BIKE_ENTITY.get(),
//                    Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D).build());
//            event.put(EntityRegistry.GEO_EXAMPLE_ENTITY.get(),
//                    Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D).build());
//            event.put(EntityRegistry.EXTENDED_RENDERER_EXAMPLE.get(),
//                    Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D).build());
//            event.put(EntityRegistry.GEOLAYERENTITY.get(), Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 1.0D)
//                    .add(Attributes.MOVEMENT_SPEED, 0.25f).build());
//        }
//    }
//}
