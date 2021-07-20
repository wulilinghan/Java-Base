package io.nio.nonblockIO.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientData {

    public static String clientDataKey = "CLIENT_DATA";
    public static volatile List<Object> synData = Collections.synchronizedList(new ArrayList<>());

    static {
        synData.add("gaoyue");
        synData.add("tlx");
        synData.add("gege");
    }

}
