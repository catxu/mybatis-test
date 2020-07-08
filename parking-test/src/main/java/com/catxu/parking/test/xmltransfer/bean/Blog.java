package com.catxu.parking.test.xmltransfer.bean;

import com.catxu.parking.test.xmltransfer.converter.AuthorAttrConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kiven
 */
@XStreamAlias("blog")
@XStreamConverter(AuthorAttrConverter.class)
public class Blog {
    @XStreamAlias("author")
    @XStreamAsAttribute
    private Author writer;
    @XStreamImplicit
    private List<Entry> entries = new ArrayList<>();

    public Blog(Author writer) {
        this.writer = writer;
    }

    public void addContent(Entry entry) {
        this.entries.add(entry);
    }

    public Author getWriter() {
        return writer;
    }

    public void setWriter(Author writer) {
        this.writer = writer;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "author=" + writer +
                ", entries=" + entries +
                '}';
    }
}
