package org.fileSystem.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class FileSystemNode {
    String name ;
    String path ;
    long size ;
    FileSystemNode parent ;
    Map<String, FileSystemNode> children ;
    FileSystemNodeType nodeType ;
    public FileSystemNode(String name, Long size, FileSystemNodeType nodeType, FileSystemNode parent, String path){
        this.name = name ;
        this.size = size ;
        this.nodeType = nodeType ;
        this.children = new ConcurrentHashMap<>() ;
        this.parent = parent ;
        this.path = path ;
    }
    @Override
    public boolean equals(Object o){
        // custom equals method for comparing based on the path name: since it will be unique, if path changes for any node,
        // the updates need to be handled in the priority queue.as well.
        if ( o==null  || this.getClass() != o.getClass()){
            return false ;
        }
        FileSystemNode thisObject = (FileSystemNode)this;
        FileSystemNode otherObject = (FileSystemNode)o ;

        return thisObject.getPath().equals(otherObject.getPath());

    }
}
