package ca.rttv.malum.rite;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;

import java.util.Random;

import static ca.rttv.malum.registry.MalumRegistry.SACRED_AURA;

public class SacredRite extends Rite {
    public SacredRite(Item... items) {
        super(items);
    }

    @Override
    public void onTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {
        if (tick % 20 != 0) {
            return;
        }

        world.getEntitiesByClass(PlayerEntity.class, new Box(pos.add(-4, -4, -4), pos.add(4, 4, 4)), player -> !player.isSpectator()).forEach(player -> player.addStatusEffect(new StatusEffectInstance(SACRED_AURA, 200, 0)));
    }

    @Override
    public void onCorruptTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {
        if (tick % 20 != 0) {
            return;
        }

        world.getEntitiesByClass(AnimalEntity.class, new Box(pos.add(-4, -4, -4), pos.add(4, 4, 4)), Entity::isLiving).forEach(entity -> {
            if (world.random.nextFloat() <= 0.04f) {
                if (entity.getBreedingAge() < 0) {
                    // todo, particle
                    entity.setBreedingAge(entity.getBreedingAge() + 25);
                }
            }
        });
    }
}
