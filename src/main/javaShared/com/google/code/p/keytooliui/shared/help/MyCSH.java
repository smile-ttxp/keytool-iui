package com.google.code.p.keytooliui.shared.help;

/**
**/

import com.google.code.p.keytooliui.shared.lang.*;

import javax.help.*;


import java.awt.event.*;
import java.awt.*;


public class MyCSH extends CSH       
{

    
    
    // ---------------------------
    // FINAL STATIC PRIVATE STRING
    
    final static private String _f_s_strClass = "com.google.code.p.keytooliui.shared.help.MyCSH.";
    
    // --------------
    // STATIC PRIVATE
    
    static private java.util.Vector<Component> _s_vecCmpSetID = new java.util.Vector<Component>();
    
    // ------
    // STATIC
    
    static private boolean _S_BLN_DUMPCMP2ID = true;
    // in comments, april 11, 07
    /*static
    {
        
        String strMethod = _f_s_strClass + "initializer";
       
        PropertyResourceBundle prbMagic = Shared.s_getPrbMagic();
        
        if (prbMagic != null)
        {
            String str = null;
            
            // --
            str = null;
            try { str = prbMagic.getString(_f_s_strClass + "dumpCmp2ID"); }
            catch (java.util.MissingResourceException excMissingResource) {}
                
            if (str != null) // else ignoring
            {
                str = str.toLowerCase();
                    
                if (str.compareTo("false") == 0)
                    _S_BLN_DUMPCMP2ID = false;
                else if (str.compareTo("true") == 0)
                    _S_BLN_DUMPCMP2ID = true;
                else
                    MySystem.s_printOutTrace(strMethod, "ignoring uncaught value, key=" + _f_s_strClass + "dumpCmp2ID" + ", value =" + str);
            }
        }
    }*/
    
    
    // -------------
    // STATIC PUBLIC
    
    static public boolean s_checkAndDumpCmp2ID()
    {
        String strMethod = _f_s_strClass + "s_checkAndDumpCmp2ID()";
        
        if (_S_BLN_DUMPCMP2ID)
            System.out.println(">> BEGIN dump component2ID");
        
        if (MyCSH._s_vecCmpSetID == null)
        {
            MySystem.s_printOutError(strMethod, "nil MyCSH._s_vecCmpSetID");
            return false;
        }
        
        if (_S_BLN_DUMPCMP2ID)
        {
            System.out.println("MyCSH._s_vecCmpSetID.size()=" + MyCSH._s_vecCmpSetID.size());
            System.out.println("table: i strIDCur cmpCur.getClass().toString()");
        }
        
        for (int i=0; i<MyCSH._s_vecCmpSetID.size(); i++)
        {
            Component cmpCur = (Component) MyCSH._s_vecCmpSetID.elementAt(i);
            
            if (cmpCur == null)
            {
                MySystem.s_printOutError(strMethod, "nil cmpCur, i=" + i);
                return false;
            }
            
            String strIDCur = CSH.getHelpIDString(cmpCur);
            
            if (strIDCur == null)
            {
                MySystem.s_printOutError(strMethod, "nil strIDCur, i=" + i + ", cmpCur.getClass().toString()=" + cmpCur.getClass().toString());
                return false;
            }
            
            if (_S_BLN_DUMPCMP2ID)
                System.out.println(i + " " + strIDCur + " " + cmpCur.getClass().toString());
        }
        
        if (_S_BLN_DUMPCMP2ID)
            System.out.println(">> END dump component2ID");
        
        // ending
        return true;
    }
    
    /**
        overwrites superclass's method
    **/
    static public void setHelpIDString(Component cmpTarget, String strID)
    {
        String strMethod = _f_s_strClass + "setHelpIDString(cmpTarget, strID)";
        
        if (cmpTarget == null)
            MySystem.s_printOutExit(strMethod, "nil cmpTarget");
        
        
         System.out.println("setHelpIDString " + cmpTarget.getClass().getName());
            
            
        if (strID != null)
            MyCSH._s_vecCmpSetID.addElement(cmpTarget);
        
        CSH.setHelpIDString(cmpTarget, strID);
        
    }
    
    
    // -----------------------------------------
    // BEGIN STATIC CLASS MyDisplayHelpFromFocus
    
