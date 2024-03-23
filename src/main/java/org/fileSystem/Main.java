package org.fileSystem;

import org.fileSystem.models.FileSystemNode;
import org.fileSystem.models.FileSystemNodeType;

/*File system is basically a tree with root node at the root of file system
* fs -> createFileOrDirectory(path, type, size)
* */

public class Main {
    public static void main(String[] args) {
        String path = "/Users/Aman/Documents/personal/test.java" ;
        FileSystem fs = new FileSystemImpl() ;
        FileSystemNode javaFile = fs.createFile(path, FileSystemNodeType.FILE, 10L);
        System.out.println("file created");

        path = "/Users/Aman/Documents/atlassian.js";
        FileSystemNode jsFile = fs.createFile(path, FileSystemNodeType.FILE, 20L);

        FileSystemNode personalDirectory = fs.getFile("/Users/Aman/Documents/personal") ;
        FileSystemNode documentsDirectory =  fs.getFile("/Users/Aman/Documents");

        System.out.println("personal directory size: "+ personalDirectory.getSize());
        System.out.println("Documents directory size: "+ documentsDirectory.getSize());






    }
}