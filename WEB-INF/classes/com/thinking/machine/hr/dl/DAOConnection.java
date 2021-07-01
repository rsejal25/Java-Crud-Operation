package com.thinking.machine.hr.dl;
import java.sql.*;
public class DAOConnection
{
private DAOConnection()
{
}
static public Connection getConnection() throws DAOException
{
Connection connection;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmdb","tmdbuser","tmdbuser");
}catch(Exception e)
{
System.out.println("eheoeo");
throw new DAOException(e.getMessage());
}
return connection;
}
}