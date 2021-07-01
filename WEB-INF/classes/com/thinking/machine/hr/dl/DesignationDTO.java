package com.thinking.machine.hr.dl;
public class DesignationDTO implements Comparable<DesignationDTO>,java.io.Serializable
{
private int code;
private String title;
public DesignationDTO()
{
this.code=0;
this.title="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}
public int hasCode()
{
return this.code;
}
public boolean equals(Object other)
{
if(!(other instanceof DesignationDTO)) return false;
DesignationDTO d;
d=(DesignationDTO)other;
return this.code==d.getCode();
}
public int compareTo(DesignationDTO other)
{
return this.title.compareToIgnoreCase(other.title);
}
}
