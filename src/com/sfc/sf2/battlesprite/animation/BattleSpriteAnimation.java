/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.battlesprite.animation;

import com.sfc.sf2.battlesprite.animation.layout.BattleSpriteAnimationLayout;
import com.sfc.sf2.graphics.Tile;
import java.awt.Color;

/**
 *
 * @author wiz
 */
public class BattleSpriteAnimation {
    
    public static final int TYPE_ALLY = 0;
    public static final int TYPE_ENEMY = 1;
    
    private int type;
    
    private BattleSpriteAnimationFrame[] frames;
    
    private int frameNumber;
    private int spellInitFrame;
    private int spellAnim;
    private int endSpellAnim;
    
    private int idle1WeaponFrame;
    private int idle1WeaponZ;
    private int idle1WeaponX;
    private int idle1WeaponY;
    
    private int byte8;
    private int byte9;
    private int byte10;
    private int byte11;

    private int idle2WeaponFrame;
    private int idle2WeaponZ;
    private int idle2WeaponX;
    private int idle2WeaponY;    
    
    private BattleSpriteAnimationLayout layout;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BattleSpriteAnimationFrame[] getFrames() {
        return frames;
    }

    public void setFrames(BattleSpriteAnimationFrame[] frames) {
        this.frames = frames;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getSpellInitFrame() {
        return spellInitFrame;
    }

    public void setSpellInitFrame(int spellInitFrame) {
        this.spellInitFrame = spellInitFrame;
    }

    public int getSpellAnim() {
        return spellAnim;
    }

    public void setSpellAnim(int spellAnim) {
        this.spellAnim = spellAnim;
    }

    public int getEndSpellAnim() {
        return endSpellAnim;
    }

    public void setEndSpellAnim(int endSpellAnim) {
        this.endSpellAnim = endSpellAnim;
    }

    public int getIdle1WeaponFrame() {
        return idle1WeaponFrame;
    }

    public void setIdle1WeaponFrame(int idle1WeaponFrame) {
        this.idle1WeaponFrame = idle1WeaponFrame;
    }

    public int getIdle1WeaponZ() {
        return idle1WeaponZ;
    }

    public void setIdle1WeaponZ(int idle1WeaponZ) {
        this.idle1WeaponZ = idle1WeaponZ;
    }

    public int getIdle1WeaponX() {
        return idle1WeaponX;
    }

    public void setIdle1WeaponX(int idle1WeaponX) {
        this.idle1WeaponX = idle1WeaponX;
    }

    public int getIdle1WeaponY() {
        return idle1WeaponY;
    }

    public void setIdle1WeaponY(int idle1WeaponY) {
        this.idle1WeaponY = idle1WeaponY;
    }

    public int getByte8() {
        return byte8;
    }

    public void setByte8(int byte8) {
        this.byte8 = byte8;
    }

    public int getByte9() {
        return byte9;
    }

    public void setByte9(int byte9) {
        this.byte9 = byte9;
    }

    public int getByte10() {
        return byte10;
    }

    public void setByte10(int byte10) {
        this.byte10 = byte10;
    }

    public int getByte11() {
        return byte11;
    }

    public void setByte11(int byte11) {
        this.byte11 = byte11;
    }

    public int getIdle2WeaponFrame() {
        return idle2WeaponFrame;
    }

    public void setIdle2WeaponFrame(int idle2WeaponFrame) {
        this.idle2WeaponFrame = idle2WeaponFrame;
    }

    public int getIdle2WeaponZ() {
        return idle2WeaponZ;
    }

    public void setIdle2WeaponZ(int idle2WeaponZ) {
        this.idle2WeaponZ = idle2WeaponZ;
    }

    public int getIdle2WeaponX() {
        return idle2WeaponX;
    }

    public void setIdle2WeaponX(int idle2WeaponX) {
        this.idle2WeaponX = idle2WeaponX;
    }

    public int getIdle2WeaponY() {
        return idle2WeaponY;
    }

    public void setIdle2WeaponY(int idle2WeaponY) {
        this.idle2WeaponY = idle2WeaponY;
    }

    public BattleSpriteAnimationLayout getLayout() {
        return layout;
    }

    public void setLayout(BattleSpriteAnimationLayout layout) {
        this.layout = layout;
    }
}
