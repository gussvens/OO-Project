package zombienado_beta.utilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * A simple sound player
 * Created by Erik on 2016-05-18.
 */
public class SoundEffect {
    File soundFile;
    boolean isLooping;
    public SoundEffect(File sound, boolean loop) {
        soundFile = sound;
        isLooping = loop;
    }

    public SoundEffect(File sound) {
        soundFile = sound;
        isLooping = false;
    }

    /**
     * plays the sound effect
     */
    public void play(){
        Thread playThread = new Thread(() -> playSound());
        playThread.start();
    }

    /**
     * plays the sound effect with a lower volume based on the distance to the object
     * @param xSource
     * @param ySource
     * @param xPlayer
     * @param yPlayer
     */
    public void play(int xSource, int ySource, int xPlayer, int yPlayer){
        Thread playThread = new Thread(() -> playSound(xSource,ySource,xPlayer,yPlayer));
        playThread.start();
    }

    private void playSound(){
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            if(isLooping){
                sound.loop(Clip.LOOP_CONTINUOUSLY);
            }
            sound.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playSound(int xSource, int ySource, int xPlayer, int yPlayer){
        int dX = xSource - xPlayer;
        int dY = ySource - yPlayer;
        double distance = Math.sqrt((dX)*(dX)+(dY)*(dY));
        distance = distance/100;
        if(distance<2) distance = 2;
        float alfa = (float)(-20*Math.log(distance));

        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
            FloatControl volume = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(alfa);

            sound.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
