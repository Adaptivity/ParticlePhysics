package pixlepix.minechem.particlephysics.helper;

import java.util.ArrayList;
import java.util.Hashtable;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import pixlepix.minechem.particlephysics.ParticlePhysics;
import pixlepix.minechem.particlephysics.api.BaseParticle;
import pixlepix.minechem.particlephysics.entity.BlankParticle;
import pixlepix.minechem.particlephysics.entity.BlazepowderParticle;
import pixlepix.minechem.particlephysics.entity.CharcoalParticle;
import pixlepix.minechem.particlephysics.entity.ClayParticle;
import pixlepix.minechem.particlephysics.entity.CoalParticle;
import pixlepix.minechem.particlephysics.entity.ConcentratedParticle;
import pixlepix.minechem.particlephysics.entity.GlassParticle;
import pixlepix.minechem.particlephysics.entity.GunpowderParticle;
import pixlepix.minechem.particlephysics.entity.LeafParticle;
import pixlepix.minechem.particlephysics.entity.PaperParticle;
import pixlepix.minechem.particlephysics.entity.SandParticle;
import pixlepix.minechem.particlephysics.entity.SeedParticle;
import pixlepix.minechem.particlephysics.entity.SplitParticle;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ParticleRegistry
{

    public static ArrayList<Class> particles = new ArrayList();

    public static void populateParticleList()
    {
        particles.add(ClayParticle.class);
        particles.add(CoalParticle.class);
        particles.add(ConcentratedParticle.class);
        particles.add(SandParticle.class);
        particles.add(SeedParticle.class);
        particles.add(SplitParticle.class);

        particles.add(CharcoalParticle.class);

        particles.add(GlassParticle.class);

        particles.add(PaperParticle.class);

        particles.add(BlankParticle.class);
        particles.add(GunpowderParticle.class);
        particles.add(BlazepowderParticle.class);
        particles.add(LeafParticle.class);
    }

    public static Hashtable<Class, Icon> icons = new Hashtable<Class, Icon>();

    public static void populateIcons(IconRegister register)
    {

        for (int i = 0; i < particles.size(); i++)
        {
            icons.put(particles.get(i), register.registerIcon("minechem:" + particles.get(i).getName().substring("pixlepix.minechem.particlephysics.entity.".length())));
        }

    }

    public static Icon getIconFromInstance(BaseParticle particle)
    {
        for (int i = 0; i < particles.size(); i++)
        {
            if (particles.get(i).isInstance(particle))
            {
                return icons.get(particles.get(i));
            }
        }
        return null;
    }

    public static void registerEntities()
    {

        for (int i = 0; i < particles.size(); i++)
        {
            try
            {
                EntityRegistry.registerModEntity(particles.get(i), particles.get(i).getName(), i, ParticlePhysics.instance, 80, 1, true);

            }
            catch (SecurityException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
