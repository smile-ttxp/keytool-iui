/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
 * LGPL License.
 * http://code.google.com/p/keytool-iui/
 *
 * This software is the confidential and proprietary information of RagingCat Project.
 * You shall not disclose such confidential information and shall use it only in
 * accordance with the terms of RagingCat Project's license agreement.
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
 
 
package com.google.code.p.keytooliui.javax.media.rtp;

/**
    MEMO:
        . one port should have at most one IP
        . one IP could have more than one port




**/


import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;
import java.util.*;

final public class Port
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.javax.media.rtp.Port.";
    
    // --------------
    // STATIC PRIVATE
    
    static private Vector<Integer> _s_vecItgPortOpen = null;
    
    static private Map<Integer, String> _s_hmpItgPort2StrIP = null;
    static private Map<Integer, Component> _s_hmpItgPort2CmpViewer = null; // eg: JFrame, page in RCReader
    
    // -------------
    // STATIC PUBLIC
    
    static public Component s_getViewer(int intPort)
    {
        String strMethod = _f_s_strClass + "s_getViewer(...)";
        
        if (_s_vecItgPortOpen == null)
            return null;
            
        if (_s_hmpItgPort2CmpViewer == null)
            return null;
            
        // --
        for (int i=0; i<_s_vecItgPortOpen.size(); i++)
        {
            Integer itgCur = (Integer) _s_vecItgPortOpen.elementAt(i);
            
            if (itgCur.intValue() != intPort)
                continue;
                
            //
            
            if (! _s_hmpItgPort2CmpViewer.containsKey(itgCur))
                return null;
                
            return (Component) _s_hmpItgPort2CmpViewer.get(itgCur);
        }
        
        return null;
    }
    
    static public boolean s_setViewer(int intPort, Component cmpViewer)
    {
        String strMethod = _f_s_strClass + "s_setViewer(...)";
        
        if (_s_vecItgPortOpen == null)
        {
            MySystem.s_printOutError(strMethod, "nil _s_vecItgPortOpen");
            return false;
        }
        
        // --
        for (int i=0; i<_s_vecItgPortOpen.size(); i++)
        {
            Integer itgCur = (Integer) _s_vecItgPortOpen.elementAt(i);
            
            if (itgCur.intValue() != intPort)
                continue;
                
            // do job
            if (_s_hmpItgPort2CmpViewer == null)
            {
                _s_hmpItgPort2CmpViewer = Collections.synchronizedMap(new HashMap<Integer, Component>());
	            _s_hmpItgPort2CmpViewer.put(itgCur, cmpViewer);
	            return true;
            }
            
            if (_s_hmpItgPort2CmpViewer.containsKey(itgCur))
            {
                MySystem.s_printOutError(strMethod, "_s_hmpItgPort2CmpViewer.containsKey(itgCur), intPort=" + intPort);
                return false;
            }
            
            _s_hmpItgPort2CmpViewer.put(itgCur, cmpViewer);
            
            // ending
            return true;
        }
        
        MySystem.s_printOutError(strMethod, "port not found, intPort=" + intPort);
        return false;
    }
    
    static public boolean s_setOpen(int intPort, String strIP)
    {
        String strMethod = _f_s_strClass + "s_setOpen(...)";
        
        if (_s_vecItgPortOpen == null)
        {
            _s_vecItgPortOpen = new Vector<Integer>();
            Integer itg = new Integer(intPort);
            
            _s_vecItgPortOpen.addElement(itg);
            
            if (_s_hmpItgPort2StrIP == null)
                _s_hmpItgPort2StrIP = Collections.synchronizedMap(new HashMap<Integer, String>());
        
	        _s_hmpItgPort2StrIP.put(itg, strIP);
            
            return true;
        }
        
        // --
        for (int i=0; i<_s_vecItgPortOpen.size(); i++)
        {
            Integer itgCur = (Integer) _s_vecItgPortOpen.elementAt(i);
            
            if (itgCur.intValue() != intPort)
                continue;
                
            // already in use
            MySystem.s_printOutWarning(strMethod, "port already open, intPort=" + intPort);
            return false;
        }
        
        
        Integer itg = new Integer(intPort);
            
        _s_vecItgPortOpen.addElement(itg);
            
        if (_s_hmpItgPort2StrIP == null)
            _s_hmpItgPort2StrIP = Collections.synchronizedMap(new HashMap<Integer, String>());
        
	    _s_hmpItgPort2StrIP.put(itg, strIP);
	        
	        
        return true;
    }
    
    static public String s_getIP(int intPort)
    {
        String strMethod = _f_s_strClass + "s_getIP(...)";
        
        if (_s_vecItgPortOpen == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil _s_vecItgPortOpen");
            return null;
        }
        
        // --
        for (int i=0; i<_s_vecItgPortOpen.size(); i++)
        {
            Integer itgCur = (Integer) _s_vecItgPortOpen.elementAt(i);
            
            if (itgCur.intValue() != intPort)
                continue;
            
            //update collection
            if (_s_hmpItgPort2StrIP == null)
            {
                MySystem.s_printOutError(strMethod, "nil _s_hmpItgPort2StrIP");
                return null;
            }
            
            if (! _s_hmpItgPort2StrIP.containsKey(itgCur))
            {
                MySystem.s_printOutError(strMethod, "! _s_hmpItgPort2StrIP.containsKey(itgCur), itgCur.intValue()=" + itgCur.intValue());
                return null;
            }
            
            return (String) _s_hmpItgPort2StrIP.get(itgCur);
        }
        
        MySystem.s_printOutError(strMethod, "port not found, intPort=" + intPort);
        return null;
    }
    
    static public boolean s_setClosed(int intPort)
    {
        String strMethod = _f_s_strClass + "s_setClosed(...)";
        
        if (_s_vecItgPortOpen == null)
        {
            MySystem.s_printOutWarning(strMethod, "nil _s_vecItgPortOpen");
            return false;
        }
        
        // --
        for (int i=0; i<_s_vecItgPortOpen.size(); i++)
        {
            Integer itgCur = (Integer) _s_vecItgPortOpen.elementAt(i);
            
            if (itgCur.intValue() != intPort)
                continue;
                
            _s_vecItgPortOpen.remove(i);
            
            //update collection
            if (_s_hmpItgPort2StrIP == null)
            {
                MySystem.s_printOutError(strMethod, "nil _s_hmpItgPort2StrIP");
                return false;
            }
            
            if (! _s_hmpItgPort2StrIP.containsKey(itgCur))
            {
                MySystem.s_printOutError(strMethod, "! _s_hmpItgPort2StrIP.containsKey(itgCur), itgCur.intValue()=" + itgCur.intValue());
                return false;
            }
            
            _s_hmpItgPort2StrIP.remove(itgCur);
            
            //
            if (_s_hmpItgPort2CmpViewer != null)
            {
                if (_s_hmpItgPort2CmpViewer.containsKey(itgCur))
                {
                    _s_hmpItgPort2CmpViewer.remove(itgCur);
                }
            }
            
            
            
            return true;
        }
        
        MySystem.s_printOutError(strMethod, "port not found, intPort=" + intPort);
        return false;
    }
    
    static public void s_setCloseAll()
    {
        if (_s_vecItgPortOpen != null)
        {
            _s_vecItgPortOpen.clear();
            _s_vecItgPortOpen = null;
        }
        
        if (_s_hmpItgPort2StrIP != null)
        {
            _s_hmpItgPort2StrIP.clear();
            _s_hmpItgPort2StrIP = null;
        }
        
        if (_s_hmpItgPort2CmpViewer != null)
        {
            _s_hmpItgPort2CmpViewer.clear();
            _s_hmpItgPort2CmpViewer = null;
        }
    }
    
    static public boolean s_isOpen(int intPort)
    {
        if (_s_vecItgPortOpen  == null)
            return false;
        
        for (int i=0; i<_s_vecItgPortOpen.size(); i++)
        {
            Integer itgCur = (Integer) _s_vecItgPortOpen.elementAt(i);
          
            if (itgCur.intValue() == intPort)
                return true;
        }
        
        return false;
    }
}