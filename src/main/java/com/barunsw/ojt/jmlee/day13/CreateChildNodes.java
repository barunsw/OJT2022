package com.barunsw.ojt.jmlee.day13;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;

class CreateChildNodes implements Runnable {

    private DefaultMutableTreeNode root;

    private File fileRoot;

    public CreateChildNodes(File fileRoot, DefaultMutableTreeNode root) {
       this.fileRoot = fileRoot;
       this.root = root;
    }

    @Override
    public void run() {
       createChildren(fileRoot, root);
    }

    private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
       
       File[] files = fileRoot.listFiles();   // root에서 파일 리스트를 가져옴
       
       if (files == null)                  // null이면 리턴
          return;
       for (File file : files) {
          DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));
          node.add(childNode);   
          if (file.isDirectory()) {
             createChildren(file, childNode);
          }
       }
    }
 }