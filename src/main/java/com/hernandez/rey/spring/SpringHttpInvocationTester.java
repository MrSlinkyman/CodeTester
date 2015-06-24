package com.hernandez.rey.spring;


import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import org.apache.log4j.Logger;

import java.lang.reflect.UndeclaredThrowableException;

import java.io.StreamCorruptedException;


/**
 * SpringHttpInvocationTester represents ...
 * 
 * @version $Id$
 * @since Apr 29, 2010
 * 
 * @todo Complete description
 */
public class SpringHttpInvocationTester
{
   private final static transient Logger m_log = Logger.getLogger (SpringHttpInvocationTester.class);


   public static void main (final String[] args)
   {
      // final ClassPathXmlApplicationContext _applicationContext = new ClassPathXmlApplicationContext (
      // new String[] {"springApplicationContext.xml"});

      final HttpInvokerProxyFactoryBean _rey = new HttpInvokerProxyFactoryBean ();
      _rey.setServiceInterface (DoStuff.class);
      _rey.setServiceUrl ("http://cnn.com");
      // <bean id="genericHttpInvocationObject"
      // class="org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean">
      // <property name="serviceUrl"
      // value="http://warService:8080/warContext/httpinvocation/DoStuffService"/>
      // <property name="serviceInterface" value="java.io.Serializable" />
      // </bean>
      _rey.afterPropertiesSet ();
      final DoStuff _reyObject = (DoStuff) _rey.getObject ();
      // final DoStuff _bean = (DoStuff) _applicationContext.getBean ("genericHttpInvocationObject");
      try
      {
         m_log.info (_rey);
         // m_log.info (_bean);
         m_log.info (_reyObject.doStuff ());
      }
      catch (final RemoteAccessException e)
      {
         if (e.getCause () instanceof StreamCorruptedException)
         {
            m_log.warn ("no idea!", e.getCause ());
         }
         else
         {
            m_log.error ("The URL of the service does not exist", e);
         }
      }
      catch (final UndeclaredThrowableException e)
      {
         m_log.info ("The URL of the service looks good", e);
      }

   }
}
