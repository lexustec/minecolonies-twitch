package ch.lexustec.coremod;

import ch.lexustec.coremod.network.NetworkChannel;


public class Network
{
    /**
     * The network instance.
     */
    private static NetworkChannel network = new NetworkChannel("twitch-net-channel");

    /**
     * Get the network handler.
     * @return the network handler.
     */
    public static NetworkChannel getNetwork()
    {
        return network;
    }
}
