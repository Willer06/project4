package project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Controls sandwich bread, protein, toppings, prices, and combo orders.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class SandwichController {
    /** Largest offered sandwich quantity. */
    private static final int MAX_QUANTITY = 10;
    /** Brioche choice. */
    @FXML private RadioButton briocheRadio;
    /** Wheat bread choice. */
    @FXML private RadioButton wheatRadio;
    /** Pretzel choice. */
    @FXML private RadioButton pretzelRadio;
    /** Bagel choice. */
    @FXML private RadioButton bagelRadio;
    /** Sourdough choice. */
    @FXML private RadioButton sourdoughRadio;
    /** Roast beef choice. */
    @FXML private RadioButton roastBeefRadio;
    /** Salmon choice. */
    @FXML private RadioButton salmonRadio;
    /** Chicken choice. */
    @FXML private RadioButton chickenRadio;
    /** Lettuce topping. */
    @FXML private CheckBox lettuceCheck;
    /** Tomatoes topping. */
    @FXML private CheckBox tomatoesCheck;
    /** Onions topping. */
    @FXML private CheckBox onionsCheck;
    /** Avocado topping. */
    @FXML private CheckBox avocadoCheck;
    /** Cheese topping. */
    @FXML private CheckBox cheeseCheck;
    /** Selected sandwich quantity. */
    @FXML private ComboBox<Integer> quantityComboBox;
    /** Read-only running sandwich price. */
    @FXML private TextField priceTextField;

    /** Initializes bread/protein toggle groups and quantity choices. */
    @FXML
    public void initialize() {
        ToggleGroup breads = new ToggleGroup();
        
        RadioButton[] breadOptions = {briocheRadio, wheatRadio, pretzelRadio, bagelRadio, sourdoughRadio};
        for(RadioButton radio : breadOptions) radio.setToggleGroup(breads);
        briocheRadio.setSelected(true);
        
        ToggleGroup proteins = new ToggleGroup();
        roastBeefRadio.setToggleGroup(proteins);
        salmonRadio.setToggleGroup(proteins);
        chickenRadio.setToggleGroup(proteins);
        roastBeefRadio.setSelected(true);
        
        for(int quantity = 1; quantity <= MAX_QUANTITY; quantity++) {
            quantityComboBox.getItems().add(quantity);
        }
        quantityComboBox.setValue(1);
        priceTextField.setEditable(false);
        registerListeners();
        updatePrice();
    }

    /** Registers change listeners for all sandwich choices. */
    private void registerListeners() {
        RadioButton[] radios = {briocheRadio, wheatRadio, pretzelRadio, bagelRadio, sourdoughRadio, roastBeefRadio, salmonRadio, chickenRadio};
        CheckBox[] extras = {lettuceCheck, tomatoesCheck, onionsCheck, avocadoCheck, cheeseCheck};
        
        for(RadioButton radio : radios) {
            radio.selectedProperty().addListener((obs, before, after) -> updatePrice());
        }

        for(CheckBox option : extras) {
            option.selectedProperty().addListener((obs, before, after) -> updatePrice());
        }

        quantityComboBox.valueProperty().addListener((obs, before, after) -> updatePrice());
    }

    /**
     * Determines which bread is selected.
     *
     * @return chosen bread type.
     */
    private Bread selectedBread() {
        if (wheatRadio.isSelected())        return Bread.WHEAT_BREAD;
        if (pretzelRadio.isSelected())      return Bread.PRETZEL;
        if (bagelRadio.isSelected())        return Bread.BAGEL;
        if (sourdoughRadio.isSelected())    return Bread.SOURDOUGH;
        return Bread.BRIOCHE;
    }

    /**
     * Builds one sandwich from the selected options.
     *
     * @param quantity number of sandwiches to include.
     * @return selected sandwich with toppings.
     */
    private Sandwich buildSandwich(int quantity) {
        Protein protein = salmonRadio.isSelected() ? Protein.SALMON : (chickenRadio.isSelected() ? Protein.CHICKEN : Protein.ROAST_BEEF);
        Sandwich item = new Sandwich(quantity, selectedBread(), protein);
        
        if (lettuceCheck.isSelected())  item.appendAddOn(AddOns.LETTUCE);
        if (tomatoesCheck.isSelected()) item.appendAddOn(AddOns.TOMATOES);
        if (onionsCheck.isSelected())   item.appendAddOn(AddOns.ONIONS);
        if (avocadoCheck.isSelected())  item.appendAddOn(AddOns.AVOCADO);
        if (cheeseCheck.isSelected())   item.appendAddOn(AddOns.CHEESE);
        return item;
    }

    /** Displays the live total for customized sandwiches. */
    @FXML
    private void updatePrice() {
        Integer quantity = quantityComboBox.getValue();
        if (quantity == null || quantity < 1) {
            priceTextField.clear();
            return;
        }

        priceTextField.setText(String.format("%.2f", buildSandwich(quantity).price()));
    }

    /** Adds the selected sandwiches to Current Order. */
    @FXML
    private void addToOrder() {
        Integer quantity = quantityComboBox.getValue();
        if (quantity == null || quantity < 1) {
            new Alert(Alert.AlertType.WARNING, "Select a valid quantity.").showAndWait();
            return;
        }

        CurrentOrderController.currOrder.addItem(buildSandwich(quantity));
        new Alert(Alert.AlertType.INFORMATION, "Sandwich added to Current Order.").showAndWait();
    }

    /** Opens combo customization with the selected sandwich. */
    @FXML
    private void openComboView() {
        Integer quantity = quantityComboBox.getValue();
        if (quantity == null || quantity < 1) return;
        
        ComboController.prepareCombo(buildSandwich(1), quantity);
        ViewController.switchView("/combo-view.fxml");
        ComboController.refreshPreparedCombo();
    }

    /** Returns to the main menu. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }
}
