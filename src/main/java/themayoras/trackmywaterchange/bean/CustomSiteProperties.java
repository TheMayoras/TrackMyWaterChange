package themayoras.trackmywaterchange.bean;

import org.springframework.beans.factory.annotation.Value;


public class CustomSiteProperties {
    @Value("${website.title}")
    private String siteName;

    public String getSiteName() {
        return siteName;
    }


}
