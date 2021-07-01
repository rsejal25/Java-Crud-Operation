package com.thinking.machine.hr.servlets;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import java.text.*;
import java.math.*;
import com.thinking.machine.hr.dl.*;
import javax.servlet.http.*;
public class EditEmployee extends HttpServlet
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
SimpleDateFormat sdf;
String dataToSend="";
try
{
System.out.println("fjjefjfr!1");
String employeeId=request.getParameter("employeeId");
EmployeeDAO employeeDAO=new EmployeeDAO();
pw=response.getWriter();
EmployeeDTO employeeDTO=null;
employeeDTO=employeeDAO.getByEmployeeId(employeeId);
dataToSend="{\"result\":\"success\"";
dataToSend+=",\"name\":\""+employeeDTO.getName()+"\"";
dataToSend+=",\"employeeId\":\""+employeeDTO.getEmployeeId()+"\"";

System.out.println(employeeDTO.getName());

System.out.println("fjjefjfr!hhhh1");
System.out.println(employeeDTO.getName());
dataToSend+=",\"designation\":\""+employeeDTO.getDesignation()+"\"";
dataToSend+=",\"designationCode\":\""+employeeDTO.getDesignationCode()+"\"";
String d1=new SimpleDateFormat("yyyy-MM-dd").format(employeeDTO.getDateOfBirth());
dataToSend+=",\"dateOfBirth\":\""+d1+"\"";
dataToSend+=",\"gender\":\""+employeeDTO.getGender()+"\"";
dataToSend+=",\"isIndian\":\""+employeeDTO.getIsIndian()+"\"";
System.out.println(employeeDTO.getIsIndian());

dataToSend+=",\"basicSalary\":\""+employeeDTO.getBasicSalary().toPlainString()+"\"";
dataToSend+=",\"panNumber\":\""+employeeDTO.getPANNumber()+"\"";
dataToSend+=",\"aadharCardNumber\":\""+employeeDTO.getAadharCardNumber()+"\"";
dataToSend+="}";
pw.print(dataToSend);
System.out.println("fjjefjfr");
}catch(DAOException daoexception)
{
dataToSend="{\"result\":\"error\",\"errorMessage\":\""+daoexception.getMessage()+"\"}";
System.out.println(daoexception.getMessage());
pw.print(dataToSend);
}
catch(Exception exception)
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