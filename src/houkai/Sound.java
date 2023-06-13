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
    Clip clip; // untuk membuka file audio dan mengimpor
    URL SoundURL[] = new URL [30]; // menggunakan URL ini untuk menyimpan file suara 
    
    public Sound() {
        SoundURL[0] = getClass().getResource("/sound/mixkit-game-show-intro-943.wav");
        SoundURL[1] = getClass().getResource("/sound/mixkit-sad-game-over-trombone-471.wav");
    }
    // ada 4 metode
        public void setFile(int i) { // metode 1
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(SoundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(ais);
            } catch (Exception e){
            }
        }
        public void play(){ // metode 2 untuk memutar suara yg dipanggil
            
            clip.start();
        }
        public void loop(){ // metode 3 untuk mengeloop suara
            
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        public void stop(){ // metode 4 ini untuk menghentikan suara
            
            clip.stop();
        }
    
}
