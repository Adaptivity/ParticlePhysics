package particlephysics;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import particlephysics.blocks.ControlGlass;
import particlephysics.blocks.Emitter;
import particlephysics.blocks.InfiniteEmitter;
import particlephysics.blocks.PolarizedGlass;
import particlephysics.blocks.SeriesReceptor;
import particlephysics.utils.IBlock;
import cpw.mods.fml.common.registry.GameRegistry;


public class BetterLoader
{

    public static ArrayList<Block> blocks = new ArrayList<Block>();
    public static ArrayList<Class> classes = new ArrayList<Class>();

    public void populateClasses()
    {

        classes.add(Emitter.class);

        classes.add(InfiniteEmitter.class);

        classes.add(PolarizedGlass.class);

        classes.add(SeriesReceptor.class);

        classes.add(ControlGlass.class);

    }

    public void loadBlocks()
    {

        populateClasses();

        try
        {
            for (int i = 0; i < classes.size(); i++)
            {

                Class currentClass = classes.get(i);
                Class clazz = currentClass;
                Block newBlock = ((Block) clazz.newInstance()).setHardness(0.5F).setStepSound(Block.soundAnvilFootstep);
                if (((IBlock) newBlock).inCreativeTab())
                {
                    newBlock.setCreativeTab(ModParticlePhysics.CREATIVE_TAB);
                }
                blocks.add(newBlock);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static Block getBlock(Class clazz)
    {

        for (int i = 0; i < blocks.size(); i++)
        {

            Block currentBlock = blocks.get(i);

            if (clazz.isInstance(currentBlock))
            {
                return currentBlock;
            }
        }
        System.out.println("Failed to find block in BetterLoader. Crash incoming.");
        return null;
    }

    public void mainload()
    {
        System.out.println(blocks);
        for (int i = 0; i < blocks.size(); i++)
        {

            Block currentBlock = blocks.get(i);
            if (currentBlock instanceof IBlock)
            {
                IBlock currentIBlock = (IBlock) currentBlock;
                MinecraftForge.setBlockHarvestLevel(currentBlock, "pickaxe", 0);
                if (currentIBlock.getItemBlock() != null)
                {
                    GameRegistry.registerBlock(currentBlock, currentIBlock.getItemBlock(), currentIBlock.getName());
                }
                else
                {
                    GameRegistry.registerBlock(currentBlock, currentIBlock.getName());
                }
                currentIBlock.addRecipe();

                GameRegistry.registerTileEntity(currentIBlock.getTileEntityClass(), currentIBlock.getName() + "Minechem Tile Entity");
            }

        }
    }

}
