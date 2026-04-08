package dev.amymialee.gettheballrolling.mixin;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractCubeMob.class)
public abstract class AbstractCubeMobMixin extends AgeableMob {
    protected AbstractCubeMobMixin(EntityType<? extends AgeableMob> type, Level level) {
        super(type, level);
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    protected void gettheballrolling$tickTail(CallbackInfo ci) {}
}