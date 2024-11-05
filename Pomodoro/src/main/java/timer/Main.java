package timer;

import javax.swing.*;
import java.awt.*;


public class Main {
    private static final int WORK_TIME = 60;
    private static final int BREAK_TIME = 30;
    private static int counter = WORK_TIME;
    private static boolean isWorkTime = true;// 60秒のカウントダウン
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
            timerLabel.setText((isWorkTime ? "作業時間: " : "休憩時間: ") + counter); // カウントダウンの表示更新

            if (counter <= 0) {
                timer.stop();
                if (isWorkTime) {
                    // 作業時間終了 -> 休憩時間開始
                    isWorkTime = false;
                    counter = BREAK_TIME;
                    timerLabel.setText("休憩時間: " + counter);
                } else {
                    // 休憩時間終了 -> 作業時間に戻る
                    isWorkTime = true;
                    counter = WORK_TIME;
                    timerLabel.setText("作業時間: " + counter);
                }
                timer.start(); // 次のタイマーを自動的に開始
            }
        });

        // スタート/リセットボタンの動作
        startResetButton.addActionListener(e -> {
            if (!running) {
                // スタートボタンの処理
                timer.start();  // タイマーを開始
                startResetButton.setText("リセット");  // ボタンのラベルを「リセット」に変更
                running = true; // タイマーが動作している状態に設定
            } else {
                // リセットボタンの処理
                timer.stop();  // タイマーを停止
                counter = WORK_TIME;  // カウンターを作業時間の初期値にリセット
                isWorkTime = true; // 作業時間に戻す
                timerLabel.setText("作業時間: " + counter);  // ラベルに初期作業時間を再表示
                startResetButton.setText("スタート");  // ボタンのラベルを「スタート」に変更
                running = false; // タイマーが停止している状態に設定
            }
        });

        frame.setVisible(true);
    }
}
