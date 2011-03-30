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

package com.google.code.p.keytooliui.beans.panel;


import java.beans.*;

/**
    get and set properties, used for reflection
    
    known subclasses:
    . PMediaUrlAudioBeanInfo
    . PMediaUrlVideoBeanInfo
    
**/
abstract public class PMediaUrlAbsBeanInfo extends PMediaAbsBeanInfo 
{
    // ------
    // PUBLIC
    
    protected PropertyDescriptor[] _getPropertyDescriptors_(PropertyDescriptor[] pdrsSub) 
    {
        int intShift = pdrsSub.length;
        
	    PropertyDescriptor pdrs[] = new PropertyDescriptor[intShift + 2];
	    
	    try 
	    {
	        for (int i=0; i<intShift; i++)
	            pdrs[i] = pdrsSub[i];
	        
	        // required
	        pdrs[intShift] = new PropertyDescriptor("url", super._clsBean_); intShift++;
            // optional
	        pdrs[intShift] = new PropertyDescriptor("urlIsRelative", super._clsBean_); intShift ++; // ++ not needed
	        
	        return pdrs;
	    } 
	    
	    catch (Exception exc) 
	    {
	        exc.printStackTrace();
	        return null;
	    }
    }
    
    
    // ---------
    // PROTECTED
    
    
    protected PMediaUrlAbsBeanInfo(
        Class clsBean,
        String strName
        ) 
    {
        super(clsBean, strName);
    }
}