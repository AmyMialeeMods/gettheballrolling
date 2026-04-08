package dev.amymialee.gettheballrolling.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.amymialee.gettheballrolling.util.QuatfRotatable;
import dev.amymialee.gettheballrolling.util.QuatfRotatableState;
import net.minecraft.client.renderer.entity.SulfurCubeRenderer;
import net.minecraft.client.renderer.entity.state.SulfurCubeRenderState;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SulfurCubeRenderer.class)
public abstract class SulfurCubeRendererMixin {
    @Inject(method = "scale(Lnet/minecraft/client/renderer/entity/state/SulfurCubeRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;)V", at = @At(value = "HEAD"))
    private void gettheballrolling$rolling(SulfurCubeRenderState state, PoseStack poseStack, CallbackInfo ci) {
        if (state instanceof QuatfRotatableState quat) quat.rotate(state, poseStack);
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/monster/cubemob/SulfurCube;Lnet/minecraft/client/renderer/entity/state/SulfurCubeRenderState;F)V", at = @At(value = "TAIL"))
    private void gettheballrolling$extractRoll(SulfurCube entity, SulfurCubeRenderState state, float partialTicks, CallbackInfo ci) {
        if (entity instanceof QuatfRotatable rotatable && state instanceof QuatfRotatableState holder) holder.gettheballrolling$setRotation(rotatable.gettheballrolling$getRotation(partialTicks));
    }
}