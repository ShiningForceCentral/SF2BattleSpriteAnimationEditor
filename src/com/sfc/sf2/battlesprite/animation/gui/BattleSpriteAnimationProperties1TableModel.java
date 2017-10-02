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
public class BattleSpriteAnimationProperties1TableModel extends AbstractTableModel {
    
    private final String[][] tableData;
    private final String[] columns = {"Frame Number", "Spell Init Frame", "Spell Anim", "End Spell", "Idle 1 Weapon Frame", "Idle 1 Z", "Idle 1 Weapon X", "Idle 1 Weapon Y"};
 
    public BattleSpriteAnimationProperties1TableModel(BattleSpriteAnimation animation) {
        super();
        tableData = new String[1][];
        int i = 0;
        if(animation!=null){
            tableData[i] = new String[8];
            tableData[i][0] = Integer.toString(animation.getFrameNumber());
            tableData[i][1] = Integer.toString(animation.getSpellInitFrame());
            tableData[i][2] = Integer.toString(animation.getSpellAnim());
            tableData[i][3] = Integer.toString(animation.getEndSpellAnim());
            tableData[i][4] = Integer.toString(animation.getIdle1WeaponFrame());
            tableData[i][5] = Integer.toString(animation.getIdle1WeaponZ());
            tableData[i][6] = Integer.toString(animation.getIdle1WeaponX());
            tableData[i][7] = Integer.toString(animation.getIdle1WeaponY());
        }
    }
    
    public void updateProperties(BattleSpriteAnimation animation) {
                animation.setFrameNumber(Integer.parseInt(tableData[0][0]));
                animation.setSpellInitFrame(Integer.parseInt(tableData[0][1]));
                animation.setSpellAnim(Integer.parseInt(tableData[0][2]));
                animation.setEndSpellAnim(Integer.parseInt(tableData[0][3]));
                animation.setIdle1WeaponFrame(Integer.parseInt(tableData[0][4]));
                animation.setIdle1WeaponZ(Integer.parseInt(tableData[0][5]));
                animation.setIdle1WeaponX(Integer.parseInt(tableData[0][6]));
                animation.setIdle1WeaponY(Integer.parseInt(tableData[0][7]));
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
