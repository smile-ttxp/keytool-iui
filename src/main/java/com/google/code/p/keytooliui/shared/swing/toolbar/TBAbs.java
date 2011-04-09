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
 
 
package com.google.code.p.keytooliui.shared.swing.toolbar;

/*
    known subclasses:
    . TBListNoteAbstract   ==> rcr
    . TBEditorAbstract     ==> shared
    . TBMSAbstract         ==> shared
    . TBViewerHtmlAbstract ==> shared, TO BE REMOVED!
    . TBAbstract           ==> shared_gen
    . TBPageSecAbs              ==> shared, "PageSec" means "Page Secondary", either inside secondary window, or as an object being rendered in HTML
*/

import javax.swing.*;

import java.awt.*;

public abstract class TBAbs extends JToolBar
{
    // ----------------------
    // PROTECTED STATIC FINAL
    
    protected static final Dimension _f_s_dimSeparator4_ = new Dimension(4, 4);
    protected static final Dimension _f_s_dimSeparator10_ = new Dimension(10, 10);
    
    // ---------
    // PROTECTED
    
    protected TBAbs(int intOrientation, boolean blnFloatable)
    {
        super(intOrientation);
        setFloatable(blnFloatable); // change, testing under J1.5.0// because of tbrl: JDK1.3.0, JDK1.3.1-beta, JDK1.3.1, should remain false!
    }
}