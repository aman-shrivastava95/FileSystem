package org.fileSystem;

import org.fileSystem.models.FileSystemNode;
import org.fileSystem.models.FileSystemNodeType;
import org.fileSystem.util.FSUtil;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class FileSystemImpl implements FileSystem{
    private FileSystemNode root ;
    private PriorityQueue<FileSystemNode> priorityQueue ;

    public FileSystemImpl(){
        this.root = new FileSystemNode("/", 0L, FileSystemNodeType.DIRECTORY, null, "/");
        this.priorityQueue = new PriorityQueue<>(((o1, o2) -> (int)(o2.getSize() - o1.getSize())));
        priorityQueue.add(root) ;
    }

    @Override
    public FileSystemNode createFile(String path, FileSystemNodeType fileType, long size) {
        ArrayList<String> pathElements = new ArrayList<>();
        String[] nodes = FSUtil.getPathNodes(path) ;
        FileSystemNode curNode = this.root ;
        for (int i = 0 ; i< nodes.length; i++){
            boolean isLeaf = i == nodes.length - 1;
            FileSystemNodeType newNodeType = isLeaf ? fileType : FileSystemNodeType.DIRECTORY ;
            String nodeName = nodes[i] ;
            pathElements.add(nodeName) ;
            assert curNode != null;
            FileSystemNode parentRef = curNode ;
            String curPath  = "/" + String.join("/", pathElements) ;
            curNode = curNode.getChildren().computeIfAbsent(nodeName, k -> new FileSystemNode(nodeName, 0L, newNodeType, null , curPath) );
            curNode.setSize(curNode.getSize() + size);

            //this bit will make sure that the priority queue goes through reordering, whenever the size of nodes change
            priorityQueue.remove(curNode) ;
            priorityQueue.add(curNode) ;

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

    @Override
    public ArrayList<FileSystemNode> getTopNFiles(int K) {
        ArrayList<FileSystemNode> result = new ArrayList<>() ;
        for (int i = 0 ; i < K ; i++){
            if(!priorityQueue.isEmpty()){
                result.add(priorityQueue.poll()) ;
            }
        }
        priorityQueue.addAll(result);
        return result ;
    }
}
