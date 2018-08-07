#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.util;

import java.io.IOException;
import java.util.Properties;



public class PropertyLoader {

    private static String path = "${groupId}";
    
    private static String PROP_FILE ="/"+path.replace(".", "/")+"/application.properties";
 
    public static String loadProperty(String name) {

        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

}
