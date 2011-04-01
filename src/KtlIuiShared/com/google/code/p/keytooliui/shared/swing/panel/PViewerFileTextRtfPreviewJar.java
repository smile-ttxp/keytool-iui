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
 
 
package com.google.code.p.keytooliui.shared.swing.panel;


import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.scrollpane.*;

import java.awt.*;
import java.awt.event.*;

final public class PViewerFileTextRtfPreviewJar extends PViewerFileTextRtfPreviewAbs
{    
    // ------
    // PUBLIC
    
    
    
    public PViewerFileTextRtfPreviewJar(

        ActionListener actListenerParent,
        Component cmpFrameOwner,
        String strApplicationTitle)
    {
        super(actListenerParent, cmpFrameOwner, strApplicationTitle);
    
        String strMethod = "PViewerFileTextRtfPreviewJar(actListenerParent, cmpFrameOwner, strApplicationTitle)";
	    
	    // ----
	    super._nsl_ = new SPViewerFileTextRtfJar();
	    
	     // IMPORTANT MEMO: _nsl_.init(): there, in order to get actions for other children constructors
	     // not in use in this class, BUT in in classes extending the same superclass (eg: PEditorNote, for superclass PEditorAbstract)
	    if (! ((SPViewerFileTextAbs) super._nsl_).init())
            MySystem.s_printOutExit(this, strMethod, "failed");
	    
        // ----
        
        
    }
    
    
    
    // -------
    // PRIVATE
}