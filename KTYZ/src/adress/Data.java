package adress;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data {
	 private StringProperty key;
     public void setKey(String value) { keyProperty().set(value); }
     public String getKey() { return keyProperty().get(); }
     public StringProperty keyProperty() { 
         if (key == null) key = new SimpleStringProperty(this, "key");
         return key; 
     }
 
     private DoubleProperty value;
     public void setValue(Double value) { valueProperty().set(value); }
     public Double getValue() { return valueProperty().get(); }
     public DoubleProperty valueProperty() { 
         if (value == null) value = new SimpleDoubleProperty(this, "value");
         return value; 
     } 
     public Data(String key,Double value) {
    	 this.key = new SimpleStringProperty(key);
    	 this.value = new SimpleDoubleProperty(value);
     }
}
