package ch.lexustec.coremod.client.gui;

import ch.lexustec.api.configuration.StreamerConfig;
import ch.lexustec.coremod.colony.buildings.workerbuildings.BuildingTwitch;
import ch.lexustec.coremod.network.messages.BuyStreamerMessage;
import com.ldtteam.blockout.Log;
import com.ldtteam.blockout.Pane;
import com.ldtteam.blockout.controls.Button;
import com.ldtteam.blockout.controls.ButtonImage;
import com.ldtteam.blockout.controls.Label;
import com.ldtteam.blockout.views.ScrollingList;
import ch.lexustec.api.util.constant.Constants;
import com.minecolonies.api.colony.IColonyView;
import ch.lexustec.coremod.Network;
import com.minecolonies.coremod.client.gui.AbstractWindowBuilding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import sun.net.ResourceManager;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.minecolonies.api.util.constant.WindowConstants.LIST_RESOURCES;
import static com.minecolonies.api.util.constant.WindowConstants.STOCK_REMOVE;

/**
 * Window for the home building.
 */
public class WindowHutTwitch extends AbstractWindowBuilding<BuildingTwitch.View>
{
    /**
     * Id of the hire/fire button in the GUI.
     */
    private static final String BUTTON_ASSIGN = "assign";

    /**
     * Id of the hire/fire button in the GUI.
     */
    private static final String      BUTTON_REMOVE = "remove";
    /**
     * The client side colony data
     */
    private final        IColonyView colony;
    /**
     * Suffix describing the window xml.
     */
    private static final String      HOME_BUILDING_RESOURCE_SUFFIX = ":gui/windowhuttwitch.xml";

    /**
     * Id to identify the list of the citizen in the view.
     */
    private static final String            LIST_CITIZEN = "assignedCitizen";
    /**
     * The building the view is relates to.
     */
    private final        BuildingTwitch.View building;
    /**
     * The list of citizen assigned to this hut.
     */
    private              ScrollingList     hireStreamer;

    private List <String> streamerForHire ;
    /**
     * Creates the Window object.
     *
     * @param b View of the home building.
     */
    public WindowHutTwitch(final BuildingTwitch.View b)
    {
        super(b, Constants.MOD_ID + HOME_BUILDING_RESOURCE_SUFFIX);

        registerButton(BUTTON_ASSIGN, this::assignClicked);
        Log.getLogger().info("Tile Entity " + b.getID() +" cust name "+ b.getCustomName() +" SchemName "+ b.getSchematicName());
        hireStreamer = window.findPaneOfTypeByID("assignStreamer", ScrollingList.class);
        this.building = b;
        streamerForHire = StreamerConfig.getStreamers();
        colony = b.getColony();
        if(building.getBuildingLevel() > 0)
        {
            window.findPaneOfTypeByID("build", ButtonImage.class).hide();
        }
        if(colony == null)
        {
            Log.getLogger().info("No Colony..... why");
        }
        registerButton("rent", this::trySpawnStreamer);
    }

    @Override
    public void onOpened()
    {
        super.onOpened();
        //final boolean isManualHousing = building.getColony().isManualHousing();
        //citizen = findPaneOfTypeByID(LIST_CITIZEN, ScrollingList.class);
        //citizen.setDataProvider(new ScrollingList.DataProvider()
        //{
        //    @Override
        //    public int getElementCount()
        //    {
        //        return twitchview.getResidents().size();
        //    }
        //
        //    @Override
        //    public void updateElement(final int index, @NotNull final Pane rowPane)
        //    {
        //        final ICitizenDataView citizenDataView = home.getColony().getCitizen((home.getResidents().get(index)));
        //        if (citizenDataView != null)
        //        {
        //            rowPane.findPaneOfTypeByID("name", Label.class).setLabelText(citizenDataView.getName());
        //            rowPane.findPaneOfTypeByID(BUTTON_REMOVE, Button.class).setEnabled(isManualHousing);
        //        }
        //    }
        //});
        Log.getLogger().info(Minecraft.getInstance().getResourceManager().getResourceNamespaces().toString());
        refreshView();
    }

