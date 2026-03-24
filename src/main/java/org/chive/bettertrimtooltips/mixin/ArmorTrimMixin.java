package org.chive.bettertrimtooltips.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.armortrim.ArmorTrim;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.Consumer;


@Mixin(ArmorTrim.class)
public abstract class ArmorTrimMixin {


    @Final
    @Shadow
    private Holder<TrimMaterial> material;

    @Final
    @Shadow
    private Holder<TrimPattern> pattern;

    @Final
    @Shadow
    private boolean showInTooltip;

    /**
     * @author Chive
     * 改其格式、化三行爲一
     * 21.1改爲addToTooltip
    */
    @Inject(method = "addToTooltip",at = @At(value = "HEAD"),cancellable = true)
    private void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> componentConsumer,
                                     TooltipFlag tooltipFlag, CallbackInfo ci) {
        if(showInTooltip){
            Style style = material.value().description().getStyle();
            componentConsumer.accept(Component.empty()
                    .append(pattern.value().description()).withStyle(ChatFormatting.GRAY)
                    .append(Component.translatable("tooltip.bettertrimtooltips.trim_bracket_left").setStyle(style))
                    .append(material.value().description())
                    .append(Component.translatable("tooltip.bettertrimtooltips.trim_bracket_right").setStyle(style)));
            ci.cancel();
        }
        // 元操作
//        if (this.showInTooltip) {
//            componentConsumer.accept(UPGRADE_TITLE);
//            componentConsumer.accept(CommonComponents.space().append(((TrimPattern)this.pattern.value()).copyWithStyle(this.material)));
//            componentConsumer.accept(CommonComponents.space().append(((TrimMaterial)this.material.value()).description()));
//        }
}
}