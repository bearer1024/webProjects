package com.gxa.common;

import java.io.IOException;
import java.util.Properties;

public class DBConfigUtil {

	private static Properties prt; 
	
    public Properties getProperties(){
    	if(prt ==null){
    		init();
    	}
    	
    	return prt;
    }

	private void init() {
		// TODO Auto-generated method stub
		prt = new Properties();
		
		try {
			prt.load(DBConfigUtil.class.getClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	}

