package shpp.mentor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class App {

    public static class Account {
        public String message;

        public Account( String un) {
            message = un;
        }
    }
    public  static final Logger logger = LoggerFactory.getLogger(App.class);

    public  static  void main(String[] args ) throws IOException {
        Properties myProp = new Properties();
        try {
        FileInputStream input = new FileInputStream("myProp.properties");
        myProp.load(input);
        String filename ;
        ObjectMapper oM;
        Account newAccount = new Account("Hello all,"+myProp.getProperty("username"));
        if (Objects.equals(System.getProperty("type"), "xml")) {
           oM = new XmlMapper();
            filename = "account.xml";
        } else {
            oM = new ObjectMapper();
            filename = "account.json";
        }
        oM.writeValue(new File(filename), newAccount );
        logger.info(oM.writeValueAsString(newAccount));
        }  catch (IOException e){
          logger.error("myProp.properties not found! ",e);
        }
    }
}
