package timer;

import javax.swing.*;
import java.awt.*;


public class Main {
    private static int counter = 60; // 60秒のカウントダウン
    static Timer timer;
    private static boolean running = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("ポモドーロタイマー");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        frame.setLayout(new GridBagLayout()); // GridBagLayoutを使用

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // マージンを追加

        JLabel timerLabel = new JLabel("残り時間: " + counter, SwingConstants.CENTER);
        Font labelFont = timerLabel.getFont();  // デフォルトのフォントを取得
        timerLabel.setFont(labelFont.deriveFont(24f));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        frame.add(timerLabel, gbc);

        // スタート/リセットボタン
        JButton startResetButton = new JButton("スタート");
        startResetButton.setPreferredSize(new Dimension(100, 40)); // ボタンのサイズを設定
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // ボタンが横に広がるように設定
        frame.add(startResetButton, gbc);

        // タイマーの設定
        timer = new Timer(1000, e -> {
            counter--;
            timerLabel.setText("残り時間: " + counter); // カウントダウンの表示更新

            if (counter <= 0) {
                ((Timer) e.getSource()).stop();
                timerLabel.setText("時間切れ！");
                startResetButton.setText("スタート"); // 時間切れ後は「スタート」に戻す
                running = false; // タイマーが停止している状態に設定
            }
        });

        // スタート/リセットボタンの動作
        startResetButton.addActionListener(e -> {
            if (!running) {
                // スタートボタンの処理
                timer.start();
                startResetButton.setText("リセット");
                running = true;
            } else {
                // リセットボタンの処理
                timer.stop();
                counter = 60;
                timerLabel.setText("残り時間: " + counter);
                startResetButton.setText("スタート");
                running = false;
            }
        });

        frame.setVisible(true);
    }
}
