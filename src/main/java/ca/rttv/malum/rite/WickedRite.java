package ca.rttv.malum.rite;

import ca.rttv.malum.registry.MalumDamageSourceRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.Random;

public class WickedRite extends Rite {
    public WickedRite(Item... items) {
        super(items);
    }

    @Override
    public void onTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {
        if (tick % 20 != 0) {
            return;
        }

        world.getEntitiesByClass(LivingEntity.class, new Box(pos.add(-8, -8, -8), pos.add(8, 8, 8)), entity -> entity.getHealth() >= 2.5f && (!(entity instanceof PlayerEntity playerEntity) || !playerEntity.getAbilities().creativeMode)).forEach(entity -> entity.damage(MalumDamageSourceRegistry.VOODOO, 2.0f));
    }

    @Override
    public void onCorruptTick(BlockState state, ServerWorld world, BlockPos pos, Random random, long tick) {
        if (tick % 20 != 0) {
            return;
        }

        world.getEntitiesByClass(LivingEntity.class, new Box(pos.add(-8, -8, -8), pos.add(8, 8, 8)), entity -> entity.getHealth() <= 2.5f && (!(entity instanceof PlayerEntity playerEntity) || !playerEntity.getAbilities().creativeMode)).forEach(entity -> entity.damage(MalumDamageSourceRegistry.FORCED_SHATTER, 10.0f));
    }
}
