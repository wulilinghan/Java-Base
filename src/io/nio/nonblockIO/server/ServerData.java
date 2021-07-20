package io.nio.nonblockIO.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerData {
    public static String serverDataKey = "SERVER_DATA";
    public static volatile List<Object> synData = Collections.synchronizedList(new ArrayList<>());
}
