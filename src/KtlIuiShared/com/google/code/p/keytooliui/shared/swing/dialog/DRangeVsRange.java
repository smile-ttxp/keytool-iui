/*
 *
 * Copyright (c) 2001-2002 RagingCat Project.
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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;


import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

import java.awt.*;


final public class DRangeVsRange extends DRangeAbstract
{
    // ------
    // PUBLIC
    
    
    public DRangeVsRange(DRangeAbstractListener draListenerParent, Component cmpFrameOwner,
        String strTitleApplication, String strTitleDialog, String strTextArea,
        String strTitleTop, int intRangeMinTop, int intRangeMaxTop, int intRangeDefaultTop,
        String strTitleBottom, int intRangeDefaultBottom,
        int intMajorTickSpacing, int intMinorTickSpacing,
        int intSpacingMin)
    {
        
        super(draListenerParent, cmpFrameOwner, strTitleApplication, strTitleDialog, strTextArea);
        
        super._pra_ = new PRangeVsRange(strTitleTop, intRangeMinTop, intRangeMaxTop, intRangeDefaultTop, strTitleBottom, intRangeDefaultBottom, intMajorTickSpacing, intMinorTickSpacing, intSpacingMin);
       
        // tempo, should be removed, and code added in parent to handle "current" instead of "default"
        super._btnReset_.setText("default");
        super._btnReset_.setToolTipText("default");
    }
}