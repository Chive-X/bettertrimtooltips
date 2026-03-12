package org.chive.bettertrimtooltips.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.armortrim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

import static net.minecraft.world.item.armortrim.ArmorTrim.getTrim;

@Mixin(ArmorTrim.class)
public abstract class ArmorTrimMixin {


    /**
     * @author Chive
     * 改其格式、化三行爲一
    */
    @Inject(method = "appendUpgradeHoverText",at = @At(value = "HEAD"),cancellable = true)
    private static void appendUpgradeHoverText(ItemStack stack, RegistryAccess registryAccess, List<Component> tooltip, CallbackInfo ci) {
    Optional<ArmorTrim> optional = getTrim(registryAccess, stack);
    if (optional.isPresent()) {
        ArmorTrim trim = optional.get();
        // 此三行爲元操作
        // tooltip.add(UPGRADE_TITLE);
        // tooltip.add(CommonComponents.space().append(trim.pattern().value().copyWithStyle(trim.material())));
        // tooltip.add(CommonComponents.space().append(trim.material().value().description()));
        Style style = trim.material().value().description().getStyle();
        tooltip.add(Component.empty()
                .append(trim.pattern().value().description()).withStyle(ChatFormatting.GRAY)
                .append(Component.translatable("tooltip.bettertrimtooltips.trim_bracket_left").setStyle(style))
                .append(trim.material().value().description())
                .append(Component.translatable("tooltip.bettertrimtooltips.trim_bracket_right").setStyle(style)));
        ci.cancel(); // 返回、遶過元操作
    }
}
}