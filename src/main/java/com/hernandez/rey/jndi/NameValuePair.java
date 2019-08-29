package com.hernandez.rey.jndi;


/**
 * NameValuePair represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Apr 28, 2010
 * 
 * @todo Complete description
 */
public class NameValuePair
{
   @SuppressWarnings("unused")
private String m_name;
   private String m_value;


   /**
    * @return the value
    */
   public String getValue ()
   {
      return m_value;
   }


   /**
    * @param value the value to set
    */
   public void setValue (final String value)
   {
      m_value = value;
   }
}
