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

package com.google.code.p.keytooliui.beans;


import java.beans.*;

/**
    get and set properties, used for reflection
    
    known subclasses:
    . PMediaAbsBeanInfo
    . B2SwAbsBeanInfo
    
**/

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.*;

abstract public class BeanInfoAbs extends SimpleBeanInfo 
{
    // -------------------
    // FINAL STATIC PUBLIC
    
     // -- lib short name
    final static public String f_s_strName = "Raging Cat Bean";
    
    // -------------
    // STATIC PUBLIC
    
    
    // eg: rc[version]bns.jar
    static public String s_getNameLibClass()
    {
        return Shared.s_getNameLibClass(Shared.f_s_strLibNameShortXPBeans);
    }
    
    // ---------------
    // ABSTRACT PUBLIC
    
    abstract public PropertyDescriptor[] getPropertyDescriptors();
    
    // ------
    // PUBLIC
    
    public BeanDescriptor getBeanDescriptor()
    {
        String strMethod = "getBeanDescriptor()";
        // tempo
        //String strClassMethod = this._clsBean_.toString() + ".getBeanDescriptor()"; // calling method, in subclass
        //System.out.println("ENTER---> " + strClassMethod);
        MySystem.s_printOutFlagDev(this, strMethod, "ENTER");
        
        BeanDescriptor bdr = new BeanDescriptor(this._clsBean_);
        bdr.setDisplayName(this._strName_);
        
        // tempo
        //System.out.println("EXIT---> " + strClassMethod);
        MySystem.s_printOutFlagDev(this, strMethod, "EXIT");
        
        return bdr;
    }
    
    
    
    // ---------
    // PROTECTED
    
    protected Class _clsBean_ = null;
    protected String _strName_ = null;
    
    protected BeanInfoAbs(
        Class clsBean,
        String strName
        ) 
    {
        super();
        this._clsBean_ = clsBean;
        this._strName_ = strName;
    }
}