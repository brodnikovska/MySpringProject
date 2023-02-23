package example.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource(value = "classpath:/application.properties")
public class Storage {

    @Value("${csv.file.path}")
    private String csvFilePath;

    private Map<String, String> generalStorage = new HashMap<>();

    @PostConstruct
    public void init() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String dataKey = values[0];
                String dataValue = values[1];
                generalStorage.putIfAbsent(dataKey, dataValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getItem (String id) {
        return generalStorage.get(id);
    }

    public void putItem (String id, String value) {
        generalStorage.putIfAbsent(id, value);
    }

    public void updateItem (String id, String value) {
        generalStorage.replace(id, value);
    }

    public boolean deleteItem (String id) {
        return generalStorage.remove(id) != null;
    }

    public boolean isItemPresent (String id) {
        return generalStorage.containsValue(id);
    }

    public Map<String, String> getAllItems () {
        return generalStorage;
    }
}
