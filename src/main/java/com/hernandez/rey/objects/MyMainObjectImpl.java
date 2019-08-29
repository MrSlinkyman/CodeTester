package com.hernandez.rey.objects;


/**
 * MyMainObjectImpl represents ...
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Sep 21, 2009
 * 
 * @todo Complete description
 */
public class MyMainObjectImpl implements MyMainObject
{

   private String m_name;


   public MyMainObjectImpl (final String name)
   {
      m_name = name;
   }


   /**
    * Overrides getName
    * 
    * @return
    * @since Sep 21, 2009
    * @see com.hernandez.rey.objects.MyMainObject#getName()
    */
   public String getName ()
   {
      return m_name;
   }


   /**
    * Overrides setName
    * 
    * @param name
    * @since Sep 21, 2009
    * @see com.hernandez.rey.objects.MyMainObject#setName(java.lang.String)
    */
   public void setName (final String name)
   {
      m_name = name;
   }

}
