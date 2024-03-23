package org.fileSystem.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class FileSystemNode {
    String name ;
    long size ;
    FileSystemNode parent ;
    Map<String, FileSystemNode> children ;
    FileSystemNodeType nodeType ;
    public FileSystemNode(String name, Long size, FileSystemNodeType nodeType, FileSystemNode parent){
        this.name = name ;
        this.size = size ;
        this.nodeType = nodeType ;
        this.children = new ConcurrentHashMap<>() ;
        this.parent = parent ;
    }
}
