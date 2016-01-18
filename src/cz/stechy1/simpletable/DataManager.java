package cz.stechy1.simpletable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Petr on 18. 6. 2015.
 */
public class DataManager {

    private ObservableList<DataModel> list = FXCollections.observableArrayList();

    public DataManager(ObservableList<DataModel> list) {
        this.list = list;
    }

    /**
     * P�id� dato do kolekce
     * @param data Data
     */
    public void add(DataModel data) {
        list.add(data);
    }
}
