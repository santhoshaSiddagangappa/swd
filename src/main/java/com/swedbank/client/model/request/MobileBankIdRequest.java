package com.swedbank.client.model.request;

public class MobileBankIdRequest {

    private boolean generateEasyLoginId;
    private boolean useEasyLogin;
    private String userId;

    @Override
    public String toString() {
        return "MobilRequest{" +
                "generateEasyLoginId='" + generateEasyLoginId + '\'' +
                ", useEasyLogin='" + useEasyLogin + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public MobileBankIdRequest(String userId) {
        this.userId = userId;
        this.generateEasyLoginId = false;
        this.useEasyLogin = false;
    }

    public boolean isGenerateEasyLoginId() {
        return generateEasyLoginId;
    }

    public void setGenerateEasyLoginId(boolean generateEasyLoginId) {
        this.generateEasyLoginId = generateEasyLoginId;
    }

    public boolean isUseEasyLogin() {
        return useEasyLogin;
    }

    public void setUseEasyLogin(boolean useEasyLogin) {
        this.useEasyLogin = useEasyLogin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public MobileBankIdRequest() {
    }
}
