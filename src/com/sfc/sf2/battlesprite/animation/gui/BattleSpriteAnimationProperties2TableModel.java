/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.battlesprite.animation.gui;

import com.sfc.sf2.battlesprite.animation.BattleSpriteAnimation;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author wiz
 */
public class BattleSpriteAnimationProperties2TableModel extends AbstractTableModel {
    
    private final Object[][] tableData;
    private final String[] columns = {"Byte 8", "Byte 9", "Byte 10", "Byte 11", "Idle 2 Weapon Frame", "H Flip", "V Flip", "Idle 2 Z", "Idle 2 Weapon X", "Idle 2 Weapon Y"};
 
    public BattleSpriteAnimationProperties2TableModel(BattleSpriteAnimation animation) {
        super();
        tableData = new Object[1][];
        int i = 0;
        if(animation!=null){
            tableData[i] = new Object[10];
            tableData[i][0] = Integer.toString(animation.getByte8());
            tableData[i][1] = Integer.toString(animation.getByte9());
            tableData[i][2] = Integer.toString(animation.getByte10());
            tableData[i][3] = Integer.toString(animation.getByte11());
            tableData[i][4] = Integer.toString(animation.getIdle2WeaponFrame()&0xF);
            tableData[i][5] = (animation.getIdle2WeaponFrame()&0x10)!=0;
            tableData[i][6] = (animation.getIdle2WeaponFrame()&0x20)!=0;
            tableData[i][7] = Integer.toString(animation.getIdle2WeaponZ());
            tableData[i][8] = Integer.toString(animation.getIdle2WeaponX());
            tableData[i][9] = Integer.toString(animation.getIdle2WeaponY());
        }
    }    
    
    public void updateProperties(BattleSpriteAnimation animation) {
                animation.setByte8(Integer.parseInt((String)tableData[0][0]));
                animation.setByte9(Integer.parseInt((String)tableData[0][1]));
                animation.setByte10(Integer.parseInt((String)tableData[0][2]));
                animation.setByte11(Integer.parseInt((String)tableData[0][3]));
                animation.setIdle2WeaponFrame(Integer.parseInt((String)tableData[0][4]) + (int)(((Boolean)tableData[0][5])?0x10:0) + (int)((Boolean)tableData[0][6]?0x20:0));
                animation.setIdle2WeaponZ(Integer.parseInt((String)tableData[0][7]));
                animation.setIdle2WeaponX(Integer.parseInt((String)tableData[0][8]));
                animation.setIdle2WeaponY(Integer.parseInt((String)tableData[0][9]));
    }
    
    @Override
    public Class getColumnClass(int column) {
        if(column == 5 ||column == 6){
            return Boolean.class;
        }else {
            return String.class;
        }
    } 
    
    @Override
    public Object getValueAt(int row, int col) {
        return tableData[row][col];
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        tableData[row][col] = (String)value;
    }    
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }    
    
    @Override
    public int getRowCount() {
        return tableData.length;
    }
 
    @Override
    public int getColumnCount() {
        return columns.length;
    }
 
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
}
