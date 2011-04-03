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

import com.google.code.p.keytooliui.shared.util.eventlistener.*;
import com.google.code.p.keytooliui.shared.awt.awtevent.*;

import java.util.EventListener;

public class DRangeAbstractEventMulticaster extends java.awt.AWTEventMulticaster implements DRangeAbstractListener
{
    // -----------------------
    // STATIC PUBLIC ~Listener
    
    static public DRangeAbstractListener add(DRangeAbstractListener amlA, DRangeAbstractListener amlB)
	{
        return (DRangeAbstractListener) addInternal(amlA, amlB);
	}
  
	static public DRangeAbstractListener remove(DRangeAbstractListener aml, DRangeAbstractListener amlOld)
	{
        return (DRangeAbstractListener) removeInternal(aml, amlOld);
	}
	
	// ----------------
	// STATIC PROTECTED
	
	static protected EventListener addInternal(EventListener elrA, EventListener elrB)
	{
		if (elrA == null) return elrB;
		if (elrB == null) return elrA;
        return new DRangeAbstractEventMulticaster(elrA, elrB);
	}
	
	// ------
	// PUBLIC
	
	public void valueChanged(DRangeAbstractEvent evtDRangeAbstract)
	{
		if (a != null)
			((DRangeAbstractListener) a).valueChanged(evtDRangeAbstract);
    
		if (b != null)
			((DRangeAbstractListener) b).valueChanged(evtDRangeAbstract);
	}
	
	
    
    // ---------
    // PROTECTED
    
    protected DRangeAbstractEventMulticaster(EventListener elrA, EventListener elrB)
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