package zombienado_v1.utilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
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

    public void play(){
        Thread playThread = new Thread(() -> playSound());
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

    private void playSound(int xSource, int ySource, int xCamera, int yCamera){
        int dX = xSource - xCamera;
        int dY = ySource - yCamera;
        double distance = Math.sqrt((dX)*(dX)+(dY)*(dY));
        int alfa = 2 - (int)(Math.log(distance));

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
