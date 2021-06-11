package dao;

import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerBO
{
	
	private Logger logger;
	 
    public Logger getLogger(String className)
    {
    	try
    	{
    		logger = Logger.getLogger(className);
	    	logger.setLevel(Level.INFO);
	        InputStream input = new FileInputStream("C:\\Users\\ved.asole\\Desktop\\Project Workspace\\AngelDoc\\WebContent\\WEB-INF\\log4j.properties");
	        PropertyConfigurator.configure(input);
	        return logger;
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
    }

}
