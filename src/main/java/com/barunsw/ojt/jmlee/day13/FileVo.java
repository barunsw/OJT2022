package com.barunsw.ojt.jmlee.day13;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FileVo implements Serializable {
	
	private String fileName;
	private String created;
	private String fileType;
	private String filepath;
	
	
	public FileVo() {
	}
	
	public FileVo(String fileName, String created, String fileType, String filepath) {
		this.fileName = fileName;
		this.created = created;
		this.fileType = fileType;
		this.filepath = filepath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	public String getFilepath() {
		return filepath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	@Override
	public String toString() {
		//return ToStringBuilder.reflectionToString(this);
		return fileName;
	}

}
