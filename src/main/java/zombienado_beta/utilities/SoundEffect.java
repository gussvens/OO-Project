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

    /**
     * Constructor for SoundEffect
     * @param sound - The File where the sound is
     * @param loop - If the Sound should loop or not
     */
    public SoundEffect(File sound, boolean loop) {
        soundFile = sound;
        isLooping = loop;
    }

    /**
     * Constructor for SoundEffect
     * @param sound - The File where the sound is
     */
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

    /**
     * Plays the sound file associated with this SoundEffect
     */
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

    /**
     * Plays a sound with different volume depending on distance to the source
     * @param xSource - The x position of the source of the sound
     * @param ySource - The y position of the source of the sound
     * @param xPlayer - The x position of the player
     * @param yPlayer - The y position of the player
     */
    private void playSound(int xSource, int ySource, int xPlayer, int yPlayer){
        int dX = xSource - xPlayer;
        int dY = ySource - yPlayer;
        double distance = Math.sqrt((dX)*(dX)+(dY)*(dY));
        distance = distance/80;
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
