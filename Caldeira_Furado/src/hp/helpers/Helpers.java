package hp.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {
	
	public static String DateToString(Date data) {		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
        String strDate = dateFormat.format(data);
		
        return strDate;
		
	}

}
