package com.catxu.parking.test.xmltransfer.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author kiven
 */
@XStreamAlias("entry")
public class Entry {
    private String title;
    private String description;

    public Entry(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
