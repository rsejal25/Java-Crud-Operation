package com.thinking.machine.hr.servlets;
import java.util.*;
import java.io.*;
import com.google.gson.*;
import javax.servlet.*;
import com.thinking.machine.hr.dl.*;
import javax.servlet.http.*;
public class Employees extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception exception)
{
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{

PrintWriter pw=null;
try
{
EmployeeDAO employeeDAO=new EmployeeDAO();
pw=response.getWriter();
response.setContentType("application/json");
List<EmployeeDTO> employees=employeeDAO.getAll();
int i=0;
Gson gson=new Gson();
String jsonString="";
jsonString=gson.toJson(employees);
pw.print(jsonString);
}catch(Exception exception)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception e)
{

}
}
}
}