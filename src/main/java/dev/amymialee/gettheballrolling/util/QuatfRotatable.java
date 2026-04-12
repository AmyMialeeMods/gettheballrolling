package dev.amymialee.gettheballrolling.util;

import com.mojang.math.Axis;
import net.minecraft.world.entity.monster.cubemob.SulfurCube;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public interface QuatfRotatable {
    int gettheballrolling$getIdleTime();
    void gettheballrolling$setIdleTime(int idleTime);
    Quaternionf gettheballrolling$getRotation();
    Quaternionf gettheballrolling$getPrevRotation();
    void gettheballrolling$setRotation(Quaternionf rotation);
    void gettheballrolling$setPrevRotation(Quaternionf rotation);

    static void tickQuat(SulfurCube cube) {
        if (!(cube instanceof QuatfRotatable quat)) return;
        quat.gettheballrolling$getPrevRotation().set(quat.gettheballrolling$getRotation());
        if (cube.hasBodyItem()) {
            var direction = new Vec3(cube.xOld - cube.getX(), 0, cube.zOld - cube.getZ());
            if (direction.length() > 0.001) {
                var targetDirection = new Vector3f((float) direction.x, 0, (float) -direction.z);
                targetDirection.rotate(Axis.YP.rotationDegrees(-cube.getYRot()));
                var rotationQuat = new Quaternionf().rotateTo(new Vector3f(0, 1, 0), targetDirection);
                var goal = new Quaternionf().set(rotationQuat).mul(quat.gettheballrolling$getRotation());
                quat.gettheballrolling$getRotation().slerp(goal, (float) direction.length() / 1.5f);
                quat.gettheballrolling$setIdleTime(0);
            } else if (quat.gettheballrolling$getIdleTime() > 120) {
                quat.gettheballrolling$getRotation().slerp(new Quaternionf(), 0.35f);
            } else {
                quat.gettheballrolling$setIdleTime(quat.gettheballrolling$getIdleTime() + 1);
            }
        } else {
            quat.gettheballrolling$getRotation().slerp(new Quaternionf(), 0.35f);
        }
    }

    default Quaternionf gettheballrolling$getRotation(float delta) {
        var temp = new Quaternionf().set(this.gettheballrolling$getPrevRotation());
        return temp.nlerp(this.gettheballrolling$getRotation(), delta);
    }
}