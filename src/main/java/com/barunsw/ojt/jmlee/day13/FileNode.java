package com.barunsw.ojt.jmlee.day13;

import java.io.File;

class FileNode {

    private File file;

    public FileNode(File file) {
       this.file = file;
    }

    public File getFile() {
		return file;
	}

	@Override
    public String toString() {
       
       String name = file.getName();
       
       if (name.equals("")) {
          return file.getAbsolutePath();
       } else {
          return name;
       }
    }
 }