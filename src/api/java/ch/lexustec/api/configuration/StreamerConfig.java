package ch.lexustec.api.configuration;

import java.util.ArrayList;
import java.util.List;

public final class StreamerConfig
{
    public static List<String> streamers;

    public StreamerConfig()
    {
        streamers = new ArrayList<>();
    }

    public static List<String> getStreamers()
    {
        return streamers;
    }
    public void addStreamer(String str)
    {
        streamers.add(str);
    }
    public void setStreamers(List<String> str)
    {
        streamers = str;
    }
    public void addManyStreamers(List<String> str)
    {
        streamers.addAll(str);
    }

}
