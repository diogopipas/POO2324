import javax.swing.*;
import java.awt.*;

public class LeaderBoardFrame extends JFrame {

    private LeaderBoardPanel leaderBoardPanel;

    public LeaderBoardFrame() {
        setTitle("Leaderboard");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new BorderLayout());

        leaderBoardPanel = new LeaderBoardPanel();
        add(leaderBoardPanel, BorderLayout.CENTER);
    }

    // Method to update the leaderboard
    public void updateLeaderboard(String[] data) {
        leaderBoardPanel.updateLeaderboard(data);
    }
}
