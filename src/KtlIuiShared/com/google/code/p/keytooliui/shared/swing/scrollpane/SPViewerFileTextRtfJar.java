/*
 *
 * Copyright (c) 2001-2002 keyTool IUI Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.scrollpane;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.editorpane.*;

import javax.swing.*;

import java.net.*;


final public class SPViewerFileTextRtfJar extends SPViewerFileTextRtfAbs
{    
    // ------
    // PUBLIC
    

    public boolean doFileOpen(URL url)
    {
        String strMethod = "doFileOpen(url)";
        
         super._ntt_ = new EPTextRTFJar(
            null, //pepListenerParent,
            null, //cmpFrameOwner,
            null, //strApplicationTitle,
            java.awt.Color.red, // dummy value, colPageTextSelection,
            url);
           
        
            
        // ?? trying to fix up bug in jdk1.2.2, see EPTextRTFAbs for more info
        JViewport vpt = getViewport();
        vpt.add(super._ntt_);
            
        if (! super._ntt_.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
            
        return true;   
    }
    
    
    public SPViewerFileTextRtfJar()
    {
        super();
    }

    // -------
    // PRIVATE
}