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
 
 package com.google.code.p.keytooliui.shared.swing.label;
 
 /*
    known subclasses:
    . LabelCheckTypeJarAbs
 
 
    a label with an icon on the left
    icon's size: 11x11
    
    if checked ==> red triangle right-oriented
    if unchecked ==> transparent icon
 */
 
 import javax.swing.*;
 
 import java.awt.*;
 
 public class LabelCheck extends JLabel
 {
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private ImageIcon _f_s_iinChecked = 
        //com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("tick_red9x11.gif"); 
        com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("checked16.gif");
        
    final static private ImageIcon _f_s_iinUnchecked = 
        //com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("transp9x11.gif");
        com.google.code.p.keytooliui.shared.swing.imageicon.S_IINShared.s_get("emptyicon16.gif");
        
   
    //final static private Font _f_s_fntChecked = 
      //  new Font("serif", Font.BOLD, 14);
        
    //final static private Font _f_s_fntUnchecked = 
      //  new Font("serif", Font.PLAIN, 14);
        
    // ------
    // PUBLIC
    
    public boolean isChecked() { return this._blnIsChecked; }
    
    public void setChecked(boolean bln)
    {
        
        this._blnIsChecked = bln;
        
        if (bln)
        {
            setIcon(LabelCheck._f_s_iinChecked);
            //setFont(LabelCheck._f_s_fntChecked);
            return;
        }
        
        // ----
        setIcon(LabelCheck._f_s_iinUnchecked);
        //setFont(LabelCheck._f_s_fntUnchecked);
    }
    
    public LabelCheck(String strText, boolean blnChecked)
    {
        this(strText);
        
        setChecked(blnChecked);
    }
        
    public LabelCheck(String strText)
    {
        super(strText);
        
        this._blnIsChecked = false;
        
        setForeground(Color.black);
        
        setIcon(LabelCheck._f_s_iinUnchecked);
        //setFont(LabelCheck._f_s_fntUnchecked);
    }
    
    // -------
    // PRIVATE
    
    private boolean _blnIsChecked = false;
 }