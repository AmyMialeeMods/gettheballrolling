package dev.amymialee.gettheballrolling.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.state.SulfurCubeRenderState;
import org.joml.Quaternionf;
import org.jspecify.annotations.NonNull;

public interface QuatfRotatableState {
    Quaternionf gettheballrolling$getRotation();
    void gettheballrolling$setRotation(Quaternionf rotation);

    default void rotate(@NonNull SulfurCubeRenderState state, @NonNull PoseStack poseStack) {
        poseStack.translate(0, -state.boundingBoxHeight / 2, 0);
        poseStack.rotate(this.gettheballrolling$getRotation());
        poseStack.translate(0, state.boundingBoxHeight / 2, 0);
    }
}