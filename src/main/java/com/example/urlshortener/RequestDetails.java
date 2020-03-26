package com.example.urlshortener;

import java.time.YearMonth;

public class RequestDetails {
    private String url;
    private String custom_code="NONE";
    private YearMonth ym= YearMonth.of(3000,12);//default value

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCustom_code() {
        return custom_code;
    }

    public void setCustom_code(String custom_code) {
        this.custom_code = custom_code;
    }

    public YearMonth getYm() {
        return ym;
    }

    public void setYm(YearMonth ym) {
        this.ym = ym;
    }
}