    static public class MyDisplayHelpFromFocus implements ActionListener 
    {
        String strClassSub = "MyDisplayHelpFromFocus.";
        
        // ------
        // PUBLIC
        
        public MyDisplayHelpFromFocus(HelpBroker hbr)
        {
            String strMethod = _f_s_strClass + strClassSub + "MyDisplayHelpFromFocus(hbr)";
         
            if (hbr == null)
                MySystem.s_printOutExit(this, strMethod, "nil hbr");
                
            if (! (hbr instanceof MyHelpBroker))
                MySystem.s_printOutExit(this, strMethod, "! (hbr instanceof MyHelpBroker)");
                
            this._mhb = (MyHelpBroker) hbr;    
   
            try
            {
                this._alr = new CSH.DisplayHelpFromFocus(hbr);
            }
            
            catch(NullPointerException excNullPointer)
            {
                excNullPointer.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excNullPointer caught");
            }
            
            strMethod = null;
        }
        
        public void actionPerformed(ActionEvent evtAction)
        {
            final String strMethod = _f_s_strClass + strClassSub + "actionPerformed(evtAction)"; // !!!!!!!!!
            
            if (this._alr == null)
                MySystem.s_printOutExit(this, strMethod, "nil this._alr");
                
            this._alr.actionPerformed(evtAction);
            
            javax.swing.SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    String strMethod2 = strMethod + "." + "run()";

                    if (_mhb == null)
                    {
                        MySystem.s_printOutTrace(this, strMethod2, "nil _mhb, maybe destroying");
                        strMethod2 = null;
                        return;
                    }

                    if (! _mhb.assignStyleSheet())
                    {
                        MySystem.s_printOutTrace(this, strMethod2, "failed, maybe destroying");
                        strMethod2 = null;
                        return;
                    }
                    
                    strMethod2 = null;
                }
            });
        }
        
        // -------
        // PRIVATE
        
        private ActionListener _alr = null;
        private MyHelpBroker _mhb = null;
    }
    
    // ---------------------------------------
    // END STATIC CLASS MyDisplayHelpFromFocus
    
    // ---------------------------------------------
    // BEGIN STATIC CLASS MyDisplayHelpAfterTracking
    
    static public class MyDisplayHelpAfterTracking implements ActionListener 
            , MouseListener // test
    {
        String strClassSub = "MyDisplayHelpAfterTracking.";
        
        // ------
        // PUBLIC
        
        public MyDisplayHelpAfterTracking(HelpBroker hbr)
        {
            String strMethod = _f_s_strClass + strClassSub + "MyDisplayHelpAfterTracking(hbr)";
         
            if (hbr == null)
                MySystem.s_printOutExit(this, strMethod, "nil hbr");
                
            if (! (hbr instanceof MyHelpBroker))
                MySystem.s_printOutExit(this, strMethod, "! (hbr instanceof MyHelpBroker)");
                
            this._mhb = (MyHelpBroker) hbr;    
            
            
            try
            {
                this._alr = new CSH.DisplayHelpAfterTracking(hbr);
            }
            
            catch(NullPointerException excNullPointer)
            {
                excNullPointer.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excNullPointer caught");
            }
        }
        
        public void actionPerformed(ActionEvent evtAction)
        {
            final String strMethod = _f_s_strClass + strClassSub + "actionPerformed(evtAction)";
            
            System.out.println("evtAction.getSource().getClass().toString()=" + evtAction.getSource().getClass().toString());
            
            if (this._alr == null)
                MySystem.s_printOutExit(this, strMethod, "nil this._alr");
                
            // CANNOT CATCH EXCEPTION !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            try
            {
                this._alr.actionPerformed(evtAction);
            }
            
            catch(javax.help.BadIDException excBadID)
            {
                excBadID.printStackTrace();
                MySystem.s_printOutError("com.google.code.p.keytooliui.shared.help.MyCSH$MyDisplayHelpAfterTracking().actionPerfomed(evtAction)",
                        "got excBadID, excBadID.getID()=" + excBadID.getID());
                
                String strBody = "Got javax.help.BadIDException";
                strBody += "\n" + excBadID.getMessage();
                com.google.code.p.keytooliui.shared.swing.optionpane.OPAbstract.s_showDialogError(null, strBody);
                return;
            }
            
            
            javax.swing.SwingUtilities.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                String strMethod2 = strMethod + "." + "run()";
                        
                    if (_mhb == null)
                        MySystem.s_printOutExit(this, strMethod2, "nil _mhb");
                        
                    if (! _mhb.assignStyleSheet())
		                MySystem.s_printOutExit(this, strMethod2, "failed");
	            }
	        }); 
        }
        
        // -------
        // PRIVATE
        
        private ActionListener _alr = null;
        private MyHelpBroker _mhb = null;

        public void mouseClicked(MouseEvent e) {
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        public void mousePressed(MouseEvent e) {
            //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        public void mouseReleased(MouseEvent e) {
            //throw new java.lang.UnsupportedOperationException("Not supported yet.");
        }

        public void mouseEntered(MouseEvent e) {
            System.out.println("mouse entered to " + e.getSource().getClass().getName());
        }

        public void mouseExited(MouseEvent e) {
            System.out.println("mouse exited from " + e.getSource().getClass().getName());
        }
    }
    
    // -------------------------------------------
    // END STATIC CLASS MyDisplayHelpAfterTracking
    
    
    
    // ------------------------------------------
    // BEGIN STATIC CLASS MyDisplayHelpFromSource
    
    static public class MyDisplayHelpFromSource implements ActionListener 
    {
        String strClassSub = "MyDisplayHelpFromSource.";
        
        // ------
        // PUBLIC
        
        public MyDisplayHelpFromSource(HelpBroker hbr)
        {
            String strMethod = _f_s_strClass + strClassSub + "MyDisplayHelpFromSource(hbr)";
         
            if (hbr == null)
                MySystem.s_printOutExit(this, strMethod, "nil hbr");
                
            if (! (hbr instanceof MyHelpBroker))
                MySystem.s_printOutExit(this, strMethod, "! (hbr instanceof MyHelpBroker)");
                
            this._mhb = (MyHelpBroker) hbr;    
            
            
            try
            {
                this._alr = new CSH.DisplayHelpFromSource(hbr);
            }
            
            catch(NullPointerException excNullPointer)
            {
                excNullPointer.printStackTrace();
                MySystem.s_printOutExit(this, strMethod, "excNullPointer caught");
            }
        }
        
        public void actionPerformed(ActionEvent evtAction)
        {
            final String strMethod = _f_s_strClass + strClassSub + "actionPerformed(evtAction)";
            
            if (this._alr == null)
                MySystem.s_printOutExit(this, strMethod, "nil this._alr");
                
            this._alr.actionPerformed(evtAction);
            
            javax.swing.SwingUtilities.invokeLater(new Runnable()
	        {
	            public void run()
	            {
	                String strMethod2 = strMethod + "." + "run()";
                        
                    if (_mhb == null)
                        MySystem.s_printOutExit(this, strMethod2, "nil _mhb");
                        
                    if (! _mhb.assignStyleSheet())
		                MySystem.s_printOutExit(this, strMethod2, "failed");
	            }
	        });
        }
        
        // -------
        // PRIVATE
        
        private ActionListener _alr = null;
        private MyHelpBroker _mhb = null;
    }

    
    
    // ----------------------------------------
    // END STATIC CLASS MyDisplayHelpFromSource
}