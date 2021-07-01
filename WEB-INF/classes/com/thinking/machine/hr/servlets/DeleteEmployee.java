package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class DeleteEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
String responseData="";
PrintWriter pw=null;
BigDecimal basicSalary=null;
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
Date dateOfBirth;
try
{
System.out.println("jjnjjnejnejnrjr");
pw=response.getWriter();
response.setContentType("application/json");
String employeeId=request.getParameter("employeeId");
DesignationDAO designationDAO=new DesignationDAO();
EmployeeDTO employeeDTO=new EmployeeDTO();
System.out.println("jjnjjne234r");
EmployeeDAO employeeDAO=new EmployeeDAO();
boolean designationCodeExists;
employeeDAO.deleteByCode(employeeId);
responseData="{\"result\":\"success\"}";
pw.print(responseData);
}catch(DAOException daoexception)
{
responseData="{\"result\":\"error\",\"errorString\":\""+daoexception.getMessage()+"\"}";
pw.print(responseData);
return;
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
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
}
}
}


