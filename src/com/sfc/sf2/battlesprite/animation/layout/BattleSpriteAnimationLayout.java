/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.battlesprite.animation.layout;

import com.sfc.sf2.background.Background;
import com.sfc.sf2.background.layout.BackgroundLayout;
import com.sfc.sf2.battlesprite.BattleSprite;
import com.sfc.sf2.battlesprite.animation.BattleSpriteAnimation;
import com.sfc.sf2.battlesprite.layout.BattleSpriteLayout;
import com.sfc.sf2.graphics.Tile;
import com.sfc.sf2.ground.Ground;
import com.sfc.sf2.ground.layout.GroundLayout;
import com.sfc.sf2.weaponsprite.WeaponSprite;
import com.sfc.sf2.weaponsprite.layout.WeaponSpriteLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import javax.swing.JPanel;

/**
 *
 * @author wiz
 */
public class BattleSpriteAnimationLayout extends JPanel {
    
    private static final int ALLY_TILES_PER_ROW = 12;
    private static final int ENEMY_TILES_PER_ROW = 16;
    
    private static final int DEFAULT_TILES_PER_ROW = 32;
    
    private int tilesPerRow = DEFAULT_TILES_PER_ROW;
    
    private int battlespriteanimationType;
    
    private BackgroundLayout backgroundLayout = new BackgroundLayout();
    private GroundLayout groundLayout = new GroundLayout();
    private BattleSpriteLayout battlespriteLayout = new BattleSpriteLayout();
    private WeaponSpriteLayout weaponspriteLayout = new WeaponSpriteLayout();
    
    private Tile[] backgroundTiles;
    private Tile[] groundTiles;
    private Tile[] battlespriteTiles;
    private Tile[] weaponTiles;
    
    private BufferedImage backgroundImage = null;
    private BufferedImage groundImage = null;
    private BufferedImage battlespriteImage = null;
    private BufferedImage weaponspriteImage = null;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);   
        g.drawImage(buildImage(), 0, 0, this);       
    }
    
    public BufferedImage buildImage(){
        BufferedImage image = buildImage(false);
        setSize(image.getWidth(), image.getHeight());
        return image;
    }
    
    public BufferedImage buildImage(boolean pngExport){
        
        
        
        BufferedImage image = new BufferedImage(256, 224 , BufferedImage.TYPE_INT_RGB);
        
        backgroundLayout.setTiles(backgroundTiles);
        groundLayout.setTiles(groundTiles);
        battlespriteLayout.setTiles(battlespriteTiles);
        weaponspriteLayout.setTiles(weaponTiles);
        
        backgroundImage = backgroundLayout.buildImage();
        groundImage = groundLayout.buildImage();
        battlespriteImage = battlespriteLayout.buildImage();
        weaponspriteImage = weaponspriteLayout.buildImage();
        
        Graphics g = image.getGraphics();
        g.drawImage(backgroundImage, 0, 64, null);
        g.drawImage(groundImage, 128, 64, null);
        g.drawImage(battlespriteImage, 128, 64, null);
        g.drawImage(weaponspriteImage, 128, 64, null);
          
        return image;
    }  
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }
    
    public int getBattleSpriteAnimationType() {
        return battlespriteanimationType;
    }

    public void setBattleSpriteAnimationType(int battlespriteanimationType) {
        this.battlespriteanimationType = battlespriteanimationType;
    }
    
    public int getTilesPerRow() {
        return tilesPerRow;
    }

    public void setTilesPerRow(int tilesPerRow) {
        this.tilesPerRow = tilesPerRow;
    }

    public int getBattlespriteanimationType() {
        return battlespriteanimationType;
    }

    public void setBattlespriteanimationType(int battlespriteanimationType) {
        this.battlespriteanimationType = battlespriteanimationType;
    }

    public Tile[] getBackgroundTiles() {
        return backgroundTiles;
    }

    public void setBackgroundTiles(Tile[] backgroundTiles) {
        this.backgroundTiles = backgroundTiles;
    }

    public Tile[] getGroundTiles() {
        return groundTiles;
    }

    public void setGroundTiles(Tile[] groundTiles) {
        this.groundTiles = groundTiles;
    }

    public Tile[] getBattlespriteTiles() {
        return battlespriteTiles;
    }

    public void setBattlespriteTiles(Tile[] battlespriteTiles) {
        this.battlespriteTiles = battlespriteTiles;
    }

    public Tile[] getWeaponTiles() {
        return weaponTiles;
    }

    public void setWeaponTiles(Tile[] weaponTiles) {
        this.weaponTiles = weaponTiles;
    }
    
    
}
