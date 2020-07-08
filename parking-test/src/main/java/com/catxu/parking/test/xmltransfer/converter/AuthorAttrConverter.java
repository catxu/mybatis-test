package com.catxu.parking.test.xmltransfer.converter;

import com.catxu.parking.test.xmltransfer.bean.Author;
import com.catxu.parking.test.xmltransfer.bean.Blog;
import com.catxu.parking.test.xmltransfer.bean.Entry;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author kiven
 */
public class AuthorAttrConverter implements Converter {

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Blog blog = (Blog) source;
        if (blog.getWriter() != null) {
            writer.addAttribute("author", blog.getWriter().getName());
        }
        if (blog.getEntries() != null) {
            context.convertAnother(blog.getEntries());
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String authorAttr = reader.getAttribute("author");
        if (authorAttr != null) {
            Author author = new Author(authorAttr);
            Blog blog = new Blog(author);
            while (reader.hasMoreChildren()) {
                reader.moveDown();
                if ("entry".equals(reader.getNodeName())) {
                    Entry person = (Entry)context.convertAnother(blog, Entry.class);
                    blog.addContent(person);
                }
                reader.moveUp();
            }
            return blog;
        }
        return null;
    }

    @Override
    public boolean canConvert(Class type) {
        return type == Blog.class;
    }
}
