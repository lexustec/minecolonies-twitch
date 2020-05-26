package ch.lexustec.coremod.entity.citizen.citizenhandlers;

import ch.lexustec.api.client.render.modeltype.TwitchModelType;
import com.ldtteam.blockout.Log;
import com.minecolonies.api.client.render.modeltype.BipedModelType;
import com.minecolonies.api.client.render.modeltype.CitizenModel;
import com.minecolonies.api.client.render.modeltype.IModelType;
import com.minecolonies.api.client.render.modeltype.registry.IModelTypeRegistry;
import com.minecolonies.api.colony.jobs.IJob;
import com.minecolonies.api.entity.citizen.AbstractEntityCitizen;
import com.minecolonies.api.entity.citizen.citizenhandlers.ICitizenJobHandler;
import com.minecolonies.api.util.BlockPosUtil;
import com.minecolonies.coremod.entity.ai.basic.AbstractEntityAIInteract;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

import static com.minecolonies.api.entity.citizen.AbstractEntityCitizen.DATA_MODEL;

/**
 * Handles the citizen job methods.
 */
public class TwitchJobHandler implements ICitizenJobHandler
{
    /**
     * The citizen assigned to this manager.
     */
    private final AbstractEntityCitizen citizen;

    /**
     * Constructor for the experience handler.
     * @param citizen the citizen owning the handler.
     */
    public TwitchJobHandler(final AbstractEntityCitizen citizen)
    {
        this.citizen = citizen;
    }

    /**
     * Set Model depending on job.
     * @param job the new job.
     */
    @Override
    public void setModelDependingOnJob(@Nullable final IJob job)
    {
        Log.getLogger().info("SETTING JOB IN TWITCHJOBHANDLER");
        if (citizen.isChild())
        {
            citizen.setModelId(BipedModelType.CHILD);
            citizen.getDataManager().set(DATA_MODEL, citizen.getModelType().getName());
            citizen.setRenderMetadata("");
            return;
        }

        if (job == null)
        {

//            if (citizen.getCitizenColonyHandler().getHomeBuilding() != null)
//            {
//                switch (citizen.getCitizenColonyHandler().getHomeBuilding().getBuildingLevel())
//                {
//                    case 3:
//                        citizen.setModelId(BipedModelType.CITIZEN);
//                        break;
//                    case 4:
//                        citizen.setModelId(BipedModelType.NOBLE);
//                        break;
//                    case 5:
//                        citizen.setModelId(BipedModelType.ARISTOCRAT);
//                        break;
//                    default:
//                        citizen.setModelId(BipedModelType.SETTLER);
//                        break;
//                }
//            }
//            else
//            {
            IModelTypeRegistry registry = IModelTypeRegistry.getInstance();
            for (Map.Entry<IModelType, CitizenModel<AbstractEntityCitizen>> entry : registry.getMaleMap().entrySet())
            {
                Log.getLogger().info("YaY" + entry.getKey().getName() + " == " + citizen.getName().getString());
                if (entry.getKey().getName() == citizen.getName().getString())
                {
                    Log.getLogger().info("Found It");
                    citizen.setModelId(entry.getKey());
                }
            }

            for (Map.Entry<IModelType, CitizenModel<AbstractEntityCitizen>> entry : registry.getFemaleMap().entrySet())
            {
                Log.getLogger().info("YaY " + entry.getKey().getName() + " == " + citizen.getName().getString());
                if (entry.getKey().getName() == citizen.getName().getString())
                {
                    Log.getLogger().info("Found It");
                    citizen.setModelId(entry.getKey());
                }
            }


//            }
        }
        else
        {
            citizen.setModelId(job.getModel());
        }
        citizen.getDataManager().set(DATA_MODEL,  citizen.getName().getString());
        citizen.setRenderMetadata("");
    }

    /**
     * Defines job changes and state changes of the citizen.
     *
     * @param job the set job.
     */
    @Override
    public void onJobChanged(@Nullable final IJob job)
    {
        //  Model
        setModelDependingOnJob(job);

        //  AI Tasks
        for (@NotNull final PrioritizedGoal task : citizen.getTasks().goals)
        {
            if (task.getGoal() instanceof AbstractEntityAIInteract)
            {
                citizen.getTasks().removeGoal(task.getGoal());
            }
        }

        if (job != null)
        {
            job.addWorkerAIToTaskList(citizen.getTasks());
            if (citizen.getTicksExisted() > 0 && citizen.getCitizenColonyHandler().getWorkBuilding() != null)
            {
                BlockPosUtil.tryMoveBaseCitizenEntityToXYZ(citizen, citizen.getCitizenColonyHandler().getWorkBuilding().getPosition());
            }
        }
    }

    /**
     * Get the job of the citizen.
     *
     * @param type of the type.
     * @param <J>  wildcard.
     * @return the job.
     */
    @Override
    @Nullable
    public <J extends IJob> J getColonyJob(@NotNull final Class<J> type)
    {
        return citizen.getCitizenData() == null ? null : citizen.getCitizenData().getJob(type);
    }

    /**
     * Gets the job of the entity.
     * @return the job or els enull.
     */
    @Override
    @Nullable
    public IJob getColonyJob()
    {
        return citizen.getCitizenData() == null ? null : citizen.getCitizenData().getJob();
    }

    @Override
    public boolean shouldRunAvoidance()
    {
        return getColonyJob() == null || getColonyJob().allowsAvoidance();
    }
}
