import javax.swing.*;
import java.awt.*;

public class LeaderBoardPanel extends JPanel {

    private JList<String> leaderboardList;

    public LeaderBoardPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 600));

        // Sample data for the leaderboard
        String[] sampleData = {"Player 1: 100", "Player 2: 90", "Player 3: 80"};
        leaderboardList = new JList<>(sampleData);

        add(new JScrollPane(leaderboardList), BorderLayout.CENTER);
    }

    // Method to update the leaderboard
    public void updateLeaderboard(String[] data) {
        leaderboardList.setListData(data);
    }
}
