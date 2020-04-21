package ch.lexustec.coremod.client.gui;

import ch.lexustec.coremod.colony.buildings.workerbuildings.BuildingTwitch;
import com.ldtteam.blockout.Log;
import com.ldtteam.blockout.controls.Button;
import com.ldtteam.blockout.views.ScrollingList;
import ch.lexustec.api.util.constant.Constants;
import com.minecolonies.api.colony.buildings.IBuilding;
import com.minecolonies.coremod.client.gui.AbstractWindowBuilding;
import org.jetbrains.annotations.NotNull;


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
    private static final String BUTTON_REMOVE = "remove";

    /**
     * Suffix describing the window xml.
     */
    private static final String HOME_BUILDING_RESOURCE_SUFFIX = ":gui/windowhuttwitch.xml";

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
    private              ScrollingList     citizen;

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

        this.building = b;
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

        refreshView();
    }

    /**
     * Refresh the view.
     */
    private void refreshView()
    {
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
