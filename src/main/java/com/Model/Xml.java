package com.Model;

public class Xml {
    private String title;
    private String link;
    private String pubDate;
    private String category;
    private String guid;
    private String description;

    public Xml(String title, String link, String pubDate, String category, String guid, String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.category = category;
        this.guid = guid;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "XmlPojo{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", category='" + category + '\'' +
                ", guid='" + guid + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}