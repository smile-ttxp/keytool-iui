package com.google.code.p.keytooliui.beans.button;

/*
    based on JavaHelp's ViewAwareComponent.java
*/

import javax.swing.text.*;



public interface MyViewAwareComponent 
{
    /**
     * Set the View that corresponds to this object
     * This gives access to a wealth of information.
     */
    public void setViewData(View v);

    /**
    * May need something to react to changes (in my view?)
    */
}