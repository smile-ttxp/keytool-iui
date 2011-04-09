/*
 *
 * Copyright (c) 2001-2007 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.shared.swing.frame;

/**
    known subclasses:
    . FMainAbs ==> main applis
    . FViewerHtmlSys ==> ?TO BE REPLACED by FSwUrlTextHTML?
    . FSwAbs ==> "Sw" means "Secondary Window"
    . FMainJH ==> JavaHelp
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.swing.*;

import java.awt.*;

public abstract class FAbs extends JFrame
{
    // ------
    // PUBLIC
    
    public void setVisible(boolean bln)
    {
        if (bln == true)
            _setVisibleTrue();
            
        super.setVisible(bln);
    }
    
    
    
    
    // overwriting superclass's method
    public void setTitle(String str)
    {
        String strMethod = "setTitle(str)";
        
        if (str == null)
            MySystem.s_printOutExit(this, strMethod, "nil str");
        
        str = str.trim();
        
        if (str.length() < 1)
            MySystem.s_printOutExit(this, strMethod, "str.length() < 1");
        
        super.setTitle(str);
    }
    
    public boolean init()
    {
        
        return true;
    }
    
    public void destroy()
    {
        String strMethod = "destroy()";
        MySystem.s_printOutFlagDev(this, strMethod, "(reinstalled) #### CODE IN COMMENTS #### if (isVisible()) setVisible(false)");
        
   
        if (isVisible())
            setVisible(false);
    }
    
    // ---------
    // PROTECTED
    

    
    protected FAbs(
      
        Image imgIcon
        )
    {
        super(System.getProperty("_appli.title"));
        
  
        
        if (imgIcon != null)
            setIconImage(imgIcon);
    }
    
    // -------
    // PRIVATE
    
    
    /*
        1) pack
        2) if frame's width or frame's height bigger than respective screen's width or heigth, fit screen
        3) redirect to superclass
    */
    private void _setVisibleTrue()
    {
        String strMethod = "_setVisibleTrue()";
        
        // get screen size
        Dimension dimScreen = null;
        
        try
        {
            Toolkit tkt = Toolkit.getDefaultToolkit();
            dimScreen = tkt.getScreenSize();
        }
        
        catch(AWTError errAWT)
        {
            errAWT.printStackTrace();
            MySystem.s_printOutExit(this, strMethod, "errAWT caught");
        }
        
        Insets ins = getInsets();
        
        int intW = dimScreen.width - ins.left - ins.right;
        int intH = dimScreen.height - ins.top - ins.bottom;
        
        
        Dimension dimCur = getSize();
        int intWCur = dimCur.width;
        int intHCur = dimCur.height;
        boolean blnDoChange = false;
        
        if (intWCur > intW)
        {
            blnDoChange = true;
            intWCur = intW;
        }   
        
        if (intHCur > intH)
        {
            blnDoChange = true;
            intHCur = intH;
        }   
        
        if (blnDoChange)
        {
            setSize(new Dimension(intWCur, intHCur));
        }
                
    }
}