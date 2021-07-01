package com.thinking.machine.hr.dl;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;

public class EmployeeDAO 
{
public List<EmployeeDTO> getAll() 
{
List<EmployeeDTO> employees=new LinkedList<>();
Connection connection;
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select employee.employee_id,employee.name,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_card_number,designation.title from employee inner join designation on employee.designation_code=designation.code order by employee.name");
resultSet=preparedStatement.executeQuery();
EmployeeDTO employeeDTO;
while(resultSet.next())
{
employeeDTO=new EmployeeDTO();
employeeDTO.setEmployeeId(resultSet.getString("employee.employee_id"));
employeeDTO.setName(resultSet.getString("name"));
employeeDTO.setPANNumber(resultSet.getString("pan_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
employeeDTO.setDateOfBirth(resultSet.getDate("date_of_birth"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basic_salary"));
employeeDTO.setIsIndian(resultSet.getBoolean("is_indian"));
employeeDTO.setGender(resultSet.getString("gender").charAt(0));
employeeDTO.setDesignation(resultSet.getString("title"));
employees.add(employeeDTO);
}
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception e)
{

}
return employees;
}
public void addEmployees(EmployeeDTO employeeDTO) throws DAOException
{
Connection connection;
PreparedStatement preparedStatement;
java.sql.Date sqlDateOfBirth;
java.util.Date dateOfBirth;
ResultSet resultSet;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select employee_id from employee where pan_number=?");
preparedStatement.setString(1,employeeDTO.getPANNumber());
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN Number "+employeeDTO.getPANNumber()+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select employee_id from employee where aadhar_card_number=?");
preparedStatement.setString(1,employeeDTO.getAadharCardNumber());
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number "+employeeDTO.getAadharCardNumber()+" already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into employee (name,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_card_number,designation_code) values(?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,employeeDTO.getName());
dateOfBirth=employeeDTO.getDateOfBirth();
sqlDateOfBirth=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(2,sqlDateOfBirth);
preparedStatement.setString(3,String.valueOf(employeeDTO.getGender()));
preparedStatement.setBoolean(4,employeeDTO.getIsIndian());
preparedStatement.setBigDecimal(5,employeeDTO.getBasicSalary());
preparedStatement.setString(6,employeeDTO.getPANNumber());
preparedStatement.setString(7,employeeDTO.getAadharCardNumber());
preparedStatement.setInt(8,employeeDTO.getDesignationCode());
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int employeeId=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException exception)
{
throw new DAOException(exception.getMessage());
}
}
public boolean panNumberExists(String panNumber) throws DAOException
{
ResultSet resultSet;
Connection connection;
PreparedStatement preparedStatement;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select gender from employee where pan_number=?");
preparedStatement.setString(1,panNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()) return true;
resultSet.close();
preparedStatement.close();
connection.close();

}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return false;
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
ResultSet resultSet;
Connection connection;
PreparedStatement preparedStatement;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select gender from employee where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()) return true;
resultSet.close();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return false;
}
public void deleteByCode(String employeeId) throws DAOException
{
//int actualEmployeeId;
Connection connection;
PreparedStatement preparedStatement;
ResultSet resultSet;
try
{
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select gender from employee where employee_id=?");
preparedStatement.setString(1,employeeId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Employee Id does not exists");
}
System.out.println("hhfjjfjf");
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("delete from employee where employee_id=?");
preparedStatement.setString(1,employeeId);
preparedStatement.executeUpdate();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public void update(EmployeeDTO employeeDTO) throws DAOException
{
if(employeeDTO==null) throw new DAOException("employee is null");
String name;
String employeeId;
String panNumber;
String aadharCardNumber;
int designationCode;
boolean isIndian;
BigDecimal basicSalary;
java.util.Date dateOfBirth;
char gender;
employeeId=employeeDTO.getEmployeeId();
if(employeeId==null) throw new DAOException("EmployeeId is null");
employeeId=employeeId.trim();
if(employeeId.length()==0) throw new DAOException("EmployeeId length is zero");
name=employeeDTO.getName();
if(name==null) throw new DAOException("name is null");
name=name.trim();
if(name.length()==0) throw new DAOException("length of name is zero");
designationCode=employeeDTO.getDesignationCode();
if(designationCode<=0) throw new DAOException("invalid designation code");
boolean designationCodeValid=false;
DesignationDAO designationDAO=new DesignationDAO();
designationCodeValid=designationDAO.designationCodeExists(designationCode);
if(designationCodeValid==false) throw new DAOException("designation code does not exists");
dateOfBirth=employeeDTO.getDateOfBirth();
if(dateOfBirth==null) throw new DAOException("date of birth is null");
gender=employeeDTO.getGender();
if(gender==' ')
{
throw new DAOException("gender not set Male/Female");
}
isIndian=employeeDTO.getIsIndian();
basicSalary=employeeDTO.getBasicSalary();
if(basicSalary==null) throw new DAOException("basic salary is null");
if(basicSalary.signum()==-1) throw new DAOException("basic salary is negative");
panNumber=employeeDTO.getPANNumber();
if(panNumber==null) throw new DAOException("PAN number is null");
panNumber=panNumber.trim();
if(panNumber.length()==0) throw new DAOException("PAN Number is of zero length");
aadharCardNumber=employeeDTO.getAadharCardNumber();
if(aadharCardNumber==null) throw new DAOException("Aadhar card number is null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0) throw new DAOException("Aadhar card number is of zero length");
Connection connection;
try
{
boolean employeeIdExists=false;
boolean panNumberExists=false;
boolean aadharCardNumberExists=false;
String actualEmployeeId=employeeId;
connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
ResultSet resultSet;
//actualEmployeeId=Integer.parseInt(employeeId.substring(1))-10000000;
preparedStatement=connection.prepareStatement("select gender from employee where employee_id=?");
preparedStatement.setString(1,actualEmployeeId);
resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Employee Id does not exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select gender from employee where pan_number=? and employee_id!=?");
preparedStatement.setString(1,panNumber);
preparedStatement.setString(2,employeeId);
resultSet=preparedStatement.executeQuery();
panNumberExists=resultSet.next();
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select gender from employee where aadhar_card_number=? and employee_id!=?");
preparedStatement.setString(1,aadharCardNumber);
preparedStatement.setString(2,employeeId);

resultSet=preparedStatement.executeQuery();
aadharCardNumberExists=resultSet.next();
if(aadharCardNumberExists&&panNumberExists)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN number and Aadhar Card number already exists");
}
if(panNumberExists)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("PAN number already exists");
}
if(aadharCardNumberExists)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar card number already exists");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update employee set name=?,designation_code=?,date_of_birth=?,basic_salary=?,gender=?,is_indian=?,pan_number=?,aadhar_card_number=? where employee_id=?");
preparedStatement.setString(1,name);
preparedStatement.setInt(2,designationCode);
java.sql.Date sqlDateOfBirth=new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());
preparedStatement.setDate(3,sqlDateOfBirth);
preparedStatement.setBigDecimal(4,basicSalary);
preparedStatement.setString(5,String.valueOf(gender));
preparedStatement.setBoolean(6,isIndian);
preparedStatement.setString(7,panNumber);
preparedStatement.setString(8,aadharCardNumber);
preparedStatement.setString(9,actualEmployeeId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}

public EmployeeDTO getByEmployeeId(String employeeId) throws DAOException
{
Connection connection;
PreparedStatement preparedStatement;
ResultSet resultSet;
EmployeeDTO employeeDTO;
if(employeeId==null)
{
throw new DAOException("invalid employee id");
}
try
{
String actualEmployeeId=employeeId;
System.out.println(employeeId);
connection=DAOConnection.getConnection();
preparedStatement=connection.prepareStatement("select employee.designation_code,employee.gender,designation.title,employee.pan_number,employee.aadhar_card_number,employee.designation_code,employee.date_of_birth,employee.basic_salary,employee.is_indian,employee.name from employee inner join designation on employee.designation_code=designation.code and employee_id=?");
preparedStatement.setString(1,actualEmployeeId);
resultSet=preparedStatement.executeQuery();
System.out.println("horhhh");
if(resultSet.next()==false) throw new DAOException("Employee Id does not exists");
System.out.println("hor");
employeeDTO=new EmployeeDTO();
System.out.println("hor111");
System.out.println("found");
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setGender(resultSet.getString("gender").charAt(0));
System.out.println("found11");
employeeDTO.setName(resultSet.getString("name"));
System.out.println("foundjdj");
employeeDTO.setPANNumber(resultSet.getString("pan_number"));
employeeDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
employeeDTO.setBasicSalary(resultSet.getBigDecimal("basic_salary"));
employeeDTO.setDateOfBirth(resultSet.getDate("date_of_birth"));
employeeDTO.setDesignationCode(resultSet.getInt("designation_code"));	
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,employeeDTO.getDesignationCode());
resultSet=preparedStatement.executeQuery();
if(resultSet.next()) 
employeeDTO.setDesignation(resultSet.getString("title"));
}catch(Exception exception)
{
System.out.println(exception.getMessage());
throw new DAOException(exception.getMessage());
}
return employeeDTO;
}
}