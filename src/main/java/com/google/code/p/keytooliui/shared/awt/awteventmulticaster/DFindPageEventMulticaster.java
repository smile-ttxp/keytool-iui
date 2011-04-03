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
 
 
 package com.google.code.p.keytooliui.shared.awt.awteventmulticaster;

import com.google.code.p.keytooliui.shared.awt.awtevent.*;
import com.google.code.p.keytooliui.shared.util.eventlistener.*;

/**
    MEMO:
        protected AWTEventMulticaster.EventListener a;
        protected AWTEventMulticaster.EventListener b;
**/

import java.util.EventListener;

public class DFindPageEventMulticaster extends java.awt.AWTEventMulticaster implements DFindPageListener
{
    // -------------
    // STATIC PUBLIC
    
    static public DFindPageListener add(DFindPageListener aflA, DFindPageListener aflB)
	{
        return (DFindPageListener) addInternal(aflA, aflB);
	}
  
	static public DFindPageListener remove(DFindPageListener aflA, DFindPageListener aflB)
	{
        return (DFindPageListener) removeInternal(aflA, aflB);
	}
	
	// ----------------
	// STATIC PROTECTED
	
	static protected EventListener addInternal(EventListener elrA, EventListener elrB)
	{
		if (elrA == null) return elrB;
		if (elrB == null) return elrA;
        return new DFindPageEventMulticaster(elrA, elrB);
	}
	
	// ------
	// PUBLIC
	
	public void findNext(DFindPageEvent evt)
	{
		if (a != null)
			((DFindPageListener) a).findNext(evt);
    
		if (b != null)
			((DFindPageListener) b).findNext(evt);
	}
	
	// ---------
	// PROTECTED
    
    protected DFindPageEventMulticaster(EventListener elrA, EventListener elrB)
	{
        super(elrA, elrB);
	}
  
	protected EventListener remove(EventListener elrOld)
	{
        if (elrOld == a) return b;
		if (elrOld == b) return a;
        
        EventListener elrA2 = removeInternal(a, elrOld);
        EventListener elrB2 = removeInternal(b, elrOld);
        
        if (elrA2 == a && elrB2 == b)
			return this;
		
		return addInternal(elrA2, elrB2);
    }
}