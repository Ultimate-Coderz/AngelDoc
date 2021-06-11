package MigrateServlets;

public class Alert 
{
	
	public String successAlert(String message)
	{
		
		String alert="<html lang=\"en\">\r\n"
				+ " \r\n"
				+ "<head>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/dist/notiflix-aio-2.7.0.min.js\"></script>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/src/all-in-one/notiflix-aio.js\"></script>\r\n"
				+ "  <script src=\"https://kit.fontawesome.com/64d58efce2.js\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  <link href=\"assets/vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\">\r\n"
				+ "  <script>\r\n"
				+ "    Notiflix.Notify.Init({ position:\"right-bottom\",useFontAwesome:true,fontSize:\"15px\",fontAwesomeIconSize:\"30px\", width:\" 350px; \" });\r\n"				
				+ "    \r\n"
				+ "    window.addEventListener(\"load\", function(){\r\n"
				+ "      Notiflix.Notify.Success('"+message+"');\r\n"
				+ "    });\r\n"
				+ "  </script>\r\n"
				+ "</head>\r\n"
				+ " \r\n"
				+ "<body >\r\n"
				+ " \r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		return alert;
	}
	
			// For success => Notiflix.Notify.Success('Success Message');
			// For failure => Notiflix.Notify.Failure('Alert Message');
	
	public String failureAlert(String message)
	{
		
		String alert="<html lang=\"en\">\r\n"
				+ " \r\n"
				+ "<head>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/dist/notiflix-aio-2.7.0.min.js\"></script>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/src/all-in-one/notiflix-aio.js\"></script>\r\n"
				+ "  <script src=\"https://kit.fontawesome.com/64d58efce2.js\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  <link href=\"assets/vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\">\r\n"
				+ "  <script>\r\n"
				+ "    Notiflix.Notify.Init({ position:\"right-bottom\",useFontAwesome:true,fontSize:\"15px\",fontAwesomeIconSize:\"30px\" });\r\n"				
				+ "    \r\n"
				+ "    window.addEventListener(\"load\", function(){\r\n"
				+ "      Notiflix.Notify.Failure('"+message+"');\r\n"
				+ "    });\r\n"
				+ "  </script>\r\n"
				+ "</head>\r\n"
				+ " \r\n"
				+ "<body >\r\n"
				+ " \r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		return alert;
	}
	
	public String newUserAlert(String name)
	{
		
		String alert="<html lang=\"en\">\r\n"
				+ " \r\n"
				+ "<head>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/dist/notiflix-aio-2.7.0.min.js\"></script>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/src/all-in-one/notiflix-aio.js\"></script>\r\n"
				+ "  <script src=\"https://kit.fontawesome.com/64d58efce2.js\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  <script>\r\n"
				+ "    Notiflix.Report.Init({ borderRadius:\"10px\",  backOverlayColor:\"rgba(0,0,0,0.238)\" });\r\n"				
				+ "    \r\n"
				+ "    window.addEventListener(\"load\", function(){\r\n"
				+ "       Notiflix.Report.Success( 'Welcome to AngelDoc', 'Hi, "+name+"!, you have successfully registered.', 'Okay' );\r\n"
				+ "    });\r\n"
				+ "  </script>\r\n"
				+ "</head>\r\n"
				+ " \r\n"
				+ "<body >\r\n"
				+ " \r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		return alert;
		
	}
	
	public String failureReport(String heading, String msg)
	{
		
		String alert="<html lang=en>\r\n"
				+ "				 \r\n"
				+ "				<head>\r\n"
				+ "				  <script src=\"assets/vendor/Notiflix-2.7.0/dist/notiflix-aio-2.7.0.min.js\"></script>\r\n"
				+ "				  <script src=\"assets/vendor/Notiflix-2.7.0/src/all-in-one/notiflix-aio.js\"></script>\r\n"
				+ "				  <script src=\"https://kit.fontawesome.com/64d58efce2.js\" crossorigin=anonymous></script>\r\n"
				+ "				  <script>\r\n"
				+ "				    Notiflix.Report.Init({ borderRadius:\"10px\", backOverlayColor:\"rgba(255,255,255,0.5)\" });\r\n"
				+ "				    \r\n"
				+ "				    window.addEventListener(\"load\", function(){\r\n"
				+ "				    	 Notiflix.Report.Failure( '"+heading+"', '"+msg+"', 'Okay' );  \r\n"
				+ "				    });\r\n"
				+ "				  </script>\r\n"
				+ "				</head>\r\n"
				+ "				 \r\n"
				+ "				<body >\r\n"
				+ "				 \r\n"
				+ "				</body>\r\n"
				+ "				</html>";
		
		return alert;
		
	}
	
	public String UserAlert(String name)
	{
		
		String alert="<html lang=\"en\">\r\n"
				+ " \r\n"
				+ "<head>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/dist/notiflix-aio-2.7.0.min.js\"></script>\r\n"
				+ "  <script src=\"assets/vendor/Notiflix-2.7.0/src/all-in-one/notiflix-aio.js\"></script>\r\n"
				+ "  <script src=\"https://kit.fontawesome.com/64d58efce2.js\" crossorigin=\"anonymous\"></script>\r\n"
				+ "  <script>\r\n"
				+ "    Notiflix.Report.Init({ borderRadius:\"10px\",  backOverlayColor:\"rgba(0,0,0,0.238)\" });\r\n"				
				+ "    \r\n"
				+ "    window.addEventListener(\"load\", function(){\r\n"
				+ "       Notiflix.Report.Success( 'Hi "+name+"!', 'Welcome back to AngelDoc', 'Okay' );\r\n"
				+ "    });\r\n"
				+ "  </script>\r\n"
				+ "</head>\r\n"
				+ " \r\n"
				+ "<body >\r\n"
				+ " \r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		return alert;
		
	}

}
