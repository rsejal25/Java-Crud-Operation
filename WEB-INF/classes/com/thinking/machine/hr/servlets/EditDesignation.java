package com.thinking.machine.hr.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
import com.thinking.machine.hr.dl.*;
public class EditDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
String jsonString="";
try
{
System.out.println("lave233");
int code=Integer.parseInt(request.getParameter("code"));
Gson gson=new Gson();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
DesignationDAO designationDAO=new DesignationDAO();
DesignationDTO designationDTO;
designationDTO=designationDAO.getByCode(code);
pw=response.getWriter();
jsonString="{\"result\":\"success\"";
jsonString+=",\"title\":\""+designationDTO.getTitle()+"\"";
jsonString+=",\"code\":\""+designationDTO.getCode()+"\"";
jsonString+="}";
pw.print(jsonString);
pw.flush();
}catch(DAOException e)
{
jsonString="{\"result\":\"error\"}";
pw.print(jsonString);
pw.flush();
}
catch(Exception exception)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception exceptin)
{

}

}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception exception)
{

}
}
}