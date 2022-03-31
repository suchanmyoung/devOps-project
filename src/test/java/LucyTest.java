import com.nhncorp.lucy.security.xss.XssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;
import com.nhncorp.lucy.security.xss.XssSaxFilter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LucyTest {

    @Test
    public void testXssPreventer(){
            String dirty = "\"><script>alert('xss');</script>";
            String clean = XssPreventer.escape(dirty);

            assertEquals(clean, "&quot;&gt;&lt;script&gt;alert(&#39;xss&#39;);&lt;/script&gt;");
            assertEquals(dirty, XssPreventer.unescape(clean));
    }

    @Test
    public void pairQuoteCheckOtherCase() {
        XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
        String dirty = "<img src=\"<img src=1\\ onerror=alert(1234)>\" onerror=\"alert('XSS')\">";
        String expected = "<img src=\"\"><!-- Not Allowed Attribute Filtered ( onerror=alert(1234)) --><img src=1\\>\" onerror=\"alert('XSS')\"&gt;";
        String actual = filter.doFilter(dirty);
        assertEquals(expected, actual);

        dirty = "<img src='<img src=1\\ onerror=alert(1234)>\" onerror=\"alert('XSS')\">";
        expected = "<img src=''><!-- Not Allowed Attribute Filtered ( onerror=alert(1234)) --><img src=1\\>\" onerror=\"alert('XSS')\"&gt;";
        actual = filter.doFilter(dirty);
        assertEquals(expected, actual);
    }
}
