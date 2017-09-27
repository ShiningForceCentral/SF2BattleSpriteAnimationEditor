/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.battlesprite.animation;

import com.sfc.sf2.graphics.GraphicsManager;
import com.sfc.sf2.graphics.Tile;
import com.sfc.sf2.battlesprite.animation.io.DisassemblyManager;
import com.sfc.sf2.battlesprite.animation.io.PngManager;
import com.sfc.sf2.palette.PaletteManager;
import java.awt.Color;

/**
 *
 * @author wiz
 */
public class BattleSpriteAnimationManager {
       
    private PaletteManager paletteManager = new PaletteManager();
    private GraphicsManager graphicsManager = new GraphicsManager();
    private Tile[] tiles;
    private BattleSpriteAnimation battlespriteanimation = new BattleSpriteAnimation();
       
    public void importDisassembly(String filePath){
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.importDisassembly() - Importing disassembly ...");
        battlespriteanimation = DisassemblyManager.importDisassembly(filePath);
        if(battlespriteanimation.getFrames() != null && battlespriteanimation.getFrames().length > 0){
            int blockColumnsNumber = (battlespriteanimation.getType()==BattleSpriteAnimation.TYPE_ALLY)?3:4;
            tiles = new Tile[battlespriteanimation.getFrames().length*blockColumnsNumber*4*12];
            for(int i=0;i<battlespriteanimation.getFrames().length;i++){
                System.arraycopy(battlespriteanimation.getFrames()[i], 0, tiles, i*blockColumnsNumber*4*12, blockColumnsNumber*4*12);
            }
            graphicsManager.setTiles(tiles);
        }
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.importDisassembly() - Disassembly imported.");
    }
    
    public void exportDisassembly(String filepath, String animSpeed, String unknown){
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.importDisassembly() - Exporting disassembly ...");
        DisassemblyManager.exportDisassembly(battlespriteanimation, filepath);
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.importDisassembly() - Disassembly exported.");        
    }   
    
    
    public void importPng(String filepath, boolean usePngPalette){
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.importPng() - Importing PNG ...");
        battlespriteanimation = PngManager.importPng(filepath, battlespriteanimation, usePngPalette);
        if(battlespriteanimation.getFrames() != null && battlespriteanimation.getFrames().length > 0){
            int blockColumnsNumber = (battlespriteanimation.getType()==BattleSpriteAnimation.TYPE_ALLY)?3:4;
            tiles = new Tile[battlespriteanimation.getFrames().length*blockColumnsNumber*4*12];
            for(int i=0;i<battlespriteanimation.getFrames().length;i++){
                System.arraycopy(battlespriteanimation.getFrames()[i], 0, tiles, i*blockColumnsNumber*4*12, blockColumnsNumber*4*12);
            }
            graphicsManager.setTiles(tiles);
        }
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.importPng() - PNG imported.");
    }
    
    public void exportPng(String filepath, int selectedPalette){
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.exportPng() - Exporting PNG ...");
        PngManager.exportPng(battlespriteanimation, filepath, selectedPalette);
        System.out.println("com.sfc.sf2.battlespriteanimation.BattleSpriteAnimationManager.exportPng() - PNG exported.");       
    }

    public BattleSpriteAnimation getBattleSpriteAnimation() {
        return battlespriteanimation;
    }

    public void setBattleSpriteAnimation(BattleSpriteAnimation battlespriteanimation) {
        this.battlespriteanimation = battlespriteanimation;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }
}
