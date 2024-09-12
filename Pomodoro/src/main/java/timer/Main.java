package timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static int counter = 60; // 60秒のカウントダウン
    static Timer timer;

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

        JButton startButton = new JButton("スタート");
        startButton.setPreferredSize(new Dimension(100, 45)); // ボタンのサイズを設定
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.add(startButton, gbc);

//        JButton resetButton = new JButton("リセット");
//        resetButton.setPreferredSize(new Dimension(100, 45)); // ボタンのサイズを設定
//        gbc.gridx = 0;
//        gbc.gridy = 2;
//        gbc.gridwidth = 1;
//        frame.add(resetButton, gbc);
//
        timer = new Timer(1000, new ActionListener() {
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

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();  // ボタンを押すとタイマーがスタート
                startButton.setEnabled(false);  // ボタンを無効にして再度押せないようにする
            }
        });
//
//        resetButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                timer.stop();  // タイマーを停止
//                counter = 60;  // カウンターを初期値にリセット
//                timerLabel.setText("残り時間: " + counter);  // ラベルの表示をリセット
//                startButton.setEnabled(true);  // スタートボタンを再び有効化
//            }
//        });

        frame.setVisible(true);
    }
}
