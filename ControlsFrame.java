package edu.foothill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 * CS1A, Assignment 5, "5 Graphical User Interfaces (GUI) and Events" <br>
 * Quarter: Fall 2019 <br>
 * This class creates a JFrame that is used to control the size of another
 * JFrame that implements the ImageDisplay class.<br>
 * This class contains a constructor, a single method and 2 inner classes used
 * for event handling. The purpose of this assignment is to work with object
 * oriented programming by correctly implementing the java.awt library to create
 * a poster displayer.
 * 
 * 
 * @author Kevin Morales-Nguyen
 * @author Andrew Lasko
 */
public class ControlsFrame extends JFrame
{
    private JFrame posterDisplay;
    private JPanel panelMain, panelCenter;
    private ImageDisplay imageDisplay;
    private JOptionPane inputError;
    private JComboBox posterSelect;
    private JLabel widthLabel, heightLabel, mainPanelLabel;
    JTextField widthInput, heightInput;
    private JButton setDimensions;
    private String[] posters =
    { "NGNL0", "Code Geass", "Cowboy Bebop", "Steins Gate", "fate", "Koe No Katachi", "sao Ordinal Scale",
            "Kimi No Na Wa", "Violet Evergarden", "Magica Madoka" };
    private String[] posterLocations =
    { "NGNL0.jpg", "Code Geass.jpg", "Cowboy Bebop.jpg", "Steins Gate.jpg", "fate.jpg", "Koe No Katachi.jpg",
            "sao Ordinal Scale.jpg", "Kimi No Na Wa.jpg", "Violet Evergarden.jpg", "Magica Madoka.jpg" };
    private int[] posterWidths =
    { 655, 566, 354, 415, 515, 515, 465, 577, 591, 515 };
    private int[] posterHeights =
    { 944, 835, 535, 564, 748, 739, 714, 875, 755, 770 };
    private int posterIndex;

    /**
     * This is the constructor for the ControlsFrame class, The first block in the
     * constructor creates all of the Graphical components that will be used to
     * create two JFrames, the first frame will consist of two JPanels, a JComboBox,
     * 3 labels, and 2 text fields, 1 JButton. The second block is where everything
     * is added to the appropriate JFrames.The last block adds the listeners to the
     * intended graphical components, JButton and JComboBox.
     * 
     * @param title the title of the Controls Frame JFrame
     */
    public ControlsFrame(String title) throws HeadlessException
    {
        super(title);
        posterDisplay = new JFrame("Poster");
        mainPanelLabel = new JLabel("Choose a poster from the dropdown.");
        panelMain = new JPanel(new GridLayout(1, 1, 10, 10));
        panelCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
        imageDisplay = new ImageDisplay();
        inputError = new JOptionPane();
        posterSelect = new JComboBox(posters);
        widthLabel = new JLabel("Width:");
        heightLabel = new JLabel("Height:");
        widthInput = new JTextField(7);
        heightInput = new JTextField(7);
        setDimensions = new JButton("Set Size");

        setLayout(new BorderLayout(20, 10));
        add(mainPanelLabel, BorderLayout.NORTH);
        add(panelMain, BorderLayout.CENTER);
        panelMain.add(panelCenter);
        panelCenter.add(posterSelect);
        panelCenter.add(widthLabel);
        panelCenter.add(widthInput);
        panelCenter.add(heightLabel);
        panelCenter.add(heightInput);
        panelCenter.add(setDimensions);
        panelCenter.setBorder(new TitledBorder("Movie Poster"));
        posterDisplay.add(imageDisplay);
        posterDisplay.setSize(600, 900);
        posterDisplay.setLocationRelativeTo(this);
        posterDisplay.setVisible(true);

        itemsListener myListener = new itemsListener();
        actionsListener actListener = new actionsListener();
        posterSelect.addItemListener(myListener);
        setDimensions.addActionListener(actListener);
        imageDisplay.setImagePath(posterLocations[0]);
        updateDisplay();
        posterIndex = 0;
    }

    /**
     * This method is used to update the display on the second frame that implements
     * the imageDisplay. It sets the image path and then changes the size of the
     * frame to a default size of 600 width 900 height.
     */
    public void updateDisplay()
    {
        imageDisplay.setImagePath(posterLocations[posterIndex]);
        posterDisplay.setSize(posterWidths[posterIndex], posterHeights[posterIndex]);
    }

    /**
     * This class implements the ItemListener class, which is used to detect a
     * change in state variables.
     */
    class itemsListener implements ItemListener
    {
        /**
         * This method is used to change the posterIndex according to a change in the
         * JCombobox
         */
        public void itemStateChanged(ItemEvent e)
        {
            posterIndex = posterSelect.getSelectedIndex();
            updateDisplay();
        }
    }

    /**
     * This class implements the ActionListener to handle action events within a
     * JFrame.
     */
    class actionsListener implements ActionListener
    {
        /**
         * This method is used to change the width and height of the JFrame that shows
         * the poster, if the button is clicked the method will update the dimensions if
         * the input is valid. Otherwise a error message is displayed prompting the user
         * for valid input.
         */
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                int tempWidth = Integer.parseInt(widthInput.getText());
                int tempHeight = Integer.parseInt(heightInput.getText());
                if (tempWidth > 0 && tempHeight > 0)
                {
                    posterDisplay.setSize(tempWidth, tempHeight);
                } else
                {
                    inputError.showMessageDialog(null, "Please input valid whole numbers for width and height.");
                }
            } catch (Exception a)
            {
                inputError.showMessageDialog(null, "Please input valid whole numbers for width and height.");
            }
        }
    }
}
