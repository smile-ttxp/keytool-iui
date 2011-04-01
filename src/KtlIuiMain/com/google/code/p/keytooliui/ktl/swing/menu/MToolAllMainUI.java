/*
 *
 * Copyright (c) 2001-2008 keyTool IUI Project.
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
 
 
package com.google.code.p.keytooliui.ktl.swing.menu;

/**
    IN COMMENTS:
    _matDeploy
**/

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;
import com.google.code.p.keytooliui.ktl.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.*;

import java.awt.event.*;

final public class MToolAllMainUI extends MToolAllMainAbstract
{

    // ------
    // PUBLIC


    public MToolAllMainUI(
        JFrame frmOwner,
        String strTitleAppli,
        ActionListener actListenerParent)
    {
        super();
        //super._pam_ = new MToolAppliMainSec(actListenerParent);
        
        this._mimManagerKstJks = new MIToolKstJks(actListenerParent);
        this._mimManagerKstJceks = new MIToolKstJceks(actListenerParent);
        this._mimManagerKstPkcs12 = new MIToolKstPkcs12(actListenerParent);
        this._mimManagerKstBks = new MIToolKstBks(actListenerParent);
        this._mimManagerKstUber = new MIToolKstUber(actListenerParent);
        
        
        this._mimManagerKstJksCrtsTrustSys = new MIToolKstJksSysRootCA(actListenerParent);
        
        /**this._matDeploy = new MToolDeplAll(
            frmOwner, 
            strTitleAppli,
            com.google.code.p.keytooliui.ktl.AppMainUIAbs.f_s_strApplicationDir // "kst"
        );**/
    }
 
    public boolean init()
    {
        String strMethod = "init()";
        
        if (! super.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // 
        
        // --
        
        if (this._mimManagerKstJks == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mimManagerKstJks");
            return false;
        }
        
        if (! this._mimManagerKstJks.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._mimManagerKstJceks == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mimManagerKstJceks");
            return false;
        }
        
        if (! this._mimManagerKstJceks.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        // --
        
        if (this._mimManagerKstPkcs12 != null)
        {
            if (! this._mimManagerKstPkcs12.init())
            {
                MySystem.s_printOutError(this, strMethod, "failed");
                return false;
            }
        }
        
        if (this._mimManagerKstBks == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mimManagerKstBks");
            return false;
        }
        
        if (! this._mimManagerKstBks.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._mimManagerKstUber == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mimManagerKstUber");
            return false;
        }
        
        if (! this._mimManagerKstUber.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        if (this._mimManagerKstJksCrtsTrustSys == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._mimManagerKstJksCrtsTrustSys");
            return false;
        }
        
        if (! this._mimManagerKstJksCrtsTrustSys.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }
        
        
        
        
        // --
        
        /**if (this._matDeploy == null)
        {
            MySystem.s_printOutError(this, strMethod, "nil this._matDeploy");
            return false;
        }
        
        if (! this._matDeploy.init())
        {
            MySystem.s_printOutError(this, strMethod, "failed");
            return false;
        }**/
        
        // --
        
        JMenu menKstMan = new JMenu();
        menKstMan.setText("Keystore manager");
        
        add(menKstMan);
        
        menKstMan.add(this._mimManagerKstJks);
        menKstMan.add(this._mimManagerKstJceks);
        
        if (this._mimManagerKstPkcs12 != null)
            menKstMan.add(this._mimManagerKstPkcs12);
     
        menKstMan.add(this._mimManagerKstBks);
        menKstMan.add(this._mimManagerKstUber);
        
        menKstMan.addSeparator();
        menKstMan.add(this._mimManagerKstJksCrtsTrustSys);
        
        
        //add(this._matDeploy);
        
        // --
        
        super._updateSetEnabledThis_();
        
        // --

        return true;
    }
    
    public void destroy()
    {
        super.destroy();       
        
        if (this._mimManagerKstJks != null)
        {
            this._mimManagerKstJks.destroy();
            this._mimManagerKstJks = null;
        }
        
        if (this._mimManagerKstJceks != null)
        {
            this._mimManagerKstJceks.destroy();
            this._mimManagerKstJceks = null;
        }
        
        if (this._mimManagerKstPkcs12 != null)
        {
            this._mimManagerKstPkcs12.destroy();
            this._mimManagerKstPkcs12 = null;
        }
        
        if (this._mimManagerKstBks != null)
        {
            this._mimManagerKstBks.destroy();
            this._mimManagerKstBks = null;
        }
        
        if (this._mimManagerKstUber != null)
        {
            this._mimManagerKstUber.destroy();
            this._mimManagerKstUber = null;
        }
        
        if (this._mimManagerKstJksCrtsTrustSys != null)
        {
            this._mimManagerKstJksCrtsTrustSys.destroy();
            this._mimManagerKstJksCrtsTrustSys = null;
        }
    }
    
    // -------
    // PRIVATE
    
    private MIAbstract _mimManagerKstJks = null;
    private MIAbstract _mimManagerKstJceks = null;
    private MIAbstract _mimManagerKstPkcs12 = null;
    private MIAbstract _mimManagerKstBks = null;
    private MIAbstract _mimManagerKstUber = null;
    private MIAbstract _mimManagerKstJksCrtsTrustSys = null;
    
    //private MAbstract _matDeploy = null;
}