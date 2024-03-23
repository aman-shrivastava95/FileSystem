package org.fileSystem;

import org.fileSystem.models.FileSystemNode;
import org.fileSystem.models.FileSystemNodeType;

import java.util.ArrayList;

public interface FileSystem {
    public FileSystemNode createFile(String path, FileSystemNodeType type, long size) ;
    public FileSystemNode getFile(String path) ;
    public ArrayList<FileSystemNode> getTopNFiles(int K) ;
}
