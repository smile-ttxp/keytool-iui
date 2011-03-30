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
 
 
package com.google.code.p.keytooliui.shared.swing.frame;

/**
    known subclasses:
    . FSwRtpVideoOnly
    . FSwRtpVideoAudio
    
    "Sw" means "Secondary Window"
    "Rtp" means "RTP-RRTSP"
    
    
**/

import com.google.code.p.keytooliui.shared.lang.*;

import java.awt.*;

abstract public class FSwRtpVideoAbs extends FSwRtpAbs
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private Dimension _f_s_dimSizeMin = new Dimension(FSwAbs._f_s_intWidthMin_, 400);
    
    // ------
    // PUBLIC
    
    /**
        overwriting superclass's method
        
        !!!!!! same code as FSwUrlMediaVideo
    **/
    
    public void setVisible(boolean bln)
    {
        String strMethod = "setVisible(bln)";
        
        if (bln)
        {
            this.pack(); // needed for windows, let display windows'menu bar 
            
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
            
            if (_f_s_dimSizeMin.width < intW)
                intW = _f_s_dimSizeMin.width;
                
            if (_f_s_dimSizeMin.height < intH)
                intH = _f_s_dimSizeMin.height;
            
            Dimension dim = new Dimension(intW, intH);
            setSize(dim);
        }
        
        super.setVisible(bln);
        
        //if (! bln)
          //  destroy();
    }
    
    // ---------
    // PROTECTED
           
    protected FSwRtpVideoAbs(
        Window winMainAppli,
        String strTitleApplication, 
        Image imgIcon, 
        String strIPTransmitter,
        int intPortTransmitter,
        String strContentType,
        int intTTL)
    {
        super(winMainAppli, strTitleApplication, imgIcon, strIPTransmitter, intPortTransmitter, strContentType, intTTL);
        
    }
}