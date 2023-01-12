package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.util.Date;

public class AnswerNasa {
    private final String copyright;
    private final Date data;
    private final String explanation;
    private final URL HDurl;
    private final String mediaType;
    private final String sVersion;
    private final String title;
    private final URL url;

    public AnswerNasa(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") Date data,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") URL Hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String sVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") URL url) {

        this.copyright = copyright;
        this.data = data;
        this.explanation = explanation;
        this.HDurl = Hdurl;
        this.mediaType = mediaType;
        this.sVersion = sVersion;
        this.title = title;
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public String urlName() {
        return url.toString().substring(url.toString().lastIndexOf('/') + 1);
    }

    @Override
    public String toString() {
        return "AnswerNasa{" +
                "copyright='" + copyright + '\'' +
                ",\ndata=" + data +
                ",\nexplanation='" + explanation + '\'' +
                ",\nHDurl=" + HDurl +
                ",\nmediaType=" + mediaType +
                ",\nsVersion='" + sVersion + '\'' +
                ",\ntitle='" + title + '\'' +
                ",\nurl=" + url +
                '}';
    }
}
