package com.hernandez.rey;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * RegularExpressionTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Nov 10, 2009
 * 
 * @todo Complete description
 */
public class RegularExpressionTest
{
   private final static transient Logger m_log         = Logger.getLogger (RegularExpressionTest.class);
   public static final Pattern           EMAIL_ADDRESS = Pattern
                                                             .compile ("[\\w._+\\%+-]+\\@[\\w_+\\(+\\)+\\{+\\}+\\~+\\=+\\?+\\|+\\$+\\%+-]+\\.+[\\w]");


   @SuppressWarnings("null")
@Test
   public void testMatches ()
   {
      final String _arg = null;
      assertFalse ("This shouldn't have matched", "-wd".equalsIgnoreCase (_arg));
      assertFalse ("The shouldn't have matched", _arg.matches ("^-{1,2}wd$"));
   }


   @Test
   public void testEmailPattern ()
   {
      final String _email = "rey@hernandez.r$";
      m_log.info (String.format ("The pattern to match %s", EMAIL_ADDRESS.pattern ()));
      final Matcher _matcher = EMAIL_ADDRESS.matcher (_email);

      assertTrue ("The email should be valid", _matcher.find ());
   }
}
