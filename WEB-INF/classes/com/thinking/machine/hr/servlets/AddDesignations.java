package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;
public class AddDesignations extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
String responseData="";
PrintWriter pw=null;
try
{
BufferedReader br=request.getReader();
StringBuffer b=new StringBuffer();
String d;
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
//System.out.println(rawData);
String rawData=b.toString();
Gson gson=new Gson();
DesignationDTO designation=gson.fromJson(rawData,DesignationDTO.class);
System.out.println("jjnjjnejnejnrjr");
pw=response.getWriter();
DesignationDAO designationDAO=new DesignationDAO();
designationDAO.addDesignation(designation);
response.setContentType("application/json");
responseData="{\"result\":\"success\"}";
pw.print(responseData);
pw.flush();
}catch(DAOException daoexception)
{
responseData="{\"result\":\"error\",\"errorMessage\":\""+daoexception.getMessage()+"\"}";
pw.print(responseData);
pw.flush();
}
catch(Exception e)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
}
}
}