    /**
     * Refresh the view.
     */
    private void refreshView()
    {
        Log.getLogger().info(StreamerConfig.getStreamers().toString());

        hireStreamer.enable();
        hireStreamer.show();

        hireStreamer.setDataProvider(new ScrollingList.DataProvider() {
            @Override
            public int getElementCount()
            {
                return streamerForHire.size();
            }

            @Override
            public void updateElement(final int i, final Pane pane)
            {
                    final Label nameLabel = pane.findPaneOfTypeByID("name", Label.class);
                    nameLabel.setLabelText(streamerForHire.get(i));
                    final ButtonImage canvas = pane.findPaneOfTypeByID("canvas", ButtonImage.class);
                    //File f = new File("minecoloniestwitchdata:streamer/" + streamerForHire.get(i) + "/image/canvas.png");



                 //   String absolutePath = FileSystems.getDefault().getPath("minecoloniestwitchdata:streamer/" + streamerForHire.get(i) + "/image/canvas.png").normalize().toAbsolutePath().toString();


                //////File[] file2 = new File(FileSystems.getDefault().getPath(Minecraft.getInstance().gameDir.toString()).normalize().toAbsolutePath().toString() + "/" + Constants.MOD_ID + "/streamer").listFiles(File::isDirectory);
                //Log.getLogger().info("File List2" + absolutePath);
                //    if(f.exists())
                //    {
                ////
                ////        DynamicTexture currentCrest = new DynamicTexture(32,32,true);
                ////
                ////        ResourceLocation crestLocation = Minecraft.getInstance().getTextureManager().getDynamicTextureLocation(Minecraft.getInstance().gameDir + "/" + Constants.MOD_ID + "/streamer/" + streamerForHire.get(i) + "/image/canvas.png", currentCrest);
                //        canvas.setImage("minecoloniestwitchdata:streamer/" + streamerForHire.get(i) + "/image/canvas.png");
                //    }
                //    else
                //    {
                      canvas.setImage(new ResourceLocation("minecoloniestwitchdata","default/defaultcanvas.png"));
                //    }
            }
        });
        //final Button buttonAssign = findPaneOfTypeByID(BUTTON_ASSIGN, Button.class);
        //
        //final int sparePlaces = building.getBuildingLevel() - building.getResidents().size();
        //buttonAssign.setLabel(LanguageHandler.format(COM_MINECOLONIES_COREMOD_GUI_HOME_ASSIGN, sparePlaces));
        //buttonAssign.setEnabled(sparePlaces > 0 && building.getColony().isManualHousing());
        //
        //citizen.refreshElementPanes();
    }

    /**
     * Action when an assign button is clicked.
     */
    private void assignClicked()
    {
        Log.getLogger().info("assignBuilderLevel");
        trySpawnStreamer(null);
        //if (building.getColony().isManualHousing())
        //{
        //    if (building.getBuildingLevel() == 0)
        //    {
        //        LanguageHandler.sendPlayerMessage(Minecraft.getInstance().player, COM_MINECOLONIES_COREMOD_GUI_WORKERHUTS_LEVEL_0);
        //        return;
        //    }
        //
        //    if (building.getResidents().size() < building.getBuildingLevel())
        //    {
        //        @NotNull final WindowAssignCitizen window = new WindowAssignCitizen(building.getColony(), building.getPosition());
        //        window.open();
        //    }
        //}
    }
    /**
     * Try to spawn a new citizen as child.
     * Mom / dad entities are required and chosen randomly in this hut.
     * Childs inherit stats from their parents, avergaged +-2
     * Childs get assigned to a free housing slot in the colony to be raised there,
     * if the house has an adult living there the child takes its name and gets raised by it.
     */
    public void trySpawnStreamer(final Button button)
    {

        if(button == null)
        {
            Network.getNetwork().sendToServer(new BuyStreamerMessage("DONNERLORD", colony.getID(), colony.getDimension()));
        }
        else
        {
            final int row = hireStreamer.getListElementIndexByPane(button);
            Network.getNetwork().sendToServer(new BuyStreamerMessage(streamerForHire.get(row), colony.getID(), colony.getDimension()));
        }


    }
    /**
     * Action when the remove button is clicked.
     *
     * @param button the clicked button.
     */
    private void removeClicked(@NotNull final Button button)
    {
        //if (building.getColony().isManualHousing())
        //{
        //    final int row = citizen.getListElementIndexByPane(button);
        //    final int citizenid = home.getResidents().get(row);
        //    home.removeResident(row);
        //    Network.getNetwork().sendToServer(new AssignUnassignMessage(building, false, citizenid));
        //    refreshView();
        //}
    }

    /**
     * Returns the name of a building.
     * com.minecoloniestwitch.coremod.gui.workerhuts.twitchHut
     * @return Name of a building.
     */
    @NotNull
    @Override
    public String getBuildingName()
    {
        return "blockhuttwitchhut";
    }

}
