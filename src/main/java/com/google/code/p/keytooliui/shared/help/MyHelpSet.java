/*
 *
 * Copyright (c) 2001-2011 keyTool IUI Project.
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

package com.google.code.p.keytooliui.shared.help;

import com.google.code.p.keytooliui.shared.lang.*;

import javax.help.*;

public class MyHelpSet extends HelpSet
{
    // ---------------------------
    // PRIVATE STATIC FINAL STRING
    private static final String _f_s_strClass = "com.google.code.p.keytooliui.shared.help.MyHelpSet.";

    // -------------
    // PUBLIC STATIC

    public static boolean s_assignStyleSheet(HelpSet hst)
    {
        String strMethod = MyHelpSet._f_s_strClass + "s_assignStyleSheet(hst)";

        if (hst == null)
        {
            MySystem.s_printOutError(strMethod, "nil hst");
            return false;
        }

        // x) get ??

        if (! (hst instanceof MyHelpSet))
        {
            MySystem.s_printOutError(strMethod, "! (hst instanceof MyHelpSet)");
            return false;
        }

        // ending
        return true;
    }

    // ------
    // PUBLIC

    public MyHelpSet(ClassLoader clr, java.net.URL urlHelpset) throws HelpSetException
    {
        super(clr, urlHelpset);
    }
}