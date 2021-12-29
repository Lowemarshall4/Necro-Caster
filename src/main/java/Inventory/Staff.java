/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;


public class Staff extends Item{
    private int atk;
    private int agil;
    private int reqMaterial;

    public Staff(String name, int quantity, int atk, int agil) {
        super(name, quantity);
        this.atk = atk;
        this.agil = agil;
        this.reqMaterial = 10;
    }
    
    //Accessors

    public int getAtk() {
        return atk;
    }

    public int getAgil() {
        return agil;
    }
    
    public int getReqMaterial() {
        return reqMaterial;
    }
    

}
