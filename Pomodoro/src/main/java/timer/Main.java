package timer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static int counter = 60; // 60秒のカウントダウン

    public static void main(String[] args) {
        JFrame frame = new JFrame("タイマー");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JLabel timerLabel = new JLabel("残り時間: " + counter, SwingConstants.CENTER);
        frame.add(timerLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                timerLabel.setText("残り時間: " + counter);

                if (counter <= 0) {
                    ((Timer) e.getSource()).stop();
                    timerLabel.setText("時間切れ！");
                }
            }
        });

        frame.setVisible(true);
        timer.start();
    }
}
