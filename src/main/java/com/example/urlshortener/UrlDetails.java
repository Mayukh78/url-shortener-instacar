package com.example.urlshortener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.YearMonth;

@Entity
@Table(name = "urldetails")
public class UrlDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @NotNull
    @Column(name = "Url",unique = true)
    private String url;

    @NotNull
    @Column(name = "Shortened_Code",unique = true)
    @Size(min=7,message = "Code should be minimum 7 characters long")
    private String code;

    @Column(name="Expiration_Time")
    private YearMonth ym;

    public YearMonth getYm() {
        return ym;
    }

    public void setYm(YearMonth ym) {
        this.ym = ym;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
