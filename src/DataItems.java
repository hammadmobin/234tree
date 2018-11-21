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
public class DataItems<T>
{
    public T dData; 
    
public DataItems(T dd)
{
    dData = dd; 
}


public void displayItem()
{
    System.out.print("/"+dData); }
   
}