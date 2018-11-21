/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree234;

/**
 *
 * @author intel
 */
public class Tree234Exception extends RuntimeException {
	public Tree234Exception() {
        super ("Issues with TwoFourNode");
    }
    
    public Tree234Exception(String anErrorMsg) {
        super (anErrorMsg);
    }
}