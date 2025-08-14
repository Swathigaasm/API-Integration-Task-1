import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
public class SimpleGUI{
    public static void main(String[] args){
        JFrame frame = new JFrame("Simple Music Player");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel songLabel = new JLabel("No song selected");
        songLabel.setBounds(20, 20, 350, 30);
        frame.add(songLabel);

        JButton browseButton = new JButton("Browse");
        browseButton.setBounds(20, 60, 100, 30);
        frame.add(browseButton);

        JButton playButton = new JButton("Play");
        playButton.setBounds(20, 100, 100, 30);
        frame.add(playButton);

        JButton pauseButton = new JButton("Pause");
        pauseButton.setBounds(130, 100, 100, 30);
        frame.add(pauseButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(240, 100, 100, 30);
        frame.add(stopButton);

        final File[] audioFile = new File[1];

        // Browse action
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                audioFile[0] = fileChooser.getSelectedFile();
                songLabel.setText("Selected: " + audioFile[0].getName());
            }
        });

        // Play action
        playButton.addActionListener(e -> {
            if (audioFile[0] != null) {
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile[0]);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error playing file.");
                    ex.printStackTrace();
                }
            }
        });

        // Pause action
        pauseButton.addActionListener(e -> {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }
        });

        // Stop action
        stopButton.addActionListener(e -> {
            if (clip != null) {
                clip.stop();
                clip.setMicrosecondPosition(0);
            }
        });

        frame.setVisible(true);
    }
}


        


