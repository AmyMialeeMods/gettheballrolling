package dev.amymialee.gettheballrolling.mixin;

import dev.amymialee.gettheballrolling.util.QuatfRotatable;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import net.minecraft.world.level.Level;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SulfurCube.class)
public abstract class SulfurCubeMixin extends AbstractCubeMobMixin implements QuatfRotatable {
    public @Unique Quaternionf prevRotation = new Quaternionf().set(0, 0, 0, 1);
    public @Unique Quaternionf rotation = new Quaternionf().set(0, 0, 0, 1);
    public @Unique int idleTime = 0;

    protected SulfurCubeMixin(EntityType<? extends AgeableMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void gettheballrolling$tickTail(CallbackInfo ci) {
        QuatfRotatable.tickQuat((SulfurCube) (Object) this);
    }

    @Override
    public Quaternionf gettheballrolling$getRotation() {
        return this.rotation;
    }

    @Override
    public Quaternionf gettheballrolling$getPrevRotation() {
        return this.prevRotation;
    }

    @Override
    public Quaternionf gettheballrolling$getRotation(float delta) {
        return this.prevRotation.nlerp(this.rotation, delta);
    }

    @Override
    public void gettheballrolling$setRotation(Quaternionf rotation) {
        this.rotation.set(rotation);
    }

    @Override
    public void gettheballrolling$setPrevRotation(Quaternionf rotation) {
        this.prevRotation.set(rotation);
    }

    @Override
    public int gettheballrolling$getIdleTime() {
        return this.idleTime;
    }

    @Override
    public void gettheballrolling$setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }
}