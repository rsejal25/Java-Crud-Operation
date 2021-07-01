package com.thinking.machine.hr.servlets;
import com.thinking.machine.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.thinking.machine.hr.beans.*;
import com.google.gson.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class UpdateEmployee extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
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
StringBuffer b=new StringBuffer();
String d;
BufferedReader br;
br=request.getReader();
while(true)
{
d=br.readLine();
if(d==null) break;
b.append(d);
}
Gson gson=new Gson();
String rawData=b.toString();
EmployeeBean employeeBean=gson.fromJson(rawData,EmployeeBean.class);
String employeeId=employeeBean.getEmployeeId();
String name=employeeBean.getName();
String panNumber=employeeBean.getPANNumber();
String aadharCardNumber=employeeBean.getAadharCardNumber();
int designationCode=employeeBean.getDesignationCode();
String gender=employeeBean.getGender();
basicSalary=new BigDecimal(employeeBean.getBasicSalary());
dateOfBirth=sdf.parse(employeeBean.getDateOfBirth());
String title=employeeBean.getDesignation();
boolean isIndian=employeeBean.getIsIndian();
EmployeeDTO EmployeeDTO=new EmployeeDTO();
DesignationDAO designationDAO=new DesignationDAO();
EmployeeDTO employeeDTO=new EmployeeDTO();
System.out.println("jjnjjne234r");
EmployeeDAO employeeDAO=new EmployeeDAO();
boolean designationCodeExists;
designationCodeExists=designationDAO.designationCodeExists(designationCode);
if(designationCodeExists==false)
{
responseData="{\"result\":\"error\",\"errorMessage\":\"Designation code alredy exists\"}";
pw.print(responseData);
return;
}
employeeDTO.setName(name);
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setGender(gender.charAt(0));
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setIsIndian(isIndian);
System.out.println("hello12346");
employeeDAO.update(employeeDTO);
responseData="{\"result\":\"success\"}";
pw.print(responseData);
}catch(DAOException daoexception)
{
responseData="{\"result\":\"error\",\"errorMessage\":\""+daoexception.getMessage()+"\"}";
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


