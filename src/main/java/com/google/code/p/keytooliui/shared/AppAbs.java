/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 *
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of keyTool IUI Project's license agreement.
 *
 * THE SOFTWARE IS PROVIDED AND LICENSED "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * LICENSE FOR THE SOFTWARE DOES NOT INCLUDE ANY CONSIDERATION FOR ASSUMPTION OF RISK
 * BY KEYTOOL IUI PROJECT, AND KEYTOOL IUI PROJECT DISCLAIMS ANY AND ALL LIABILITY FOR INCIDENTAL
 * OR CONSEQUENTIAL DAMAGES ARISING OUT OF THE USE OR OPERATION OF OR INABILITY
 * TO USE THE SOFTWARE, EVEN IF KEYTOOL IUI PROJECT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES. 
 *
 */
 
 
package com.google.code.p.keytooliui.shared;

/**
    "App" means "Application"


    known subclasses:
    
    . AppMainAbs
**/

public abstract class AppAbs extends Object
{
    // -------------------
    // public static final
    
    public static final String F_STR_propKeyLauncherNetbeansModule = "launcher_netbeans_module";
    public static final String F_STR_propKeyLauncherEclipsePlugin = "launcher_eclipse_plugin";
    
    // --------------------
    // private static final
    
    // trick!
    private static final String _F_STR_propKeyLauncherJWS = "javawebstart.version";
    
    // -------------
    // PUBLIC STATIC
    
    /**
        tempo! for dev only
    **/
    public static boolean DUMMY_s_isDeployedWithJws()
    {
        String str = System.getProperty("dummy_" + AppAbs._F_STR_propKeyLauncherJWS);
        
        if (str == null)
            return false;
            
        if (str.trim().length() < 1)
            return false;
        
        
        return true;
    }
    
    public static boolean s_isDeployedWithEcp() // Eclipse plugin
    {
        String str = System.getProperty(AppAbs.F_STR_propKeyLauncherEclipsePlugin);
        
        if (str == null)
            return false;
            
        if (str.trim().length() < 1)
            return false;
        
        if (str.toLowerCase().compareTo("true") == 0)
            return true;
        
        return false;
    }
    
    public static boolean s_isDeployedWithNbm() // NetBeans IDE module
    {
        String str = System.getProperty(AppAbs.F_STR_propKeyLauncherNetbeansModule);
        
        if (str == null)
            return false;
            
        if (str.trim().length() < 1)
            return false;
        
        if (str.toLowerCase().compareTo("true") == 0)
            return true;
        
        return false;
    }
    
    /**
        javawebstart.version
    **/
    public static boolean s_isDeployedWithJws()
    {
        String str = System.getProperty(AppAbs._F_STR_propKeyLauncherJWS);
        
        if (str == null)
            return false;
            
        if (str.trim().length() < 1)
            return false;
        
        com.google.code.p.keytooliui.shared.lang.MySystem.s_printOutTrace(AppAbs._F_STR_propKeyLauncherJWS + "=", str);
        
        return true;
    }
    
    
    // ---------
    // PROTECTED
    
    protected AppAbs()
    {
        // just testing, drawing a border onMouseOver
         //Toolkit.getDefaultToolkit().addAWTEventListener(new DebugEventListener(), AWTEvent.MOUSE_EVENT_MASK);
    }
}