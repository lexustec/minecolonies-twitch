package ch.lexustec.api.blocks;

import ch.lexustec.api.blocks.*;
import com.minecolonies.api.blocks.AbstractBlockHut;
import org.jetbrains.annotations.NotNull;

/**
 * Class to create the modBlocks.
 * References to the blocks can be made here
 * <p>
 * We disabled the following finals since we are neither able to mark the items as final, nor do we want to provide public accessors.
 */
@SuppressWarnings({"squid:ClassVariableVisibilityCheck", "squid:S2444", "squid:S1444", "squid:S1820",})
public final class ModBlocks
{
    /*
     * Creating objects for all blocks in the mod.
     * References can be made to here.
     */
    //public static AbstractBlockHut blockHutTownHall;
    public static AbstractBlockHut blockHutTwitch;


    /**
     * Utility blocks.
     */

    //public static AbstractBlockMinecolonies           blockCompostedDirt;

    /**
     * Private constructor to hide the implicit public one.
     */
    private ModBlocks()
    {

    }

    @NotNull
    public static AbstractBlockHut[] getHuts()
    {
        return new AbstractBlockHut[] {
          blockHutTwitch
          //blockHutStoneSmeltery,
          //blockHutStonemason,
          //blockHutGuardTower,
          //blockHutArchery,
          //blockHutBaker,
          //blockHutBarracks,
          //blockHutBarracksTower,
          //blockHutBlacksmith,
          //blockHutBuilder,
          //blockHutChickenHerder,
          //blockHutHome,
          //blockHutCombatAcademy,
          //blockHutComposter,
          //blockHutCook,
          //blockHutCowboy,
          //blockHutCrusher,
          //blockHutArchery,
          //blockHutDeliveryman,
          //blockHutFarmer,
          //blockHutFisherman,
          //blockHutLibrary,
          //blockHutLumberjack,
          //blockHutMiner,
          //blockHutSawmill,
          //blockHutSifter,
          //blockHutShepherd,
          //blockHutSmeltery,
          //blockHutSwineHerder,
          //blockHutTownHall,
          //blockHutUniversity,
          //blockHutHospital,
          //blockHutSchool
        };
    }
}
