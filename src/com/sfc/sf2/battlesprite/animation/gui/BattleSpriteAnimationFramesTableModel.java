/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.battlesprite.animation.gui;

import com.sfc.sf2.battlesprite.animation.BattleSpriteAnimationFrame;
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
public class BattleSpriteAnimationFramesTableModel extends AbstractTableModel {
    
    private final String[][] tableData;
    private final String[] columns = {"Index", "Duration", "X", "Y", "Weapon Frame", "Z", "Weapon X", "Weapon Y"};
 
    public BattleSpriteAnimationFramesTableModel(BattleSpriteAnimationFrame[] frames) {
        super();
        tableData = new String[16][];
        int i = 0;
        if(frames!=null){
            while(i<frames.length){
                tableData[i] = new String[8];
                tableData[i][0] = Integer.toString(frames[i].getIndex());
                tableData[i][1] = Integer.toString(frames[i].getDuration());
                tableData[i][2] = Integer.toString(frames[i].getX());
                tableData[i][3] = Integer.toString(frames[i].getY());
                tableData[i][4] = Integer.toString(frames[i].getWeaponFrame());
                tableData[i][5] = Integer.toString(frames[i].getWeaponZ());
                tableData[i][6] = Integer.toString(frames[i].getWeaponX());
                tableData[i][7] = Integer.toString(frames[i].getWeaponY());
                i++;
            }
        }
        while(i<tableData.length){
            tableData[i] = new String[8];
            tableData[i][0] = "null";
            tableData[i][1] = "null";
            tableData[i][2] = "null";
            tableData[i][3] = "null";
            tableData[i][4] = "null";
            tableData[i][5] = "null";
            tableData[i][6] = "null";
            tableData[i][7] = "null";
            i++;
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
