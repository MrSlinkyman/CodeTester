/*
 * Copyright © 2000 - 2008 Sony Network Entertainment. All rights reserved.
 */
package com.hernandez.rey.objects;


/**
 * MySubObjectImpl represents an object that extends MyMainObjectImpl purely to test out inheritance
 * 
 * @author <a href="mailto:Rey.Hernandez@am.sony.com">Rey Hernandez</a>
 * @version $Id$
 * @since Sep 21, 2009
 *
 */
public class MySubObjectImpl extends MyMainObjectImpl implements MySubObject
{

   /**
    * Constructs an instance of MySubObjectImpl object.
    * 
    * @param name
    */
   public MySubObjectImpl (final String name)
   {
      super (name);

   }
}
