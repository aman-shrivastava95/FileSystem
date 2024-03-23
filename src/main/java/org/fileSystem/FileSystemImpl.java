package org.fileSystem;

import org.fileSystem.models.FileSystemNode;
import org.fileSystem.models.FileSystemNodeType;
import org.fileSystem.util.FSUtil;

public class FileSystemImpl implements FileSystem{
    FileSystemNode root ;

    public FileSystemImpl(){
        root = new FileSystemNode("/", 0L, FileSystemNodeType.DIRECTORY, null);
    }

    @Override
    public FileSystemNode createFile(String path, FileSystemNodeType fileType, long size) {
        String[] nodes = FSUtil.getPathNodes(path) ;
        FileSystemNode curNode = this.root ;
        for (int i = 0 ; i< nodes.length; i++){
            boolean isLeaf = i == nodes.length - 1;
            FileSystemNodeType newNodeType = isLeaf ? fileType : FileSystemNodeType.DIRECTORY ;
            String nodeName = nodes[i] ;
            assert curNode != null;
            FileSystemNode parentRef = curNode ;
            curNode = curNode.getChildren().computeIfAbsent(nodeName, k -> new FileSystemNode(nodeName, 0L, newNodeType, null));
            curNode.setSize(curNode.getSize() + size);
            if (curNode.getParent() == null){
                curNode.setParent(parentRef);
            }
        }
        return curNode;
    }

    @Override
    public FileSystemNode getFile(String path) {
        String[] nodes = FSUtil.getPathNodes(path) ;
        FileSystemNode curNode = this.root ;
        for (int i=0 ; i< nodes.length; i++){
            String nodeName = nodes[i] ;
            if(curNode != null) {
                curNode = curNode.getChildren().get(nodeName);
                continue;
            }
            break ;
        }
        return curNode ;
    }
}
