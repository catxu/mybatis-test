package com.catxu.parking.test.xmltransfer;

import com.catxu.parking.test.TestBase;
import com.catxu.parking.test.xmltransfer.bean.*;
import com.catxu.parking.test.xmltransfer.converter.AuthorAttrConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

/**
 * @author kiven
 */
@Slf4j
public class XmlBeanTransferTest extends TestBase {

    @Test
    public void xmlParse() {
        XStream xStream = new XStream(new StaxDriver());
        URL xmlUrl = ClassLoader.getSystemResource("abc.xml");

        File xmlFile = null;
        try {
            xmlFile = new File(xmlUrl.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(xmlFile);
        MyEntity o = (MyEntity) xStream.fromXML(xmlFile, "customMap");
        System.out.println(o);
    }

    @Test
    public void bean2Xml() {
        XStream xstream = new XStream(new DomDriver());
/*        // clear out existing permissions and set own ones
        xstream.addPermission(NoTypePermission.NONE);
        // allow some basics
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);*/
        XStream.setupDefaultSecurity(xstream);
        // allow any type from the same package
        xstream.allowTypesByWildcard(new String[]{
                "com.catxu.parking.**"
        });
        xstream.alias("person", Person.class);
        xstream.alias("phonenumber", PhoneNumber.class);
        Person joe = new Person();
        joe.setFirstname("Joe");
        joe.setLastname("Walnes");
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setCode(123);
        phoneNumber.setNumber("1234-456");
        joe.setPhone(phoneNumber);
        PhoneNumber fax = new PhoneNumber();
        fax.setCode(123);
        fax.setNumber("9999-999");
        joe.setFax(fax);
        String xml = xstream.toXML(joe);
        log.info(xml);
        String phoneNum = xstream.toXML(phoneNumber);
        log.info(phoneNum);
        Person person = (Person) xstream.fromXML(xml);
        log.info("{}", person);
    }

    @Test
    public void aliasTest() {
        Blog teamBlog = new Blog(new Author("Guilherme Silveira"));
        teamBlog.addContent(new Entry("first", "My first blog entry."));
        teamBlog.addContent(new Entry("tutorial",
                "Today we have developed a nice alias tutorial. Tell your friends! NOW!"));

        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
//        xstream.alias("blog", Blog.class);
//        xstream.alias("entry", Entry.class);
//        xstream.aliasField("author", Blog.class, "writer");
//        xstream.addImplicitCollection(Blog.class, "entries");
        String xml = xstream.toXML(teamBlog);
        log.info(xml);
        Blog blog = (Blog) xstream.fromXML(xml);
        log.info("{}", blog);
    }
}
