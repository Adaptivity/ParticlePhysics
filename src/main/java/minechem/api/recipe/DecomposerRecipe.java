package minechem.api.recipe;

import minechem.api.core.Chemical;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class DecomposerRecipe
{

    public static ArrayList<DecomposerRecipe> recipes = new ArrayList<DecomposerRecipe>();

    ItemStack input;
    public ArrayList<Chemical> output = new ArrayList<Chemical>();

    public static DecomposerRecipe add(DecomposerRecipe recipe)
    {
        recipes.add(recipe);
        return recipe;
    }

    public DecomposerRecipe(ItemStack input, Chemical... chemicals)
    {
        this(chemicals);
        this.input = input;
    }

    public DecomposerRecipe(Chemical... chemicals)
    {
        for (Chemical chemical : chemicals)
            this.output.add(chemical);
    }

    public ItemStack getInput()
    {
        return this.input;
    }

    public ArrayList<Chemical> getOutput()
    {
        return this.output;
    }

    public ArrayList<Chemical> getOutputRaw()
    {
        return this.output;
    }

    public ArrayList<Chemical> getPartialOutputRaw(int f)
    {
        ArrayList<Chemical> raw = getOutput();
        ArrayList<Chemical> result = new ArrayList<Chemical>();
        if (raw != null)
        {
            for (Chemical chem : raw)
            {
                try
                {
                    Chemical reduced = chem.copy();
                    reduced.amount = (int) Math.floor(chem.amount / f);
                    Random rand = new Random();
                    if (reduced.amount == 0 && rand.nextFloat() > (chem.amount / f))
                    {
                        reduced.amount = 1;
                    }
                    result.add(reduced);

                }
                catch (Exception e)
                {
                    // something has gone wrong
                    // but we do not know quite why
                    // debug code goes here
                }

            }
        }

        return result;
    }
}
