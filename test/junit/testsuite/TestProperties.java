package junit.testsuite;

import java.io.*;
import java.util.Map;
import junit.io.XMLReader;

/**
 * DB接続設定や設定ファイルなど環境依存情報を保持するクラスになります。<br>
 * プロジェクト直下にconfig.xmlファイルを配備してください。
 * 
 * @author cobot00
 */
public class TestProperties {
    
    private static final String XML_URI = "config.xml";    
    private static final String KEY_JDBCURL= "jdbcurl";
    private static final String KEY_DB_USERNAME= "db-username";
    private static final String KEY_DB_PASSWORD= "db-password";
    private static final String KEY_DB_TYPE= "db-type";
    private static final String KEY_CSV_ROOT_DIR= "csv-root-dir";
    
    private static final TestProperties instance = new TestProperties();
    
    private String jdbcurl;
    private String dbUsername;
    private String dbPassword;
    private String dbType;
    private String csvRoootDir;
    
    private TestProperties() {
        init();
    }
    
    private void init() {
            final Map<String, String> result = new XMLReader(XML_URI).parse();
            
            jdbcurl = result.get(KEY_JDBCURL);
            dbUsername = result.get(KEY_DB_USERNAME);
            dbPassword = result.get(KEY_DB_PASSWORD);
            dbType = result.get(KEY_DB_TYPE);
            csvRoootDir = result.get(KEY_CSV_ROOT_DIR);
    }
    
    public static TestProperties getInstance(){
        return instance;
    }

    /**
     * @return jdbcurl
     */
    public String getJdbcurl() {
        return jdbcurl;
    }

    /**
     * @return dbUsername
     */
    public String getDbUsername() {
        return dbUsername;
    }

    /**
     * @return dbPassword
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * @return dbType
     */
    public String getDbType() {
        return dbType;
    }
    
    /**
     * @return csvRoootDir
     */
    public String getCsvRootDir() {
        return csvRoootDir;
    }
}
