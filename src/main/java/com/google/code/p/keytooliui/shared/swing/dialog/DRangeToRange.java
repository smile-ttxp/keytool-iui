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
 
 
 package com.google.code.p.keytooliui.shared.swing.dialog;


import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.swing.panel.*;

 
import java.awt.*;


public final class DRangeToRange extends DRangeAbstract
{
    // ------
    // PUBLIC
    
    
    public DRangeToRange(DRangeAbstractListener draListenerParent, Component cmpFrameOwner,
        String strTitleDialog, String strTextArea, String strTitleMaster,
        int intRangeMinMaster, int intRangeMaxMaster, int intRangeDefaultMaster,
        int intMajorTickSpacingMaster, int intMinorTickSpacingMaster,
        String strTitleSlave, int intRangeMinSlave, int intRangeDefaultSlave)
    {
        
        super(draListenerParent, cmpFrameOwner, strTitleDialog, strTextArea);
        
        super._pra_ = new PRangeToRange(strTitleMaster, intRangeMinMaster, intRangeMaxMaster, intRangeDefaultMaster, intMajorTickSpacingMaster, intMinorTickSpacingMaster, strTitleSlave, intRangeMinSlave, intRangeDefaultSlave);
    
        // tempo, should be removed, and code added in parent to handle "current" instead of "default"
        super._btnReset_.setText("default");
        super._btnReset_.setToolTipText("default");
    }
}