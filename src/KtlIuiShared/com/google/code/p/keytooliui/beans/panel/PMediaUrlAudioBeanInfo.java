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

package com.google.code.p.keytooliui.beans.panel;


import java.beans.*;

/**
    get and set properties, used for reflection
**/
final public class PMediaUrlAudioBeanInfo extends PMediaUrlAbsBeanInfo 
{
    // --------------------
    // FINAL STATIC PRIVATE
    
    final static private Class _f_s_clsBean = PMediaUrlAudio.class;
    final static private String _f_s_strName = "Panel, media-url-audio"; 

    // ------
    // PUBLIC
    
    public PMediaUrlAudioBeanInfo() 
    {
        super(_f_s_clsBean, _f_s_strName);
    }
    
    public PropertyDescriptor[] getPropertyDescriptors()
    {
        return super._getPropertyDescriptors_(new PropertyDescriptor[0]);
    }
}