package ui.formatting;

import javax.swing.*;

// Creates component and adds it to a JPanel so that the size of component does not change with window size

public class ImmutableSizeComponent {

    public JPanel panel;

    // EFFECTS: given a JComponent, adds the component to a JPanel
    public ImmutableSizeComponent(JComponent component) {
        panel = new JPanel();
        panel.add(component);
    }

}
