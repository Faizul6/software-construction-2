
import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class FractalTreeApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Fractal Tree");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            FractalTreePanel treePanel = new FractalTreePanel();

            // Controls panel
            JPanel controls = new JPanel(new GridLayout(0, 1, 8, 8));
            controls.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

            // Depth slider
            JSlider depthSlider = new JSlider(0, 14, 10);
            depthSlider.setMajorTickSpacing(2);
            depthSlider.setPaintTicks(true);
            depthSlider.setPaintLabels(true);

            // Angle slider (degrees)
            JSlider angleSlider = new JSlider(0, 60, 25);
            angleSlider.setMajorTickSpacing(10);
            angleSlider.setPaintTicks(true);
            angleSlider.setPaintLabels(true);

            // Length factor slider (0.50 .. 0.85)
            JSlider factorSlider = new JSlider(50, 85, 70);
            factorSlider.setMajorTickSpacing(5);
            factorSlider.setPaintTicks(true);
            factorSlider.setPaintLabels(true);
            factorSlider.setLabelTable(makeFactorLabels());

            // Trunk length slider
            JSlider trunkSlider = new JSlider(50, 220, 120);
            trunkSlider.setMajorTickSpacing(25);
            trunkSlider.setPaintTicks(true);
            trunkSlider.setPaintLabels(true);

            // One listener for all sliders
            ChangeListener listener = e -> {
                treePanel.setMaxDepth(depthSlider.getValue());
                treePanel.setBranchAngleDeg(angleSlider.getValue());
                treePanel.setLengthFactor(factorSlider.getValue() / 100.0);
                treePanel.setTrunkLength(trunkSlider.getValue());
                treePanel.repaint();
            };

            depthSlider.addChangeListener(listener);
            angleSlider.addChangeListener(listener);
            factorSlider.addChangeListener(listener);
            trunkSlider.addChangeListener(listener);

            controls.add(new JLabel("Depth (recursion levels):"));
            controls.add(depthSlider);
            controls.add(new JLabel("Branch angle (degrees):"));
            controls.add(angleSlider);
            controls.add(new JLabel("Length factor (shrink per level):"));
            controls.add(factorSlider);
            controls.add(new JLabel("Initial trunk length:"));
            controls.add(trunkSlider);

            frame.setLayout(new BorderLayout());
            frame.add(treePanel, BorderLayout.CENTER);
            frame.add(controls, BorderLayout.EAST);

            frame.setSize(980, 680);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // apply initial slider values
            listener.stateChanged(null);
        });
    }

    private static Hashtable<Integer, JLabel> makeFactorLabels() {
        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(50, new JLabel("0.50"));
        labels.put(60, new JLabel("0.60"));
        labels.put(70, new JLabel("0.70"));
        labels.put(80, new JLabel("0.80"));
        labels.put(85, new JLabel("0.85"));
        return labels;
    }
}

class FractalTreePanel extends JPanel {

    private int maxDepth = 10;
    private double branchAngleDeg = 25.0;
    private double lengthFactor = 0.70;
    private int trunkLength = 120;

    public FractalTreePanel() {
        setBackground(Color.WHITE);
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void setBranchAngleDeg(double branchAngleDeg) {
        this.branchAngleDeg = branchAngleDeg;
    }

    public void setLengthFactor(double lengthFactor) {
        this.lengthFactor = lengthFactor;
    }

    public void setTrunkLength(int trunkLength) {
        this.trunkLength = trunkLength;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int startX = getWidth() / 2;
        int startY = getHeight() - 40;

        // angle -90 means "straight up" in screen coordinates (y grows downward)
        drawBranch(g2d, startX, startY, trunkLength, -90.0, maxDepth);
    }

    private void drawBranch(Graphics2D g2d, int x1, int y1, double length, double angleDeg, int depth) {
        // Base case: stop recursion
        if (depth <= 0 || length < 2) return;

        // Compute end point using trigonometry
        double rad = Math.toRadians(angleDeg);
        int x2 = x1 + (int) Math.round(length * Math.cos(rad));
        int y2 = y1 + (int) Math.round(length * Math.sin(rad));

        // Draw line (branch)
        g2d.setStroke(new BasicStroke(Math.max(1f, depth * 0.35f))); // thicker near trunk
        g2d.setColor(new Color(90, 50, 25)); // brown-ish
        g2d.drawLine(x1, y1, x2, y2);

        // Recursive calls: two smaller branches
        double newLength = length * lengthFactor;
        drawBranch(g2d, x2, y2, newLength, angleDeg - branchAngleDeg, depth - 1);
        drawBranch(g2d, x2, y2, newLength, angleDeg + branchAngleDeg, depth - 1);
    }
}
