
package com.google.code.p.keytooliui.ktl.swing.menu;
 
/**
    known subclasses:
    . MViewSelTabUIKtl
    . MViewSelTabUIJsr

**/

import com.google.code.p.keytooliui.ktl.swing.menuitem.*;

import com.google.code.p.keytooliui.shared.lang.*;
import com.google.code.p.keytooliui.shared.swing.menu.*;
import com.google.code.p.keytooliui.shared.swing.menuitem.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;


public abstract class MViewSelTabUIAbs extends MAbstract
{
    // ---------------
    // ABSTRACT PUBLIC
    
    public abstract void destroy();
    public abstract boolean init();
    

    // ---------
    // PROTECTED
    
    protected MViewSelTabUIAbs()
    {
        setText("Select task");
    }
    
}