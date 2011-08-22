/*
 *
 * Copyright (c)  2001-2011 keyTool IUI Project.
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
 
package com.google.code.p.keytooliui.shared.swing.menu;

/**
    known subclasses:
    . rcr: MProjCurRdrAbs
    . shared_gen: MProjCurGenAbs
    
    memo: text may be:
    . active project (xlb)
    . active document (rcr)
    
**/


import com.google.code.p.keytooliui.shared.lang.*;


public abstract class MProjCurAbs extends MAbstract
{
    
    
    // ---------------
    // ABSTRACT PUBLIC
    
    public abstract boolean init();
    public abstract void destroy();
    
    // ---------
    // PROTECTED
   
    
    protected MProjCurAbs(String strTextThis, String strIcon)
    {
        String strMethod = "MProjCurAbs(strTextThis, strIcon)";
        
        if (strTextThis == null)
            MySystem.s_printOutExit(this, strMethod, "nil strTextThis");
            
        setText(strTextThis);
        
        if (strIcon == null)
            MySystem.s_printOutExit(this, strMethod, "nil strIcon");
            
        javax.swing.ImageIcon iin = 
            com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get(strIcon);
        
        if (iin == null)
            MySystem.s_printOutExit(this, strMethod, "nil iin");
        
        setIcon(iin);
    }
}