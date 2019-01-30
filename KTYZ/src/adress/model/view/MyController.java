package adress.model.view;

import java.net.URL;
import java.util.*;
import adress.Data;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


public class MyController {
    @FXML
    private Button ButtonCheck;
    @FXML
    private TextArea MainTextArea;
    @FXML
    private Label LabelErrors;
    @FXML
    private Label LabelVar;
    @FXML
    private TableView<Data> MyTable;
    @FXML
    private TableColumn<Data, String> Key;
    @FXML
    private TableColumn<Data, Double> Value;
    
 


    @FXML
    public void ShowButtonClick(ActionEvent event) {
    	  
        String inputtext = MainTextArea.getText();
        String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String enalphabet = "zyxwvutsrqponmlkjihgfedcbaZYXWVUTSRQPONMLKJIHGFEDCBA";
        String numeric = "01234567";
        String numeric1 = "01234567.";
        String numeric2 = "01234567. ";
        String symbols = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ01234567";
        String numericwr = "89";
        String operaz = "+-*/^";
        String krit1 = "Программа";
        String krit2 = "Конец";
        char error = '0';
        String[] kritzv = new String[] { "Выполнить", "Зарезервироват<ь", "Первое", "Второе" };
        List<Data> list = new ArrayList<Data>();
        ObservableList<Data> variable = FXCollections.observableList(list);
        ArrayList<String> operazii = new ArrayList<String>();
        ArrayList<String> vlogenost = new ArrayList<String>();
        ArrayList<Double> operandy = new ArrayList<Double>();
        int kp = 0;
        int kpn = 0;
        int ind = -1;
        int dot = 0;
        int number = 0;
        int vse = 0;
        int vse1 = 3;
        int zap = 0;
        int zap1 = 0;
        int zap2 = 0;
        int skobnach = 0;
        int skobkonz = 0;
        int prob = 0;
        int perem = 0;
        int firstminus = 0;
        int m = 0;
        double itog = 0;
        double itog2 = 0;
        double itog3 = 0;
        boolean kp1 = false;
        boolean kp2 = false;
        boolean kp3 = false;
        boolean kp4 = false;
        String word = new String();
        char[] elem = new char[inputtext.length()];
        elem = inputtext.toCharArray();
        // Проверка слова Программа
        for (int i = 0; i < inputtext.length(); i++) {
            if ((ind = alphabet.indexOf(elem[i])) != -1) {
                word += elem[i];
                kp++;
            } else {
                break;
            }
        }
        String tekst = new String(elem);
        if (word.equals(krit1) == true) {
            kp1 = true;
        } else {
            kp1 = false;
            LabelErrors.setText("Ожидалось слово Программа, а встречено - " + word);
            LabelVar.setText(tekst);
            MainTextArea.selectRange(0, kp + 1);
            MainTextArea.setStyle("-fx-text-inner-color: red;");
            return;
        }
        if (kp1 == true) {
            //Проверка на вход в звено
            if (elem[kp] == ' ') {
                do {
                    word = new String();
                    kp++;
                    kpn = kp;
                    for (int j = (kp); j < inputtext.length(); j++) {
                        if ((ind = alphabet.indexOf(elem[j])) != -1) {
                            word += elem[j];
                            kp++;
                        } else {
                            break;
                        }
                    }
                    if (word.equals("Выполнить") == true || word.equals("Зарезервировать") == true) {
                        if (elem[kp] == ':') {
                            word = new String();
                            kp++;
                            kpn = kp;
                            if (elem[kp] == ' ' && number == 0) {
                                LabelErrors.setText("Лишний пробел после : ");
                                LabelVar.setText(tekst);
                                MainTextArea.selectRange(kpn, kp + 1);
                                MainTextArea.setStyle("-fx-text-inner-color: red;");
                                return;
                            }
                            do {
                                outer: for (int k = kp; k < inputtext.length(); k++) {
                                    if ((ind = numeric.indexOf(elem[k])) != -1) {
                                        word += elem[k];
                                        number++;
                                        kp++;
                                        continue outer;
                                    }
                                    if (elem[k] == '.') {
                                        if ((ind = numeric.indexOf(elem[k-1])) != -1) {
                                            if ((ind = numeric.indexOf(elem[k+1])) != -1) {
                                                word += elem[k];
                                                dot++;
                                                kp++;
                                                continue outer;
                                            }
                                            if (elem[k+1] == ' '){
                                                LabelErrors.setText("Лишняя точка в конце вещественного");
                                                LabelVar.setText(tekst);
                                                MainTextArea.selectRange(k, k + 1);
                                                MainTextArea.setStyle("-fx-text-inner-color: red;");
                                                return;
                                            }
                                        }
                                        if (elem[k-1] == ':') {
                                            LabelErrors.setText("Лишняя точка в начале вещественного");
                                            LabelVar.setText(tekst);
                                            MainTextArea.selectRange(kpn, k + 1);
                                            MainTextArea.setStyle("-fx-text-inner-color: red;");
                                            return;
                                        }
                                        if (elem[k-1] == '.') {
                                            LabelErrors.setText("Лишняя точка ");
                                            LabelVar.setText(tekst);
                                            MainTextArea.selectRange(k, k + 1);
                                            MainTextArea.setStyle("-fx-text-inner-color: red;");
                                            return;
                                        }
                                        if (elem[kp] == ' ') {
                                            break;
                                        }
                                    }
                                    if ((ind = numericwr.indexOf(elem[k])) != -1) {
                                        LabelErrors.setText("Неправильная арифметика");
                                        LabelVar.setText(tekst);
                                        MainTextArea.selectRange(kpn, kp + 1);
                                        MainTextArea.setStyle("-fx-text-inner-color: red;");
                                        return;
                                    }
                                    if ((ind = numeric2.indexOf(elem[kp])) == -1) {
                                        LabelErrors.setText("Ожидался пробел");
                                        LabelVar.setText(tekst);
                                        MainTextArea.selectRange(kp, kp + 1);
                                        MainTextArea.setStyle("-fx-text-inner-color: red;");
                                        return;
                                    }
                                    if (elem[kp] == ' ') {
                                        break;
                                    }
                                }
                                if (word.equals("")) {
                                    LabelErrors.setText("Пропущено вещественное !" + word);
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kpn, kp + 1);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                                if ((ind = numeric2.indexOf(elem[kp])) == -1) {
                                    LabelErrors.setText("Ожидался пробел");
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kp, kp + 1);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                                if (elem[kp] == ' ' && (ind = numeric.indexOf(elem[kp + 1])) == -1) {
                                    prob++;
                                    kp++;
                                }
                                if (elem[kp] == ' ' && (ind = numeric.indexOf(elem[kp + 1])) != -1){
                                    kp++;
                                    continue;
                                }

                            } while (prob == 0);
                            if (number > 0) {
                                if (dot != 0) {
                                    word = new String();
                                    kpn = kp;
                                    for (int l = (kp); l < inputtext.length(); l++) {
                                        if ((ind = alphabet.indexOf(elem[l])) != -1) {
                                            word += elem[l];
                                            kp++;
                                        } else {
                                            break;
                                        }
                                    }
                                    if (word.equals("Первое") == true || word.equals("Второе") == true) {
                                        if (inputtext.charAt(kp) == ',') {
                                            prob = 0;
                                            zap1++;
                                            continue;
                                        }
                                        if (inputtext.charAt(kp) == ' ') {
                                            zap++;
                                            kp2 = true;
                                        } else {

                                            LabelErrors.setText("Ожидался пробел или запятая , а встречено - " + inputtext.charAt(kp));
                                            LabelVar.setText(tekst);
                                            MainTextArea.selectRange(kp, kp + 1);
                                            MainTextArea.setStyle("-fx-text-inner-color: red;");
                                            return;
                                        }

                                    } else {

                                        LabelErrors.setText("Ожидалось слово Первое или Второе, а встречено - " + word);
                                        LabelVar.setText(tekst);
                                        MainTextArea.selectRange(kpn, kp+1);
                                        MainTextArea.setStyle("-fx-text-inner-color: red;");
                                        return;
                                    }
                                } else {
                                    LabelErrors.setText("Ожидалось вещественное , а получено простое!" + word);
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kpn, kp);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                            }
                            if (number == 0) {
                                LabelErrors.setText("Ожидалось вещественное , а получен другой символ");
                                LabelVar.setText(tekst);
                                MainTextArea.selectRange(kpn, kp);
                                MainTextArea.setStyle("-fx-text-inner-color: red;");
                                return;
                            }
                        } else {
                            LabelErrors.setText("Ожидалось : , а получен другой символ");
                            LabelVar.setText(tekst);
                            MainTextArea.selectRange(kpn, kp);
                            MainTextArea.setStyle("-fx-text-inner-color: red;");
                            return;
                        }
                    } else {
                        LabelErrors.setText("Ожидалось Выполнить Зарезервировать - " + word);
                        LabelVar.setText(tekst);
                        MainTextArea.selectRange(kpn, kp);
                        MainTextArea.setStyle("-fx-text-inner-color: red;");
                        return;
                    }
                } while (zap == 0);
            } else {
                LabelErrors.setText("Ожидалось Выполнить или Зарезервировать - " + word);
                LabelVar.setText(tekst);
                MainTextArea.selectRange(kp, kp + 1);
                MainTextArea.setStyle("-fx-text-inner-color: red;");
                return;
            }
        }
        if (kp2 == true) {

            kp++;
            zap = 0;
            do {
                m = 1;
                word = new String();
                String word1 = new String();
                word1 = "";
                String numer = new String();
                numer = "";
                String word2 = new String();
                word2 = "";
                String numer1 = new String();
                numer1 = "";
                String operaziya = new String();
                operaziya = "";
                if ((ind = alphabet.indexOf(elem[kp])) != -1) {
                    word += elem[kp];
                    kp++;
                    while ((ind = symbols.indexOf(elem[kp])) != -1){
                        zap1 = 0;
                        word += elem[kp];
                        if ((word.equals(krit2) == true) && (perem != 0)) {
                            LabelErrors.setText("Всё хорошо");
//                            Key.setCellValueFactory(cellData -> cellData.getValue().keyProperty());
//                            Value.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
                            MyTable.setItems(variable);
                            MainTextArea.selectRange(kp, kp + 1);
                            MainTextArea.setStyle("-fx-text-inner-color: black;");
                            return;
                        }
                        kp++;
                    }

                    if ((word.equals(krit2) == true) && (perem == 0)) {
                        LabelErrors.setText("Ожидалось хотя бы одно слагаемое");
                        LabelVar.setText(tekst);
                        MainTextArea.selectRange(kp, kp + 1);
                        MainTextArea.setStyle("-fx-text-inner-color: red;");
                        return;
                    }
                    if (elem[kp] == '=') {
                        kp++;
                        if (elem[kp] == '-') {
                            firstminus++;
                            kp++;
                        }
                        do {
                            zap2 = 0;
                            numer = new String();
                            word1 = new String();
                            // Проверка и ввод название введенной переменной в переменную word1
                            if ((ind = alphabet.indexOf(elem[kp])) != -1) {
                                perem++;
                                word1 += elem[kp];
                                kp++;
                                while ((ind = symbols.indexOf(elem[kp])) != -1) {
                                    word1 += elem[kp];
                                    kp++;
                                }
                                // Проверка на русский алфавит
                                if ((ind = enalphabet.indexOf(elem[kp])) != -1) {
                                    LabelErrors.setText("Неправильный язык");
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kp, kp + 1);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                                // Проверка на наличие в таблице такой переменной
                                String finalWord = word1;
                                if (list.stream().filter(arg0->arg0.getKey().equals(finalWord)).findFirst().isPresent() == false) {
                                    LabelErrors.setText("Отсутствует заданная переменная " + word1);
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kp, kp + 1);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                            }
                            // Проверка и ввод значения введеного целого в переменную numer
                            if ((ind = numeric.indexOf(elem[kp])) != -1) {
                                perem++;
                                while ((ind = numeric.indexOf(elem[kp])) != -1) {
                                    numer += elem[kp];
                                    kp++;
                                }
                            }
                            // Проверка на неправильную арифметику
                            if ((ind = numericwr.indexOf(elem[kp-1])) != -1) {
                                LabelErrors.setText("Неправильная арифметика");
                                LabelVar.setText(tekst);
                                MainTextArea.selectRange(kp, kp + 1);
                                MainTextArea.setStyle("-fx-text-inner-color: red;");
                                return;
                            }
                            // Проверка введенного символа + - * / ^
                            if ((ind = operaz.indexOf(elem[kp])) != -1) {
                                operazii.add(Character.toString(elem[kp]));
                                if (perem == 0) {
                                    LabelErrors.setText("Ожидалась переменная перед операцией");
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kp, kp + 1);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                                if (word1.equals("") == false) {
                                    String finalWord1 = word1;
                                    operandy.add(list.stream().filter(arg0->arg0.getKey().equals(finalWord1)).findFirst().get().getValue());
                                    vlogenost.add(Integer.toString(m));
                                }
                                if (numer.equals("") == false) {
                                    operandy.add(Double.parseDouble(numer));
                                    vlogenost.add(Integer.toString(m));
                                }
                                kp++;
                                continue;
                            }
                            // Проверка на наличие вложенности и возвращение в начало цикла
                            if (elem[kp] == '(') {
                                // Внос текущей переменной в double список
                                if (word1.equals("") == false) {
                                    String finalWord2 = word1;
                                    operandy.add(list.stream().filter(arg0->arg0.getKey().equals(finalWord2)).findFirst().get().getValue());
                                    vlogenost.add(Integer.toString(m));
                                }
                                if (numer.equals("") == false) {
                                    operandy.add(Double.parseDouble(numer));
                                    vlogenost.add(Integer.toString(m));
                                }
                                skobnach++;
                                kp++;
                                m++;
                                continue;
                            }
//               //Проверка основных операций
                            if (elem[kp] == ')') {
                                if (word1.equals("") == false) {
                                    String finalWord3 = word1;
                                    operandy.add(list.stream().filter(arg0->arg0.getKey().equals(finalWord3)).findFirst().get().getValue());
                                    vlogenost.add(Integer.toString(m));
                                }
                                if (numer.equals("") == false) {
                                    operandy.add(Double.parseDouble(numer));
                                    vlogenost.add(Integer.toString(m));
                                }
                                skobkonz++;
                                kp++;
                                continue;
                            }
                            if (elem[kp] == ')' && elem[kp-1] == '(') {
                                LabelErrors.setText("Пропущено слагаемое в скобках");
                                LabelVar.setText(tekst);
                                MainTextArea.selectRange(kp - 1, kp);
                                MainTextArea.setStyle("-fx-text-inner-color: red;");
                                return;
                            }
                            // Проверка конца пр.части
                            if (elem[kp] == ' ') {
                                if (skobnach != skobkonz) {
                                    LabelErrors.setText("Разное кол-во открывающих и закрывающих скобок");
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kp - 1, kp);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                                if (perem == 0) {
                                    LabelErrors.setText("ожидалась хотя бы одна переменная");
                                    LabelVar.setText(tekst);
                                    MainTextArea.selectRange(kp - 1, kp);
                                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                                    return;
                                }
                                if (word1.equals("") == false) {
                                    String finalWord4 = word1;
                                    operandy.add(list.stream().filter(arg0->arg0.getKey().equals(finalWord4)).findFirst().get().getValue());
                                    vlogenost.add(Integer.toString(m));
                                }
                                if (numer.equals("") == false) {
                                    operandy.add(Double.parseDouble(numer));
                                    vlogenost.add(Integer.toString(m));
                                }
                                vse1=m;
                                do {

                                    int lastin = -1;
                                    Double per = 0.0;
                                    String copy = new String();
                                    copy = Integer.toString(vse1);
                                    if ((operazii.lastIndexOf("^") != -1) && (vlogenost.get(operazii.lastIndexOf("^")).equals(copy))) {
                                        lastin = operazii.lastIndexOf("^");
                                        per = Math.pow(operandy.get(lastin),operandy.get(lastin+1));
                                        operandy.remove(lastin+1);
                                        operandy.set(lastin,per);
                                        operazii.remove(lastin);
                                        vlogenost.remove(lastin);
                                        vse++;
                                        continue;
                                    }
                                    if ((operazii.lastIndexOf("*") != -1) && (vlogenost.get(operazii.lastIndexOf("*")).equals(copy))) {
                                        lastin = operazii.lastIndexOf("*");
                                        per = operandy.get(lastin) * operandy.get(lastin+1);
                                        operandy.remove(lastin+1);
                                        operandy.set(lastin,per);
                                        operazii.remove(lastin);
                                        vlogenost.remove(lastin);
                                        vse++;
                                        continue;
                                    }
                                    if ((operazii.lastIndexOf("/") != -1) && (vlogenost.get(operazii.lastIndexOf("/")).equals(copy))) {
                                        lastin = operazii.lastIndexOf("/");
                                        if (operandy.get(lastin+1) == 0) {
                                            LabelErrors.setText("Деление на 0");
                                            LabelVar.setText(tekst);
                                            MainTextArea.setStyle("-fx-text-inner-color: red;");
                                            return;
                                        }
                                        per = operandy.get(lastin) / operandy.get(lastin+1);
                                        operandy.remove(lastin+1);
                                        operandy.set(lastin,per);
                                        operazii.remove(lastin);
                                        vlogenost.remove(lastin);
                                        vse++;
                                        continue;
                                    }
                                    if ((operazii.lastIndexOf("+") != -1) && (vlogenost.get(operazii.lastIndexOf("+")).equals(copy))) {
                                        lastin = operazii.lastIndexOf("+");
                                        per = operandy.get(lastin) + operandy.get(lastin+1);
                                        operandy.remove(lastin+1);
                                        operandy.set(lastin,per);
                                        operazii.remove(lastin);
                                        vlogenost.remove(lastin);
                                        vse++;
                                        continue;
                                    }
                                    if ((operazii.lastIndexOf("-") != -1) && (vlogenost.get(operazii.lastIndexOf("-")).equals(copy))) {
                                        lastin = operazii.lastIndexOf("-");
                                        per = operandy.get(lastin) - operandy.get(lastin+1);
                                        operandy.remove(lastin+1);
                                        operandy.set(lastin,per);
                                        operazii.remove(lastin);
                                        vlogenost.remove(lastin);
                                        vse++;
                                        continue;
                                    }
                                    else {
                                        vse1--;
                                    }
                                } while (vse1 != 0);
                                Data data = new Data(word, operandy.get(0));
//                                data.setKey(word);
//                                data.setValue(operandy.get(0));
                                variable.add(data);
                                operandy.clear();
                                vlogenost.clear();
                                operazii.clear();
                                kp++;
                                zap2++;
                            } else {
                                LabelErrors.setText("Неправильный символ");
                                LabelVar.setText(tekst);
                                MainTextArea.selectRange(kp - 1, kp);
                                MainTextArea.setStyle("-fx-text-inner-color: red;");
                                return;
                            }
                        } while (zap2 == 0);
                    } else {
                        LabelErrors.setText("Ожидалось = " + word);
                        LabelVar.setText(tekst);
                        MainTextArea.selectRange(kp - 1, kp);
                        MainTextArea.setStyle("-fx-text-inner-color: red;");
                        return;
                    }
                } else {
                    LabelErrors.setText("Ожидалась начало переменной " + word);
                    LabelVar.setText(tekst);
                    MainTextArea.selectRange(kp - 1, kp);
                    MainTextArea.setStyle("-fx-text-inner-color: red;");
                    return;
                }
            } while (zap == 0);//Условие на наличия слова конец и наличия хотя бы одного слагаемого
            LabelErrors.setText("Ошибок не найдено");
            LabelVar.setText(tekst);
            MainTextArea.setStyle("-fx-text-inner-color: black;");
            return;
            // Создание ключа key под названием Word с значением itog
        }
    }
}