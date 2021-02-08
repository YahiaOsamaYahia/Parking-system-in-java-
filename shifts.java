/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

/**
 *
 * @author oabdo
 */
public class shifts {
        int slot_id; 
    int book_hours ;
    int book_mins; 
    int payment; 
    shifts ( int Slot_id, int Book_hours, int Book_mins, int Payment){
        this.slot_id=Slot_id;
        this.book_hours= Book_hours; 
        this.book_mins= Book_mins; 
        this.payment=Payment; 
    }
}
