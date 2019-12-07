package graph_visualizer.graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;

class MenuPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();

    private JTextField countInputField;
    private JLabel errorLabel;
    private GraphPanel graphPanel;

    MenuPanel(GraphPanel panel)
    {
        this.InitializeWindow();
        this.InitializeComponents();
        this.graphPanel = panel;
        this.setLayout(null);
    }

    private void InitializeComponents()
    {
        AddButtons();
        AddCountInput();
        AddErrorLabel();
    }

    private void InitializeWindow()
    {
        this.setBounds(
                0,
                constants.WindowHeight() - constants.MenuPanelHeight(),
                constants.WindowWidth(),
                constants.MenuPanelHeight()
        );
        this.setBackground(constants.MenuPanelColor());
    }

    private void AddButtons()
    {
        int y = constants.MenuPanelHeight() - constants.Margin() - constants.ButtonHeight();

        var loadButton = new PanelButton("Load", GetButtonX(0), y);
        var generateButton = new PanelButton("Generate", GetButtonX(1), y);
        var clearButton = new PanelButton("Clear", GetButtonX(2), y);

        generateButton.addActionListener(this::generateButtonClicked);
        clearButton.addActionListener(this::clearButtonClicked);

        this.add(loadButton);
        this.add(generateButton);
        this.add(clearButton);
    }

    private void AddCountInput()
    {
        int y = constants.MenuPanelHeight() - (constants.Margin() + constants.ButtonHeight()) * 2;

        countInputField = new JTextField("Node count");
        countInputField.setBounds(GetButtonX(1), y, constants.ButtonWidth(), constants.ButtonHeight());

        this.add(countInputField);
    }

    private void AddErrorLabel()
    {
        int x = this.getWidth() / 2 - constants.ButtonWidth();

        errorLabel = new JLabel();
        errorLabel.setForeground(constants.ErrorTextColor());
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setBounds(x, constants.Margin(), constants.ButtonWidth() * 2, constants.ButtonHeight());

        this.add(errorLabel);
    }

    private int GetButtonX(int k)
    {
        return constants.Margin() * (k + 1) + constants.ButtonWidth() * k;
    }

    private void generateButtonClicked(ActionEvent e)
    {
        errorLabel.setText("");
        String text = countInputField.getText();

        try
        {
            int count = Integer.parseUnsignedInt(text);
            graphPanel.setCount(count);
        }
        catch (Exception ex)
        {
            errorLabel.setText("Wrong node count!");
        }
    }

    private void clearButtonClicked(ActionEvent e)
    {
        graphPanel.setCount(0);
    }
}
