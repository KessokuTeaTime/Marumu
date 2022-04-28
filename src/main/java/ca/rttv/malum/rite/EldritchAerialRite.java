package ca.rttv.malum.rite;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;

import java.util.Random;
import java.util.stream.StreamSupport;

import static ca.rttv.malum.registry.MalumRegistry.CORRUPTED_AERIAL_AURA;

public class EldritchAerialRite extends Rite {
    public EldritchAerialRite(Item... items) {
        super(items);
    }

    @Override
    public void onTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {
        if (tick % 60 != 0) {
            return;
        }

        StreamSupport.stream(BlockPos.iterateOutwards(pos.down(), 2, 0, 2).spliterator(), false).filter(fallingPos -> !fallingPos.up().equals(pos)).forEach(fallingPos -> {
            FallingBlockEntity.fall(world, fallingPos, world.getBlockState(fallingPos));
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            // todo, particle
        });
    }

    @Override
    public void onCorruptTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {
        if (tick % 20 != 0) {
            return;
        }

        world.getEntitiesByClass(PlayerEntity.class, new Box(pos.subtract(new Vec3i(2, 2, 2)), pos.add(new Vec3i(2, 2, 2))), player -> !player.isSpectator()).forEach(player -> player.addStatusEffect(new StatusEffectInstance(CORRUPTED_AERIAL_AURA, 100, 40)));
    }
}
