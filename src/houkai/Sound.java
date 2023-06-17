/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package houkai;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author TOSHIBA
 */
public class Sound {
    Clip clip;                    //--> Untuk membuka file audio dan mengimpor
    URL SoundURL[] = new URL[30]; //--> Untuk menyimpan file suara 
    
    public Sound() {
        SoundURL[0] = getClass().getResource("/sounds/mixkit-game-show-intro-943.wav");
        SoundURL[1] = getClass().getResource("/sounds/mixkit-sad-game-over-trombone-471.wav");
    }
    
    public void setFile(int i){ // metode 1
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(SoundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e){
        }
    }
    
    //--> Untuk memutar suara yg dipanggil
    public void play(){
        clip.start();
    }
    //--> Untuk mengeloop suara
    public void loop(){ 
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    //--> Untuk menghentikan suara
    public void stop(){
        clip.stop();
    }
    
}
