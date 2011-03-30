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

package com.google.code.p.keytooliui.beans.button;


import com.google.code.p.keytooliui.beans.*;


import java.beans.*;

/**
    get and set properties, used for reflection
    
    known subclasses:
    . B2SwUrlAbsBeanInfo
    . B2SwRtpAbsBeanInfo
    
**/
abstract public class B2SwAbsBeanInfo extends BeanInfoAbs 
{
    // ---------
    // PROTECTED
    
    protected B2SwAbsBeanInfo(
        Class clsBean,
        String strName
        ) 
    {
        super(clsBean, strName);
    }
    
    protected PropertyDescriptor[] _getPropertyDescriptors_(PropertyDescriptor[] pdrsSub) 
    {
        int intShift = pdrsSub.length;
	    PropertyDescriptor pdrs[] = new PropertyDescriptor[intShift + 7];
	    
	    try 
	    {
	        for (int i=0; i<intShift; i++)
	            pdrs[i] = pdrsSub[i];
	        
	        // optional
	        pdrs[intShift] = new PropertyDescriptor("windowTitle", super._clsBean_); intShift++;
	        pdrs[intShift] = new PropertyDescriptor("text", super._clsBean_); intShift ++;
	        pdrs[intShift] = new PropertyDescriptor("textTip", super._clsBean_); intShift ++;
	        pdrs[intShift] = new PropertyDescriptor("textFontFamily", super._clsBean_); intShift ++;
	        pdrs[intShift] = new PropertyDescriptor("textFontStyle", super._clsBean_); intShift ++;
	        pdrs[intShift] = new PropertyDescriptor("textFontSize", super._clsBean_); intShift ++;
	        pdrs[intShift] = new PropertyDescriptor("textColor", super._clsBean_); intShift ++; // not required
	        return pdrs;
	    } 
	    
	    catch (Exception exc) 
	    {
	        exc.printStackTrace();
	        return null;
	    }
    }
}