package com.monopolio.managers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AudioManager {
    private final File audioFile;
    private Clip clip;
    private boolean muted;

    public AudioManager(File audioFile) {
        this.audioFile = audioFile;
        this.muted = false;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (!muted) {
            clip.setFramePosition(0); // Riavvia dall'inizio
            clip.start();
        }
    }

    public void mute() {
        clip.stop();
        muted = true;
    }

    public void unmute() {
        muted = false;
        clip.start();
    }

    public boolean isMuted() {
        return muted;
    }
}
