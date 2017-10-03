/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.battlesprite.animation.io;

import com.sfc.sf2.graphics.Tile;
import com.sfc.sf2.graphics.compressed.BasicGraphicsDecoder;
import com.sfc.sf2.graphics.compressed.BasicGraphicsEncoder;
import com.sfc.sf2.battlesprite.animation.BattleSpriteAnimation;
import com.sfc.sf2.battlesprite.animation.BattleSpriteAnimationFrame;
import com.sfc.sf2.graphics.compressed.StackGraphicsDecoder;
import com.sfc.sf2.graphics.compressed.StackGraphicsEncoder;
import com.sfc.sf2.palette.graphics.PaletteDecoder;
import com.sfc.sf2.palette.graphics.PaletteEncoder;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wiz
 */
public class DisassemblyManager {
    
    public static BattleSpriteAnimation importDisassembly(String filepath){
        System.out.println("com.sfc.sf2.battlespriteanimation.io.DisassemblyManager.importDisassembly() - Importing disassembly file ...");
        
        BattleSpriteAnimation battlespriteanimation = new BattleSpriteAnimation();
        try{
            Path path = Paths.get(filepath);
            if(path.toFile().exists()){
                byte[] data = Files.readAllBytes(path);
                
                battlespriteanimation.setFrameNumber(getNextByte(data,0));
                battlespriteanimation.setSpellInitFrame(getNextByte(data,1));
                battlespriteanimation.setSpellAnim(getNextByte(data,2));
                battlespriteanimation.setEndSpellAnim(getNextByte(data,3));
                battlespriteanimation.setIdle1WeaponFrame(getNextByte(data,4));
                battlespriteanimation.setIdle1WeaponZ(getNextByte(data,5));
                battlespriteanimation.setIdle1WeaponX(getNextByte(data,6));
                battlespriteanimation.setIdle1WeaponY(getNextByte(data,7));
                battlespriteanimation.setByte8(getNextByte(data,8));
                battlespriteanimation.setByte9(getNextByte(data,9));
                battlespriteanimation.setByte10(getNextByte(data,10));
                battlespriteanimation.setByte11(getNextByte(data,11));
                battlespriteanimation.setIdle2WeaponFrame(getNextByte(data,12));
                battlespriteanimation.setIdle2WeaponZ(getNextByte(data,13));
                battlespriteanimation.setIdle2WeaponX(getNextByte(data,14));
                battlespriteanimation.setIdle2WeaponY(getNextByte(data,15));
                
                BattleSpriteAnimationFrame[] frames = new BattleSpriteAnimationFrame[battlespriteanimation.getFrameNumber()-1];
                
                for(int i=0;i<frames.length;i++){
                    BattleSpriteAnimationFrame frame = new BattleSpriteAnimationFrame();
                    
                    frame.setIndex(getNextByte(data,16+i*8+0));
                    frame.setDuration(getNextByte(data,16+i*8+1));
                    frame.setX(getNextByte(data,16+i*8+2));
                    frame.setY(getNextByte(data,16+i*8+3));
                    frame.setWeaponFrame(getNextByte(data,16+i*8+4));
                    frame.setWeaponZ(getNextByte(data,16+i*8+5));
                    frame.setWeaponX(getNextByte(data,16+i*8+6));
                    frame.setWeaponY(getNextByte(data,16+i*8+7));
                    
                    frames[i] = frame;
                }
                
                battlespriteanimation.setFrames(frames);
                
            }            
        }catch(Exception e){
             System.err.println("com.sfc.sf2.battlespriteanimation.io.DisassemblyManager.parseGraphics() - Error while parsing graphics data : "+e);
             e.printStackTrace();
        }    
        System.out.println("com.sfc.sf2.battlespriteanimation.io.DisassemblyManager.importDisassembly() - Disassembly imported.");
        return battlespriteanimation;
    }
    
    public static void exportDisassembly(BattleSpriteAnimation anim, String filepath){
        System.out.println("com.sfc.sf2.battlespriteanimation.io.DisassemblyManager.exportDisassembly() - Exporting disassembly ...");
        try{
            
                int frameNumber = anim.getFrames().length;
                byte[] animationFileBytes = new byte[16 + frameNumber*8];
            
                animationFileBytes[0] = (byte)(frameNumber+1);
                animationFileBytes[1] = (byte)(anim.getSpellInitFrame());
                animationFileBytes[2] = (byte)(anim.getSpellAnim());
                animationFileBytes[3] = (byte)(anim.getEndSpellAnim());
                animationFileBytes[4] = (byte)(anim.getIdle1WeaponFrame());
                animationFileBytes[5] = (byte)(anim.getIdle1WeaponZ());
                animationFileBytes[6] = (byte)(anim.getIdle1WeaponX());
                animationFileBytes[7] = (byte)(anim.getIdle1WeaponY());
                animationFileBytes[8] = (byte)(anim.getByte8());
                animationFileBytes[9] = (byte)(anim.getByte9());
                animationFileBytes[10] = (byte)(anim.getByte10());
                animationFileBytes[11] = (byte)(anim.getByte11());
                animationFileBytes[12] = (byte)(anim.getIdle2WeaponFrame());
                animationFileBytes[13] = (byte)(anim.getIdle2WeaponZ());
                animationFileBytes[14] = (byte)(anim.getIdle2WeaponX());
                animationFileBytes[15] = (byte)(anim.getIdle2WeaponY());
                
                for(int i=0;i<frameNumber;i++){
                    animationFileBytes[16+i*8+0] = (byte)(anim.getFrames()[i].getIndex());
                    animationFileBytes[16+i*8+1] = (byte)(anim.getFrames()[i].getDuration());
                    animationFileBytes[16+i*8+2] = (byte)(anim.getFrames()[i].getX());
                    animationFileBytes[16+i*8+3] = (byte)(anim.getFrames()[i].getY());
                    animationFileBytes[16+i*8+4] = (byte)(anim.getFrames()[i].getWeaponFrame());
                    animationFileBytes[16+i*8+5] = (byte)(anim.getFrames()[i].getWeaponZ());
                    animationFileBytes[16+i*8+6] = (byte)(anim.getFrames()[i].getWeaponX());
                    animationFileBytes[16+i*8+7] = (byte)(anim.getFrames()[i].getWeaponY());
                }

                Path animFilePath = Paths.get(filepath);
                Files.write(animFilePath,animationFileBytes);
                System.out.println(animationFileBytes.length + " bytes into " + animFilePath);   

        } catch (Exception ex) {
            Logger.getLogger(DisassemblyManager.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.out.println(ex);
        }  
        System.out.println("com.sfc.sf2.battlespriteanimation.io.DisassemblyManager.exportDisassembly() - Disassembly exported.");        
    }     
    
    private static short getNextWord(byte[] data, int cursor){
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put(data[cursor+1]);
        bb.put(data[cursor]);
        short s = bb.getShort(0);
        return s;
    }
    
    private static byte getNextByte(byte[] data, int cursor){
        ByteBuffer bb = ByteBuffer.allocate(1);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.put(data[cursor]);
        byte b = bb.get(0);
        return b;
    }    

    
}
