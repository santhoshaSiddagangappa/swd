package com.swedbank.client.model.response;

public class BankIdAuthenticationResponse extends AbstractAuthenticationResponse{

    private String status;
    private Links links;
    private boolean extendedUsage;
    private String serverTime;
    private String formattedServerTime;
    private String maskedUserId;

    public BankIdAuthenticationResponse() {
    }

    public String getMaskedUserId() {
        return maskedUserId;
    }

    public void setMaskedUserId(String maskedUserId) {
        this.maskedUserId = maskedUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public boolean isExtendedUsage() {
        return extendedUsage;
    }

    public void setExtendedUsage(boolean extendedUsage) {
        this.extendedUsage = extendedUsage;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getFormattedServerTime() {
        return formattedServerTime;
    }

    public void setFormattedServerTime(String formattedServerTime) {
        this.formattedServerTime = formattedServerTime;
    }

    @Override
    public String toString() {
        return "BankIdAuthenticationResponse{" +
                "status='" + status + '\'' +
                ", links=" + links +
                ", extendedUsage=" + extendedUsage +
                ", serverTime='" + serverTime + '\'' +
                ", formattedServerTime='" + formattedServerTime + '\'' +
                ", maskedUserId='" + maskedUserId + '\'' +
                '}';
    }
}
