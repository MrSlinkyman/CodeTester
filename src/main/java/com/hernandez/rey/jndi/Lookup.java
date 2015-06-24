package com.hernandez.rey.jndi;


import com.hernandez.rey.spring.DoStuff;

import org.codehaus.xfire.spring.remoting.XFireClientFactoryBean;
import org.jnp.interfaces.NamingContext;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import org.apache.log4j.Logger;

import java.lang.reflect.UndeclaredThrowableException;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;


/**
 * Lookup represents ...
 * 
 * @version $Id$
 * @since Apr 22, 2010
 * 
 * @todo Complete description
 */
public class Lookup
{
   private final static transient Logger m_log = Logger.getLogger (Lookup.class);


   /**
    * Represents main
    * 
    * @param args
    * @since Apr 22, 2010
    * 
    * @todo complete description
    */
   public static void main (final String[] args)
   {
      try
      {
         // Create a Properties object and set properties appropriately
         final List <String> _jndiUrls = new ArrayList <String> ();
         _jndiUrls.add ("jnp://jndi-server:1099");

         final Map <String, String> _pairs = new HashMap <String, String> ();

         for (final String _jndiUrl : _jndiUrls)
         {
            extractAllBindings (_jndiUrl, _pairs);
         }

         for (final String _key : new TreeSet <String> (_pairs.keySet ()))
         {
            final String _url = _pairs.get (_key);
            System.out.println (String.format ("%s : %s", _key, _url));
            // doesServiceExist (_url);
         }
      }
      catch (final NamingException nnfe)
      {
         m_log.info ("Encountered a naming exception", nnfe);
      }
   }


   /**
    * Represents doesServiceExist
    * 
    * @param url
    * @since Jul 2, 2010
    * 
    * @todo complete description
    */
   @SuppressWarnings("unused")
private static void doesServiceExist (final String url)
   {
      if (url.startsWith ("http"))
      {
         if (doesServiceExistAsHttp (url))
         {
            System.out.println ("\texists as http");
         }
         else if (doesServiceExistAsSoap (url))
         {
            System.out.println ("\texists as soap");
         }
         else
         {
            System.out.println ("\tdoes not exist");
         }
      }
   }


   /**
    * Represents extractAllBindings
    * 
    * @param properties
    * @return
    * @throws NamingException
    * @since Apr 28, 2010
    * 
    * @todo complete description
    */
   private static Map <String, String> extractAllBindings (final String jndiUrl, final Map <String, String> _pairs)
         throws NamingException
   {
      final Properties _contextProperties = new Properties ();
      _contextProperties.put (Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
      _contextProperties.put (Context.PROVIDER_URL, jndiUrl);

      // Create the initial context from the properties we just created
      final Context _initialContext = new InitialContext (_contextProperties);

      final Set <String> _names = new TreeSet <String> ();
      final Hashtable <?, ?> _table = _initialContext.getEnvironment ();
      m_log.info (_table);

      _names.addAll (extractBindings (_initialContext, ""));
      for (final String _name : _names)
      {
         final Object _obj = _initialContext.lookup (_name);
         if (_obj instanceof String)
         {
            String _key = _name;
            final String _value = (String) _obj;
            while (_pairs.containsKey (_key) && !_pairs.get (_key).equals (_value))
            {
               _key += "_DUP";
            }

            _pairs.put (_key, _value);
         }
      }
      _initialContext.close ();
      return _pairs;
   }


   /**
    * Represents extractBindings
    * 
    * @param initialContext
    * @param names
    * @param pair
    * @throws NamingException
    * @since Apr 22, 2010
    * 
    * @todo complete description
    */
   private static Set <String> extractBindings (final Context initialContext, final String name) throws NamingException
   {
      final Set <String> _names = new TreeSet <String> ();
      for (final NamingEnumeration <NameClassPair> _fp = initialContext.list (name); _fp.hasMore ();)
      {
         final NameClassPair _pairp = _fp.next ();
         final String _rootPart = name + "/" + _pairp.getName ();
         if (_pairp.getClassName ().equals (NamingContext.class.getName ()))
         {
            _names.addAll (extractBindings (initialContext, _rootPart));
         }
         else if (_pairp.getClassName ().equals (String.class.getName ()))
         {
            _names.add (_rootPart);
         }
      }
      return _names;
   }


   private static boolean doesServiceExistAsHttp (final String url)
   {
      final HttpInvokerProxyFactoryBean _rey = new HttpInvokerProxyFactoryBean ();
      _rey.setServiceInterface (DoStuff.class);
      _rey.setServiceUrl (url);
      // <bean id="genericHttpInvocationObject"
      // class="org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean">
      // <property name="serviceUrl"
      // value="http://war-server:8080/warContext/httpinvocation/DoStuffService"/>
      // <property name="serviceInterface" value="java.io.Serializable" />
      // </bean>
      _rey.afterPropertiesSet ();
      final DoStuff _reyObject = (DoStuff) _rey.getObject ();
      return doesServiceExist (_reyObject);
   }


   /**
    * Represents doesServiceExist
    * 
    * @param object
    * @return
    * @since Apr 30, 2010
    * 
    * @todo complete description
    */
   private static boolean doesServiceExist (final DoStuff object)
   {
      boolean _return = false;
      // final DoStuff _bean = (DoStuff) _applicationContext.getBean ("genericHttpInvocationObject");
      try
      {
         // m_log.info (_rey);
         // m_log.info (_bean);
         object.doStuff ();
      }
      catch (final RemoteAccessException e)
      {
         if (e.getCause () instanceof StreamCorruptedException)
         {
            m_log.warn ("\tno idea!", e.getCause ());
         }
         else
         {
            _return = false;
            // System.out.println ("\t" + url + ": does not exist");
         }
      }
      catch (final UndeclaredThrowableException e)
      {
         _return = true;
         // System.out.println ("\t" + url + ": looks good");
      }
      return _return;
   }


   private static boolean doesServiceExistAsSoap (final String url)
   {

      final XFireClientFactoryBean _factory = new XFireClientFactoryBean ();
      _factory.setServiceClass (DoStuff.class);
      _factory.setWsdlDocumentUrl (url + "?WSDL");
      boolean _return = false;
      try
      {
         _factory.afterPropertiesSet ();
         _return = doesServiceExist ((DoStuff) _factory.getObject ());
      }
      catch (final org.codehaus.xfire.XFireRuntimeException e)
      {
         _return = true;
      }
      catch (final FileNotFoundException e)
      {
         _return = false;
      }
      catch (final java.net.SocketException e)
      {
         _return = false;
      }
      catch (final Exception e)
      {
         _return = false;
         m_log.info ("Bad!", e);
      }
      return _return;
   }


}
