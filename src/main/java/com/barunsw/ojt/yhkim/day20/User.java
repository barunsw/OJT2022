package com.barunsw.ojt.yhkim.day20;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User implements Serializable {
   private int seqNum;
   private String name;
   
   public User(int seqNum, String name) {
      this.seqNum = seqNum;
      this.name = name;
   }
   
   public int getSeqNum() {
      return seqNum;
   }
   
   public void setSeqNum(int seqNum) {
      this.seqNum = seqNum;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   @Override
   public String toString() {
	   return ToStringBuilder.reflectionToString(this);
   }

   @Override
   public boolean equals(Object o) {
	   return EqualsBuilder.reflectionEquals(this, o);
   }

   @Override
   public int hashCode() {
	   return HashCodeBuilder.reflectionHashCode(this);
   }
}