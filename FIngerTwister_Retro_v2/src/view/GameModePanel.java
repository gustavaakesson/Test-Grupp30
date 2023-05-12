package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameModePanel extends JPanel implements ActionListener {
    private View view;
    private JComboBox<String> gameModeChoser;
    private String [] choices = new String[]{"FingerTwister", "TypingRace"};
    private JButton startButton;
    private int count = 5;
    private Timer secTimer;
    private JLabel gameModeText;
    private JLabel emptyLabel;
    private JLabel scoreboardText;

    public GameModePanel(View view) {
        this.view = view;
        addNorthPanel();
    }

    private void addNorthPanel() {
        addStartButton();
        addGameModeComboBox();
        addGameModePanel();
        addScoreboardText();

        this.add(scoreboardText);
        this.add(addEmptyLabel500());
        this.add(startButton);
        this.add(addEmptyLabel300());
        this.add(emptyLabel);
        this.add(gameModeText);
        this.add(gameModeChoser);

    }

    private void addScoreboardText() {
        scoreboardText = new JLabel("Scoreboard");
        scoreboardText.setFont(new Font("Font.ITALIC",Font.BOLD,35));
        scoreboardText.setPreferredSize(new Dimension(300,100));
    }

    private void addGameModePanel() {
        gameModeText = new JLabel("Gamemode:");
        gameModeText.setFont(new Font("Font.ITALIC",Font.ITALIC,30));
    }

    private void addGameModeComboBox() {
        gameModeChoser = new JComboBox<>(choices);
        gameModeChoser.addActionListener(this);
        gameModeChoser.setPreferredSize(new Dimension(300,100));
        gameModeChoser.setFont(new Font("Font.ITALIC",Font.ITALIC,30));
    }

    private void addStartButton() {
        startButton = new JButton("Start");
        startButton.setFont(new Font("Font.ITALIC",Font.ITALIC,54));
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(300,100));
    }

    private JLabel addEmptyLabel500(){
        emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(500,100));
        return emptyLabel;
    }
    private JLabel addEmptyLabel300(){
        emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(300,100));
        return emptyLabel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton){
            startButton.setText("5");
            secTimer = new Timer(1000,this);
            secTimer.start();
        }
        if (e.getSource() instanceof Timer){
            count--;
            startButton.setText(String.valueOf(count));
            if (count == 0 || count < 0){
                secTimer.stop();
                startButton.setText("STOP");
                // TODO: Istället för att starta ett nytt spel ska man skickas tillbaka till GameModePanel
                // transferFocus används för att ge fokus till GamePanel i fönstret för att den ska lyssna efter keystrokes.
                view.getGamePanel().transferFocus();
                //view.getGamePanel().createKeyboard();
                //view.setGamePanel(new GamePanel(view));
                view.getController().startGame();

            }
        }

        if (e.getSource() instanceof JComboBox){
            if (((JComboBox<?>) e.getSource()).getSelectedItem() == "FingerTwister"){
                JOptionPane.showMessageDialog(null,"You are now playing FingerTwister!");
            }
            if (((JComboBox<?>) e.getSource()).getSelectedItem() == "TypingRace"){
                JOptionPane.showMessageDialog(null,"You are now playing TypingRace!");
            }

        }
    }

    public JComboBox<String> getGameModeChoser() {
        return gameModeChoser;
    }

    public void setGameModeChoser(JComboBox<String> gameModeChoser) {
        this.gameModeChoser = gameModeChoser;
    }
}
