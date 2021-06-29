package com.swedbank.client;

public class Constants {

    public static final String BASE_URL = "https://online.swedbank.se/TDE_DAP_Portal_REST_WEB/api";
    public static final String HEADER_AUTH_KEY = "QjdkWkhRY1k3OFZSVno5bDoxNTkyMzE2NjM5MDIy";
    public static final String HEADER_X_CLIENT = "fdp-nib/169.1.0";
    public static final String HEADER_HOST = "online.swedbank.se";
    public static final String HEADER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36 OPR/68.0.3618.125";

    public static final String CLIENT_SESSION_ENDPOINT_V5 = "/v5/framework/clientsession";
    public static final String IDENTIFICATION_ENDPOINT_V5 = "/v5/identification/";

    public static final String HEADER_CONTENT_TYPE = "application/json";
    public static final String HEADER_ACCEPT_LANGUAGE_ENGLISH = "en";
    public static final String HEADER_ACCEPT_LANGUAGE_SVENSKA = "sv";
    public static final String HEADER_SEC_FETCH_MODE = "cors";
    public static final String HEADER_SEC_FETCH_SITE = "same-origin";

    public static final String BANK_ID_PATTERN = "[1-2]{1}[0-9]{7}-[1-9]{4}";

    public static final String AUTHENTICATOR_MOBILE_BANKID = "BANKID_MOBILE";
    public static final int MAX_VERIFY_COUNT = 10;
    public static final int VERIFY_INTERVAL_MILLIS = 1000;

    public static final String BANK_ID_WAITING_CODE = "CLIENT_NOT_STARTED";
    public static final String BANK_ID_SUCCESS_CODE = "COMPLETE";
}
