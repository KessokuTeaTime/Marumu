package ca.rttv.malum.rite;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class EarthenRite extends Rite {
    public EarthenRite(Item... items) {
        super(items);
    }

    @Override
    public void onTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {

    }

    @Override
    public void onCorruptTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {

    }
}
