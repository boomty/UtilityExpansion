package boomty.utilityexpansion.item;

public class RomanShieldItem extends ShieldItem implements IHasModelProperty {
  
  public RomanShieldItem();
  
  @Override
    @OnlyIn(Dist.CLIENT)
    public void registerModelProperty()
    {
        ItemModelsProperties.register(this, new ResourceLocation("blocking"), (p_239421_0_, p_239421_1_, p_239421_2_) ->
                p_239421_2_ != null && p_239421_2_.isUsingItem() && p_239421_2_.getUseItem() == p_239421_0_ ? 1.0F : 0.0F);
    }
}
