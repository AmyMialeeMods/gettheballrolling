package dev.amymialee.gettheballrolling.mixin;

import dev.amymialee.gettheballrolling.util.QuatfRotatableState;
import net.minecraft.client.renderer.entity.state.SulfurCubeRenderState;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SulfurCubeRenderState.class)
public class SulfurCubeRenderStateMixin implements QuatfRotatableState {
    public @Unique Quaternionf rotation = new Quaternionf().set(0, 0, 0, 1);

    @Override
    public Quaternionf gettheballrolling$getRotation() {
        return this.rotation;
    }

    @Override
    public void gettheballrolling$setRotation(Quaternionf rotation) {
        this.rotation.set(rotation);
    }
}