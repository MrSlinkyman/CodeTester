/*
 * Copyright © 2000 - 2008 Sony Network Entertainment. All rights reserved.
 */
package com.hernandez.rey;


import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;


/**
 * MatcherTest represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Jul 5, 2011
 * 
 * @todo Complete description
 */
public class MatcherTest
{

   /**
    * Test method for {@link java.util.regex.Matcher#quoteReplacement(java.lang.String)}.
    */
   @Test
   public void testQuoteReplacementNullValue ()
   {
      final String _nullString = Matcher.quoteReplacement (null);
      Assert.assertNotNull ("This is bad", _nullString);
   }

}
