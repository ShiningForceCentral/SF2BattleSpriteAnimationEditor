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
    
    public static void exportDisassembly(BattleSpriteAnimation battlespriteanimation, String filepath){
        System.out.println("com.sfc.sf2.battlespriteanimation.io.DisassemblyManager.exportDisassembly() - Exporting disassembly ...");
        try{
            /*
                short animSpeed = (short)(battlespriteanimation.getAnimSpeed()&0xFFFF);
                short unknown = battlespriteanimation.getUnknown();
                
                Color[][] palettes = battlespriteanimation.getPalettes();
                byte[][] paletteBytes = new byte[palettes.length][];
            
                Tile[][] frames = battlespriteanimation.getFrames();
            
                byte[][] frameBytes = new byte[frames.length][];
                short[] frameOffsets = new short[frames.length];
                        
                short palettesOffset = (short) (frames.length * 2 + 2);
                
                for(int i=0;i<palettes.length;i++){
                    PaletteEncoder.producePalette(palettes[i]);
                    paletteBytes[i] = PaletteEncoder.getNewPaletteFileBytes();
                }
                
                int framesSize = 0;
                int totalSize = 6 + frames.length * 2 + palettes.length * 32;
                for(int i=0;i<frames.length;i++){
                    StackGraphicsEncoder.produceGraphics(frames[i]);
                    frameBytes[i] = StackGraphicsEncoder.getNewGraphicsFileBytes();
                    if(i==0){
                        frameOffsets[i] = (short)(frames.length * 2 + palettes.length * 32);
                        System.out.println("Frame "+i+" length="+frameBytes[i].length+", offset="+frameOffsets[i]);
                    }else{
                        int target = frameOffsets[i-1] + 6 + (i-1)*2 + frameBytes[i-1].length;
                        int offsetLocation = 6 + i*2;
                        frameOffsets[i] = (short)((target - offsetLocation)&0xFFFF);
                        System.out.println("Frame "+i+" length="+frameBytes[i].length+", offset="+frameOffsets[i]);
                    }
                    framesSize += frameBytes[i].length;
                    totalSize += frameBytes[i].length;
                }

                byte[] newBattleSpriteAnimationFileBytes = new byte[totalSize];
                        
                newBattleSpriteAnimationFileBytes[0] = (byte) ((animSpeed&0xFF00) >> 8);
                newBattleSpriteAnimationFileBytes[1] = (byte) (animSpeed&0xFF); 
                newBattleSpriteAnimationFileBytes[2] = (byte) ((unknown&0xFF00) >> 8);
                newBattleSpriteAnimationFileBytes[3] = (byte) (unknown&0xFF); 
                newBattleSpriteAnimationFileBytes[4] = (byte) ((palettesOffset&0xFF00) >> 8);
                newBattleSpriteAnimationFileBytes[5] = (byte) (palettesOffset&0xFF); 
                for(int i=0;i<frameOffsets.length;i++){
                    newBattleSpriteAnimationFileBytes[6+i*2] =  (byte) ((frameOffsets[i]&0xFF00) >> 8);
                    newBattleSpriteAnimationFileBytes[6+i*2+1] = (byte) (frameOffsets[i]&0xFF); 
                }
                for(int i=0;i<paletteBytes.length;i++){
                    System.arraycopy(paletteBytes[i], 0, newBattleSpriteAnimationFileBytes, 6+frameOffsets.length*2+i*32, 32);
                }
                for(int i=0;i<frameBytes.length;i++){
                    System.out.println("Writing frame "+i+" with length="+frameBytes[i].length+" at offset="+(int)(frameOffsets[i]+6+i*2));
                    System.arraycopy(frameBytes[i], 0, newBattleSpriteAnimationFileBytes, frameOffsets[i]+6+i*2, frameBytes[i].length);
                }
                Path graphicsFilePath = Paths.get(filepath);
                Files.write(graphicsFilePath,newBattleSpriteAnimationFileBytes);
                System.out.println(newBattleSpriteAnimationFileBytes.length + " bytes into " + graphicsFilePath);   
*/
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
