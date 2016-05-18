package zombienado_v1.utilities;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Erik on 2016-05-18.
 */
public class SoundEffect {
    File soundFile;
    public SoundEffect(File sound) {
        soundFile = sound;

    }

    public void play(){
        Thread playThread = new Thread(() -> playSound());
        playThread.start();
    }

    private void playSound(){
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(soundFile));
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
