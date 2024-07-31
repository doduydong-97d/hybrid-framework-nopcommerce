package common;

import java.io.File;

public class GlobalConstants {

	public static final String USER_DEV_URL = "https://dev.nopcommerce.com/";
	public static final String USER_TEST_URL = "https://test.nopcommerce.com/";
	public static final String USER_STAG_URL = "https://staging.nopcommerce.com/";
	public static final String USER_DEMO_URL = "https://demo.nopcommerce.com/";
	public static final String USER_PROD_URL = "https://prod.nopcommerce.com/";

	public static final String ADMIN_DEV_URL = "https://dev.nopcommerce.com/";
	public static final String ADMIN_TEST_URL = "https://test.nopcommerce.com/";
	public static final String ADMIN_STAG_URL = "https://staging.nopcommerce.com/";
	public static final String ADMIN_DEMO_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PROD_URL = "https://prod.nopcommerce.com/";

	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String FILE_SEP = File.separator;
	public static final String UPLOAD_FILES_FOLDER = PROJECT_PATH + FILE_SEP + "uploadFiles" + FILE_SEP;

	public static final int LONG_TIMEOUT = 30;
	public static final int SHORT_TIMEOUT = 5;
	public static final int ONE_SEC = 1;

}
