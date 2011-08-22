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
 
 
package com.google.code.p.keytooliui.shared.swing.table;
 
 /**
    either black or red
 
 **/
 
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import com.google.code.p.keytooliui.shared.lang.bool.*;
 
 
public final class TCRCheckedWarn extends TCRCheckedAbs
{
    // ------
    // PUBLIC
    
    
    public void setValue(Object obj)
    {
        if (obj instanceof BOAbs)
        {
            BOAbs cwv = (BOAbs) obj;

            setIcon(cwv.getIcon());
            setText(cwv.getText());  
            
            Color col = null;
            

            //col = ((BOCheckedWarnAbs)cwv).getColorBG();
            
            //if (col != null)
              //  this.setBackground(col);
            
            // -- end test
            
            col = ((BOCheckedWarnAbs)cwv).getColorFG();
            
            if (col != null)
                this.setForeground(col);
        }
    }
 }
