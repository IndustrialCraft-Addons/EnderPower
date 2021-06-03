package com.nofrfa.enderpower.world.gener;

import com.nofrfa.enderpower.misc.registr.BlocksRegister;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GeneratorOres implements IWorldGenerator {
    private final WorldGenerator neifritOre;

    public GeneratorOres() {
        neifritOre = new WorldGenMinable(BlocksRegister.NeifritOre.getDefaultState(),  6, BlockMatcher.forBlock(Blocks.END_STONE));
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() != 1) {
            return;
        }
        runGenerator(neifritOre, world, random, chunkX, chunkZ, 15, 0, 256);
    }

    private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
        if (minHeight > maxHeight || minHeight < 0 || maxHeight > 256) {
            throw new IllegalArgumentException("Ore generated out of bounds");
        }

        int heightDiff = maxHeight - minHeight + 1;

        for (int i = 0; i < chance; i++) {
            int x = chunkX * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunkZ * 16 + rand.nextInt(16);

            gen.generate(world, rand, new BlockPos(x, y, z));
        }
    }
}
