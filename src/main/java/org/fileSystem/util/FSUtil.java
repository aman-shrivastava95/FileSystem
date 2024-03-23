package org.fileSystem.util;

import java.util.Arrays;

public class FSUtil {
    public static String[] getPathNodes(String path){
        String[] nodes = path.split("/") ;
        return Arrays.copyOfRange(nodes, 1, nodes.length);
    }
}
