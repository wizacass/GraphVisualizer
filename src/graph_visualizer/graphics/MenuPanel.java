package graph_visualizer.graphics;

import graph_visualizer.graph.GraphFactory;
import graph_visualizer.graph.GraphParser;
import graph_visualizer.graph.VisualGraph;
import graph_visualizer.utils.FileParser;
import graph_visualizer.utils.GraphFileManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

class MenuPanel extends JPanel
{
    private final WindowConstants constants = new WindowConstants();
    private final GraphFileManager fileManager = new GraphFileManager();
    private final FileParser fileParser = new FileParser();

    private JTextField countInputField;
    private JLabel errorLabel;
    private GraphPanel graphPanel;
    private JComboBox graphChooser;
    private JComboBox removeNodesList;
    private JComboBox addNodesList;
    private Map<Integer, String> labels;

    MenuPanel(GraphPanel panel)
    {
        this.InitializeWindow();
        this.InitializeComponents();
        this.graphPanel = panel;
        this.setLayout(null);
        this.labels = fileParser.CreateLabelDictionary();
    }

    private void InitializeComponents()
    {
        AddButtons();
        AddCountInput();
        AddErrorLabel();
        AddGraphLoader();
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
        var loadButton = new PanelButton("Load", GetButtonX(0), GetButtonY(3));
        var generateButton = new PanelButton("Generate", GetButtonX(1), GetButtonY(3));
        var clearButton = new PanelButton("Clear", GetButtonX(2), GetButtonY(3));
        var removeButton = new PanelButton("Remove Edge", GetButtonX(2), GetButtonY(2));
        var addEdgeButton = new PanelButton("Add Edge", GetButtonX(1), GetButtonY(2));

        loadButton.addActionListener(this::loadButtonClicked);
        generateButton.addActionListener(this::generateButtonClicked);
        clearButton.addActionListener(this::clearButtonClicked);
        removeButton.addActionListener(this::removeButtonClicked);
        addEdgeButton.addActionListener(this::addEdgeButtonClicked);

        this.add(loadButton);
        //this.add(generateButton);
        this.add(clearButton);
        this.add(removeButton);
        this.add(addEdgeButton);
    }

    private void AddCountInput()
    {
        countInputField = new JTextField("Node count");
        countInputField.setBounds(
                GetButtonX(1),
                GetButtonY(2),
                constants.ButtonWidth(),
                constants.ButtonHeight()
        );

        //this.add(countInputField);
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

    private void AddGraphLoader()
    {
        var names = fileManager.FindGraphFiles();
        graphChooser = new JComboBox(names);
        graphChooser.setBounds(constants.Margin(),GetButtonY(2), constants.ButtonWidth(), constants.ButtonHeight());

        this.add(graphChooser);
    }

    private int GetButtonX(int k)
    {
        return constants.Margin() * (k + 1) + constants.ButtonWidth() * k;
    }

    private int GetButtonY(int k)
    {
        return (constants.Margin() + constants.ButtonHeight()) * k;
    }

    private void loadButtonClicked(ActionEvent e)
    {
        errorLabel.setText("");

        var parser = new GraphParser();
        var file = graphChooser.getItemAt(graphChooser.getSelectedIndex());
        try
        {
            var text = fileManager.ReadFile(file.toString(), "graphs");
            var graph = (VisualGraph<Integer>)parser.CreateGraphFromIntegers(text, labels);
            updateNodesList(graph);

            graphPanel.setActiveGraph(graph);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            errorLabel.setText("");
        }
    }

    private void updateNodesList(VisualGraph<Integer> graph)
    {
        this.removeNodesList = new JComboBox(graph.GetLabels().toArray());
        this.addNodesList = new JComboBox(graph.GetLabels().toArray());

        this.removeNodesList.setBounds(GetButtonX(2), GetButtonY(1), constants.ButtonWidth(), constants.ButtonHeight());
        this.addNodesList.setBounds(GetButtonX(1), GetButtonY(1), constants.ButtonWidth(), constants.ButtonHeight());

        this.add(removeNodesList);
        this.add(addNodesList);
        this.updateUI();
    }

    private void generateButtonClicked(ActionEvent e)
    {
        errorLabel.setText("");
        String text = countInputField.getText();

        var factory = new GraphFactory();
        try
        {
            int count = Integer.parseUnsignedInt(text);
            var graph = factory.CreateRandomIntGraph(count, 2020);
            graphPanel.setActiveGraph((VisualGraph<Integer>) graph);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            errorLabel.setText("Wrong node count!");
        }
    }

    private void clearButtonClicked(ActionEvent e)
    {
        errorLabel.setText("");
        graphPanel.setActiveGraph(new VisualGraph<>());
        this.remove(removeNodesList);
        this.remove(addNodesList);
        this.updateUI();
    }

    private void removeButtonClicked(ActionEvent e)
    {
        var graph = graphPanel.getActiveGraph();
        var label1 = removeNodesList.getItemAt(removeNodesList.getSelectedIndex()).toString();
        var label2 = addNodesList.getItemAt(addNodesList.getSelectedIndex()).toString();
        graph.RemoveEdge(label1, label2);
        graphPanel.setActiveGraph(graph);
    }

    private void addEdgeButtonClicked(ActionEvent e)
    {
        var graph = graphPanel.getActiveGraph();
        var label1 = removeNodesList.getSelectedItem().toString();
        var label2 = addNodesList.getSelectedItem().toString();
        System.out.println(label1 + " > " + label2);
        graph.AddEdge(label1, label2);
        graphPanel.setActiveGraph(graph);
    }
}
